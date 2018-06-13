package de.fhluebeck.group3.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import de.fhluebeck.group3.DAO.ExportPDF;
import de.fhluebeck.group3.DAO.RecipeDAO;
import de.fhluebeck.group3.DAO.UserDAO;
import de.fhluebeck.group3.model.Ingredient;
import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.model.Step;
import de.fhluebeck.group3.util.StringUtil;
import de.fhluebeck.group3.view.Template;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The controller of the main recipe view - MainRecipeFrame.fxml. This
 * controller includes basic view loading functions and logical functions for
 * basic operations on the view. This controller calls the functions from
 * RecipeDAO directly.
 * 
 * @author Eason.Hua on 2018/05/31.
 */
public final class MainFrameController implements Initializable {

	final ToggleGroup radioGroup = new ToggleGroup();

	public static final String SYSTEM_IMAGE_DEFAULT_PATH = "src/de/fhluebeck/group3/resources/system/";

	public static final String RECIPE_IMAGE_DEFAULT_PATH = "src/de/fhluebeck/group3/resources/recipe/";

	protected boolean isShowFavorite;

	protected List<Recipe> currentRecipe;

	protected Recipe selectedRecipe;

	protected ObservableList<Step> stepData;

	protected ObservableList<Ingredient> ingredientData;

	protected ExportPDF exportPDF = null;
	
	protected boolean likeButtonTriggered = false;

	@FXML
	private Button homeButton;

	@FXML
	private Button FavButton;

	@FXML
	private Button LogoutButton;

	@FXML
	private Button searchButton;

	@FXML
	private Button addFavoriteButton;

	@FXML
	private Button editRecipeButton;

	@FXML
	private Button exportPDFButton;

	@FXML
	private Button deleteRecipeButton;

	@FXML
	private Button addRecipeButton;

	@FXML
	private Label currentUserName;

	@FXML
	private RadioButton searchByName;

	@FXML
	private RadioButton searchByIngredient;

	@FXML
	private Label currentTimeLabel;

	@FXML
	private Label preparationTimeLabel;

	@FXML
	private Label ServingPeopleLabel;

	@FXML
	private Label recipeName;

	@FXML
	private Label cookingTimeLabel;

	@FXML
	private Label DescriptionLabel;

	@FXML
	private Label recipeOwnerLabel;

	@FXML
	private TextField searchInput;

	@FXML
	private TableView<Ingredient> ingredientTable;

	@FXML
	private TableColumn<Ingredient, String> ingredientNameColumn;

	@FXML
	private TableColumn<Ingredient, Double> ingredientQuantityColumn;

	@FXML
	private TableColumn<Ingredient, String> ingredientUnitColumn;

	@FXML
	private TableColumn<Ingredient, String> ingredientCommentColumn;

	@FXML
	private TableView<Step> stepsTable;

	@FXML
	private TableColumn<Step, Integer> stepOrderColumn;

	@FXML
	private TableColumn<Step, String> stepContentColumn;

	@FXML
	protected ListView<AnchorPane> recipesList;

	@FXML
	private ImageView recipePic;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Initialize the PDF exporter.
		try {
			this.exportPDF = new ExportPDF();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		this.initialSetAllElementProperities();

		this.refreshView();

	}

	/**
	 * Given recipes to show, and display them in the listView.
	 * 
	 * @param ArrayList<Recipe>
	 *            results, the searching results(matching recipes) after clicking
	 *            the search button or first time loading we search the whole recipe
	 *            table.
	 * 
	 */
	private void showRecipeList(List<Recipe> results) throws IOException {

		ObservableList<AnchorPane> anchorPaneList = FXCollections.observableArrayList();

		for (int i = 0; i < results.size(); i++) {

			// Load root layout from FXML file.
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Template.class.getResource("./BriefRecipeInformation.fxml"));

			loader.load();

			BriefRecipeInformationController BriefRecipeInMainPageController = loader.getController();

			BriefRecipeInMainPageController.setSelectedRecipe(results.get(i));

			anchorPaneList.add(loader.getRoot());

		}

