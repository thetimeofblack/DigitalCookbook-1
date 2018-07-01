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
import de.fhluebeck.group3.model.User;
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
import javafx.stage.Modality;
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

	public static final String PC_IMAGE_DEFAULT_PATH = "src\\de\\fhluebeck\\group3\\resources\\recipe\\";

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

	/**
	 * Overrides the initialize functions in the interface Initializable. Do the
	 * major thing when the view is loaded.
	 * 
	 * @param location
	 *            the location of the new view.
	 * @param resources
	 *            resource for the view.
	 * 
	 */
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
	 * Set Icon image for specified button.
	 * 
	 * @param imagePath
	 *            the relative path of the image (In our project, only the image
	 *            name, prefix will be added later).
	 * @param button
	 *            the JavaFX Button that we want to add image on.
	 */
	protected void setIconImage(String imagePath, Button button) {
		button.setGraphic(new ImageView(new Image(new File(imagePath).toURI().toString(), 35, 30, false, false)));
	}

	/**
	 * Set the image for the JavaFX Button both when mouse is in or out of the
	 * button.
	 * 
	 * @param button
	 *            the JavaFX Button that we want to add image on.
	 * 
	 * @param mouseIn
	 *            imagePath the relative path of the image when user put their mouse
	 *            on the button.
	 * 
	 * @param mouseOut
	 *            imagePath the relative path of the image when user put their mouse
	 *            out of the button.
	 * 
	 */
	private void setButtonIconAction(Button button, String mouseIn, String mouseOut) {

		button.setOnMouseEntered((event) -> {
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + mouseIn, button);
		});

		button.setOnMouseExited((event) -> {
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + mouseOut, button);
		});

	}

	/**
	 * Clear the whole list of information on Recipe.
	 */
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
	 * Refresh the main interface -- reload the recipes and clear the information
	 * panel.
	 */
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
	 * This function is responsible for set all the properities of the elements.
	 */
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

		// Set image on buttons.
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_on.png", this.homeButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.addFavoriteButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "add_out.png", this.addRecipeButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.FavButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "logout_out.png", this.LogoutButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "search_out.png", this.searchButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "pdf_out.png", this.exportPDFButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "edit_out.png", this.editRecipeButton);
		this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "delete_out.png", this.deleteRecipeButton);

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

		// Set actions when users move their mouse on the addFavoriteButton.
		this.addFavoriteButton.setOnMouseEntered((event) -> {
			if (!this.isShowFavorite && !likeButtonTriggered) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png", this.addFavoriteButton);
			}
		});

		// Set actions when users move their mouse out of the addFavoriteButton.
		this.addFavoriteButton.setOnMouseExited((event) -> {
			if (!this.isShowFavorite && !likeButtonTriggered) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.addFavoriteButton);
			}
		});

		// Set actions when users move their mouse on the homeButton.
		this.homeButton.setOnMouseEntered((event) -> {
			if (this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_on.png", this.homeButton);
			}

		});

		// Set actions when users move their mouse out of the homeButton.
		this.homeButton.setOnMouseExited((event) -> {
			if (this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "home_out.png", this.homeButton);
			}
		});

		// Set actions when users move their mouse on the FavButton.
		this.FavButton.setOnMouseEntered((event) -> {
			if (!this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png", this.FavButton);
			}

		});

		// Set actions when users move their mouse out of the FavButton.
		this.FavButton.setOnMouseExited((event) -> {
			if (!this.isShowFavorite) {
				this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.FavButton);
			}
		});

		// When click the home button, return to the home page.
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

		// When click the home button, return to the home page.
		this.FavButton.setOnAction((event) -> {
			this.isShowFavorite = true;
			this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png", this.FavButton);
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

			if (isShowFavorite) { // if showing favorite

				Alert alert = new Alert(AlertType.WARNING, "Do you really want to remove recipe "
						+ this.selectedRecipe.getRecipeName() + "from you favorite list?", ButtonType.OK,
						ButtonType.NO);
				alert.setTitle("Remove from your favorite List");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					if (RecipeDAO.removeRecipeFromFavoriteList(this.selectedRecipe.getRecipeID(),
							Template.getCurrentUser().getUserId())) {

						this.reloadFavRecipeListOfCurrentUser();

						new Alert(AlertType.INFORMATION,
								"Recipe " + this.selectedRecipe.getRecipeName() + "has removed to your favorite list",
								ButtonType.OK).showAndWait();

						this.currentRecipe = RecipeDAO.getFavoritedRecipes(Template.getCurrentUser().getUserId());

						this.refreshView();
					} else {
						System.out.println("Problem encountered");
					}

				}

			} else { // if not showing favorite

				if (likeButtonTriggered) { // current user likes this recipe

					Alert alert = new Alert(
							AlertType.WARNING, "Do you really want to remove recipe "
									+ this.selectedRecipe.getRecipeName() + "from you favorite list?",
							ButtonType.OK, ButtonType.NO);
					alert.setTitle("Remove from your favorite List");

					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == ButtonType.OK) {

						if (RecipeDAO.removeRecipeFromFavoriteList(this.selectedRecipe.getRecipeID(),
								Template.getCurrentUser().getUserId())) {

							this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", this.addFavoriteButton);

							this.likeButtonTriggered = false;

							this.reloadFavRecipeListOfCurrentUser();

							new Alert(AlertType.INFORMATION, "Recipe " + this.selectedRecipe.getRecipeName()
									+ "has removed to your favorite list", ButtonType.OK).showAndWait();
						} else {
							System.out.println("Problem encountered");
						}

					}

				} else { // current user does not like this recipe

					if (RecipeDAO.addRecipeToFavoriteList(this.selectedRecipe.getRecipeID(),
							Template.getCurrentUser().getUserId())) {

						this.setIconImage(SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png", this.addFavoriteButton);

						this.likeButtonTriggered = true;

						this.reloadFavRecipeListOfCurrentUser();

						new Alert(AlertType.INFORMATION,
								"Recipe " + this.selectedRecipe.getRecipeName() + "has added to your favorite list",
								ButtonType.OK).showAndWait();

					} else {
						System.out.println("Problem encountered");
					}

				}
			}

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

				// refresh the whole scene in main frame.

			} else {
				Alert alert = new Alert(AlertType.ERROR,
						"Error: You are not the owner of the recipe, No Permission to edit!!", ButtonType.OK);
				alert.showAndWait();
			}

		});

		// Set on action when you click the add recipe button.
		this.addRecipeButton.setOnAction((event) -> {

			this.showAddOrEditRecipeView(null);

			// refresh the whole scene.
			this.refreshWholeInterface();
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

		// Set on action when the user change the content in the TextArea searchInput.
		searchInput.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (!isShowFavorite) { // if not search for favorite recipes.
					if (searchByName.isSelected()) {
						currentRecipe = RecipeDAO.getRecipesByName(newValue);
					} else if (searchByIngredient.isSelected()) {
						currentRecipe = RecipeDAO.getRecipesByIngredient(newValue);
					}
				} else { // if search for favorite recipes.
					if (searchByName.isSelected()) {
						// Search recipe by name within favorite recipes.
						currentRecipe = RecipeDAO.getFavRecipeByName(newValue, Template.getCurrentUser().getUserId());
					} else if (searchByIngredient.isSelected()) {
						// Search recipe by ingredient within favorite recipes.
						currentRecipe = RecipeDAO.getFavRecipeByIngredients(newValue,
								Template.getCurrentUser().getUserId());
					}
				}

				refreshView();

			}
		});

		// Add listener to the Element in the list view.
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
							.setText(StringUtil.textProcessingBeforeOutput(selectedRecipe.getDescription(), 50, 200));
					recipeName.setText(selectedRecipe.getRecipeName());
					ServingPeopleLabel.setText(new Integer(selectedRecipe.getAvailablePeople()).toString());
					preparationTimeLabel.setText(new Integer(selectedRecipe.getPreparationTime()).toString());
					cookingTimeLabel.setText(new Integer(selectedRecipe.getCookingTime()).toString());
					recipeOwnerLabel.setText(UserDAO.getUserById(selectedRecipe.getOwnerId()).getUsername());

					// Set the picture of the red-heart button.
					if (isShowFavorite) {
						likeButtonTriggered = true;
						setIconImage(MainFrameController.SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png",
								addFavoriteButton);
					} else {
						List<Recipe> favRecipes = Template.getCurrentUser().getFavoriteRecipes();
						setIconImage(MainFrameController.SYSTEM_IMAGE_DEFAULT_PATH + "like_out.png", addFavoriteButton);
						likeButtonTriggered = false;
						for (int i = 0; i < favRecipes.size(); i += 1) {
							if (favRecipes.get(i).getRecipeID().equals(selectedRecipe.getRecipeID())) { // If user is
																										// like the
																										// recipe.
								setIconImage(MainFrameController.SYSTEM_IMAGE_DEFAULT_PATH + "like_redheart.png",
										addFavoriteButton);
								likeButtonTriggered = true;
								break;
							}
						}

					}

					if (selectedRecipe.getSteps() != null && selectedRecipe.getSteps().size() > 0) {
						// Add steps into the step table.
						stepData = FXCollections.observableArrayList();
						for (Step step : selectedRecipe.getSteps()) {
							stepData.add(step);
						}
						stepsTable.setItems(stepData);
						stepOrderColumn
								.setCellValueFactory(cellData -> cellData.getValue().getIntegerProperityStepOrder());
						stepContentColumn
								.setCellValueFactory(cellData -> cellData.getValue().getStringProperityStepContent());
					} else {
						stepsTable.setItems(null);
					}

					if (selectedRecipe.getIngredients() != null && selectedRecipe.getIngredients().size() > 0) {
						// Add ingredients into the ingredient table.
						ingredientData = FXCollections.observableArrayList();
						for (Ingredient ingredient : selectedRecipe.getIngredients()) {
							ingredientData.add(ingredient);
						}
						ingredientTable.setItems(ingredientData);
						ingredientNameColumn.setCellValueFactory(
								cellData -> cellData.getValue().getStringProperityIngredientName());
						ingredientQuantityColumn
								.setCellValueFactory(cellData -> cellData.getValue().getDoubleProperityQuantity());
						ingredientUnitColumn
								.setCellValueFactory(cellData -> cellData.getValue().getStringProperityUnit());
						ingredientCommentColumn
								.setCellValueFactory(cellData -> cellData.getValue().getStringProperityComment());
					} else {
						ingredientTable.setItems(null);
					}
				}

			}
		});

	}

	/**
	 * When the user clicks the Edit (Pencil Image) or Add Recipe button, load the
	 * AddOrEditRecipe.fxml and presents it in a new window.
	 * 
	 * @param recipe
	 *            the recipe the user wants to edit or null when the user wants to
	 *            create a new recipe.
	 */
	private void showAddOrEditRecipeView(Recipe recipe) {

		// Shift the stage to the main Scene.
		try {
			FXMLLoader loader = new FXMLLoader(Template.class.getResource("../view/AddOrEditRecipe.fxml"), null,
					new JavaFXBuilderFactory());
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			AddOrEditRecipeController controller = loader.getController();
			controller.setEditStage(stage);
			stage.setTitle("Now Edit or Add new recipes");

			stage.getIcons().add(new Image(
					new File(MainFrameController.SYSTEM_IMAGE_DEFAULT_PATH + "cookbook.jpg").toURI().toString()));
			if (recipe != null) {
				controller.setEditedRecipe(recipe);
				controller.setParentStage(stage);
			}
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Template.getPrimaryStage());
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Reload the FavRecipeListOfCurrentUser.
	 */
	private void reloadFavRecipeListOfCurrentUser() {
		User user = null;
		if ((user = Template.getCurrentUser()) != null) {
			user.setFavoriteRecipes(RecipeDAO.getFavoritedRecipes(user.getUserId()));
			System.out.println(user.getFavoriteRecipes().size());
		}
	}

	/**
	 * Refresh the whole interface and reload all the recipes from the DB again.
	 */
	public void refreshWholeInterface() {

		this.currentRecipe = RecipeDAO.getAllRecipes();

		try {
			this.showRecipeList(this.currentRecipe);
			clearRecipeInformationList();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