		recipesList.setItems(anchorPaneList);

	}

	/******************************* Private Zone ******************************/

	/**
	 * 
	 * */
	protected void setIconImage(String imagePath, Button button) {
		button.setGraphic(new ImageView(new Image(new File(imagePath).toURI().toString(), 35, 30, false, false)));
	}

	/**
	 * 
	 * */
	private void setButtonIconAction(Button button, String mouseIn, String mouseOut) {

		button.setOnMouseEntered((event) -> {
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + mouseIn, button);
		});

		button.setOnMouseExited((event) -> {
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + mouseOut, button);
		});

	}

	/**
	 * 
	 * */
	private void clearRecipeInformationList() {
		// clear picture of recipe
		recipePic.setImage(null);
		// print basic information of recipe.
		DescriptionLabel.setText(null);
		recipeName.setText(null);
		ServingPeopleLabel.setText(null);
		preparationTimeLabel.setText(null);
		cookingTimeLabel.setText(null);
		// Add steps into the step table.
		stepsTable.setItems(null);
		// Add ingredients into the ingredient table.
		ingredientTable.setItems(null);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/like_out.png", this.addFavoriteButton);
	}

	/**
	 * 
	 * */
	private void refreshView() {
		// load information of recipes on ListView panel.
		try {
			this.showRecipeList(this.currentRecipe);
			this.clearRecipeInformationList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * */
	private void initialSetAllElementProperities() {

		this.isShowFavorite = false;
		
		likeButtonTriggered = false;

		this.currentRecipe = RecipeDAO.getAllRecipes();

		// Set the current user name as welcome sentence.
		this.currentUserName.setText(Template.getCurrentUser().getUsername());

		// Realized the Time on the top of system.
		DateFormat timeFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		final Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, (e) -> {
			Date date = new Date();
			currentTimeLabel.setText(timeFormat.format(date));
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();

		// Set alignment = center of table column.
		stepOrderColumn.setStyle("-fx-alignment: CENTER;");
		ingredientNameColumn.setStyle("-fx-alignment: CENTER;");
		ingredientQuantityColumn.setStyle("-fx-alignment: CENTER;");
		ingredientUnitColumn.setStyle("-fx-alignment: CENTER;");

		this.setIconImage("src/de/fhluebeck/group3/resources/system/home_on.png", this.homeButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/like_out.png", this.addFavoriteButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/add_out.png", this.addRecipeButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/like_out.png", this.FavButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/logout_out.png", this.LogoutButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/search_out.png", this.searchButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/pdf_out.png", this.exportPDFButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/edit_out.png", this.editRecipeButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/delete_out.png", this.deleteRecipeButton);

		// set radio button as a group
		this.searchByIngredient.setToggleGroup(radioGroup);
		this.searchByName.setToggleGroup(radioGroup);
		this.searchByName.setSelected(true);

		// Set button on actions.
		// setButtonIconAction(this.homeButton, "home_on.png", "home_out.png");
		// setButtonIconAction(this.FavButton, "like_on.png", "like_out.png");
		setButtonIconAction(this.addRecipeButton, "add_on.png", "add_out.png");
		setButtonIconAction(this.LogoutButton, "logout_on.png", "logout_out.png");
		setButtonIconAction(this.searchButton, "search_on.png", "search_out.png");
		setButtonIconAction(this.exportPDFButton, "pdf_on.png", "pdf_out.png");
		setButtonIconAction(this.editRecipeButton, "edit_on.png", "edit_out.png");
		setButtonIconAction(this.deleteRecipeButton, "delete_on.png", "delete_out.png");

		this.addFavoriteButton.setOnMouseEntered((event) -> {
			if (!this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png", this.addFavoriteButton);
			}
		});

		this.homeButton.setOnMouseEntered((event) -> {
			if (this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_on.png", this.homeButton);
			}

		});
		this.homeButton.setOnMouseExited((event) -> {
			if (this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_out.png", this.homeButton);
			}
		});

		this.FavButton.setOnMouseEntered((event) -> {
			if (!this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_on.png", this.FavButton);
			}

		});
		this.FavButton.setOnMouseExited((event) -> {
			if (!this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.FavButton);
			}
		});

		// when click the home button, return to the home page.
		this.homeButton.setOnAction((event) -> {
			this.isShowFavorite = false;
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_on.png", this.homeButton);
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.FavButton);

			this.currentRecipe = RecipeDAO.getAllRecipes();

			try {
				this.showRecipeList(this.currentRecipe);
				clearRecipeInformationList();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		// when click the home button, return to the home page.
		this.FavButton.setOnAction((event) -> {
			this.isShowFavorite = true;
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_on.png", this.FavButton);
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_out.png", this.homeButton);

			this.currentRecipe = RecipeDAO.getFavoritedRecipes(Template.getCurrentUser().getUserId());

			try {
				this.showRecipeList(this.currentRecipe);
				clearRecipeInformationList();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		// When click the addFavorite Button
		this.addFavoriteButton.setOnAction((event) -> {

		});

		// When click the home button, return to the home page.
		this.LogoutButton.setOnAction((event) -> {
			// shift the stage to the main Scene.
			try {
				Template.clearCurrentUser();
				Template.replaceSceneContent("./Template.fxml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// Set on action when you click the export PDF button.
		exportPDFButton.setOnAction((event) -> {

			if (exportPDF.createFile(selectedRecipe)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Export PDF Succeeded");
				alert.setContentText("We have exported the PDF file of " + selectedRecipe.getRecipeName());

				alert.showAndWait();
			}

		});

		// Set on action when you click the edit recipe button.
		editRecipeButton.setOnAction((event) -> {
			if (this.selectedRecipe.getOwnerId().equals(Template.getCurrentUser().getUserId())) {
				
				// Shift the stage to the main Scene.
				this.showAddOrEditRecipeView(selectedRecipe);
				
				//TODO refresh the whole scene.
				
			} else {
				Alert alert = new Alert(AlertType.ERROR,
						"Error: You are not the owner of the recipe, No Permission to edit!!", ButtonType.OK);
				alert.showAndWait();
			}

		});

		// Set on action when you click the add recipe button.
		this.addRecipeButton.setOnAction((event) -> {
			this.showAddOrEditRecipeView(null);
		});

		// Set on action when you click the delete Recipe button.
		this.deleteRecipeButton.setOnAction((event) -> {
			if (this.selectedRecipe.getOwnerId().equals(Template.getCurrentUser().getUserId())) {
				Alert alert = new Alert(AlertType.WARNING,
						"Do you really want to delete " + this.selectedRecipe.getRecipeName() + "?", ButtonType.YES,
						ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == ButtonType.YES) { // User confirms the deletion.

					if (RecipeDAO.deleteRecipe(this.selectedRecipe.getRecipeID())) {

						new Alert(AlertType.CONFIRMATION, "Recipe has been deleted !", ButtonType.OK).showAndWait();

						// Distinguish between favorite recipes and all recipes.
						this.currentRecipe = RecipeDAO.getAllRecipes();

						this.refreshView();

					}

				} else { // User cancels the deletion.

					/** do nothing */

				}
			} else {
				Alert alert = new Alert(AlertType.ERROR,
						"Error: You are not the owner of the recipe, No Permission to delete!!", ButtonType.OK);
				alert.showAndWait();
			}
		});

		searchInput.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (!isShowFavorite) { // if not search for favorite recipes.
					if (searchByName.isSelected()) {
						currentRecipe = RecipeDAO.getRecipesByName(newValue);
					} else if (searchByIngredient.isSelected()) {
						currentRecipe = RecipeDAO.getRecipesByIngredient(newValue);
					}
				} else {
					if (searchByName.isSelected()) {
						// TODO search recipe by name and ingredient within favorite recipes.
						// currentRecipe = RecipeDAO.getRecipesByName(newValue);
					} else if (searchByIngredient.isSelected()) {
						// currentRecipe = RecipeDAO.getRecipesByIngredient(newValue);
					}
				}
				refreshView();

			}
		});

		// add listener to the Element in the list view.
		this.recipesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AnchorPane>() {

			@Override
			public void changed(ObservableValue<? extends AnchorPane> observable, AnchorPane oldValue,
					AnchorPane newValue) {

				if (recipesList.getSelectionModel().getSelectedIndex() > -1) {
					selectedRecipe = currentRecipe.get(recipesList.getSelectionModel().getSelectedIndex());
					// set picture of recipe
					String uri = MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + selectedRecipe.getImagePath();
					recipePic.setImage(new Image(new File(uri).toURI().toString(), 80, 80, false, false));
					// print basic information of recipe.
					DescriptionLabel
							.setText(StringUtil.textProcessingBeforeOutput(selectedRecipe.getDescription(), 50, 100));
					recipeName.setText(selectedRecipe.getRecipeName());
					ServingPeopleLabel.setText(new Integer(selectedRecipe.getAvailablePeople()).toString());
					preparationTimeLabel.setText(new Integer(selectedRecipe.getPreparationTime()).toString());
					cookingTimeLabel.setText(new Integer(selectedRecipe.getCookingTime()).toString());
					recipeOwnerLabel.setText(UserDAO.getUserById(selectedRecipe.getOwnerId()).getUsername());

					// Set the picture of the red-heart button.
					if (isShowFavorite) {
						likeButtonTriggered = true;
						setIconImage("src/de/fhluebeck/group3/resources/system/like_redheart.png", addFavoriteButton);
					} else {
						List<Recipe> favRecipes = Template.getCurrentUser().getFavoriteRecipes();
						setIconImage("src/de/fhluebeck/group3/resources/system/like_out.png", addFavoriteButton);
						likeButtonTriggered = false;
						for (Recipe recipe : favRecipes) {
							if (recipe.getRecipeID().equals(selectedRecipe.getRecipeID())) {
								setIconImage("src/de/fhluebeck/group3/resources/system/like_redheart.png",
										addFavoriteButton);
								likeButtonTriggered = true;
								break;
							}
						}

					}

					// Add steps into the step table.
					stepData = FXCollections.observableArrayList();
					for (Step step : selectedRecipe.getSteps()) {
						stepData.add(step);
					}
					stepsTable.setItems(stepData);
					stepOrderColumn.setCellValueFactory(cellData -> cellData.getValue().getIntegerProperityStepOrder());
					stepContentColumn
							.setCellValueFactory(cellData -> cellData.getValue().getStringProperityStepContent());
					// Add ingredients into the ingredient table.
					ingredientData = FXCollections.observableArrayList();
					for (Ingredient ingredient : selectedRecipe.getIngredients()) {
						ingredientData.add(ingredient);
					}
					ingredientTable.setItems(ingredientData);
					ingredientNameColumn
							.setCellValueFactory(cellData -> cellData.getValue().getStringProperityIngredientName());
					ingredientQuantityColumn
							.setCellValueFactory(cellData -> cellData.getValue().getDoubleProperityQuantity());
					ingredientUnitColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperityUnit());
					ingredientCommentColumn
							.setCellValueFactory(cellData -> cellData.getValue().getStringProperityComment());
				}

			}
		});

	}
	
	/**
	 * 
	 * */
	private void showAddOrEditRecipeView(Recipe recipe) {
		
		// shift the stage to the main Scene.
		try {
			// Parent parent =
			// FXMLLoader.load(Template.class.getResource("../view/AddOrEditRecipe.fxml"),
			// null, new JavaFXBuilderFactory());
			FXMLLoader loader = new FXMLLoader(Template.class.getResource("../view/AddOrEditRecipe.fxml"), null,
					new JavaFXBuilderFactory());
			Parent parent = loader.load();
			if(recipe != null) {
				AddOrEditRecipeController controller = loader.getController();
				controller.setEditedRecipe(recipe);
			}

			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
