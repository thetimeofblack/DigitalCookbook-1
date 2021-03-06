package de.fhluebeck.group3.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import de.fhluebeck.group3.DAO.IngredientDAO;
import de.fhluebeck.group3.DAO.RecipeDAO;
import de.fhluebeck.group3.DAO.StepDAO;
import de.fhluebeck.group3.model.Ingredient;
import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.model.Step;
import de.fhluebeck.group3.util.FileUtil;
import de.fhluebeck.group3.util.StringUtil;
import de.fhluebeck.group3.view.Template;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * The controller of the add or edit recipe view - AddOrEditRecipe.fxml. This
 * controller includes basic view loading functions and logical functions for
 * basic operations on the view. This controller calls the functions from
 * RecipeDAO,stepDAO and ingredientDAO. The users can both add or edit recipes
 * on view AddOrEditRecipe.fxml.
 * 
 * @author Hua Yichen on 2018/06/05.
 */
public final class AddOrEditRecipeController implements Initializable {

	protected Stage editStage;

	@FXML
	private TextArea nameofRecipe;

	@FXML
	private Label recipeNameWarning;

	@FXML
	private Label invalidInputPrompt;

	@FXML
	private TextField description;

	@FXML
	private TextArea amountOfPeople;

	@FXML
	private TextArea preparingTime;

	@FXML
	private TextArea cookingTime;

	@FXML
	private Label defaultImageView;

	@FXML
	private Button choosePicture;

	@FXML
	private Button addIngredient;

	@FXML
	private Button removeIngredient;

	@FXML
	private Button removeStep;

	@FXML
	private Button addStep;

	@FXML
	protected Button saveRecipe;

	@FXML
	private Button cancel;

	@FXML
	private TableView<Ingredient> ingredients;

	@FXML
	private TableColumn<Ingredient, String> ingredientNameColumn;

	@FXML
	private TableColumn<Ingredient, Double> ingredientQuantityColumn;

	@FXML
	private TableColumn<Ingredient, String> ingredientUnitColumn;

	@FXML
	private TableColumn<Ingredient, String> ingredientCommentColumn;

	@FXML
	private TableView<Step> steps;

	@FXML
	private TableColumn<Step, Integer> stepOrderColumn;

	@FXML
	private TableColumn<Step, String> stepContentColumn;

	@FXML
	private ImageView newRecipeImage;

	protected ObservableList<Step> stepData;

	protected ObservableList<Ingredient> ingredientData;

	private Recipe editedRecipe;

	private boolean isAddingRecipe = true;

	private Stage parentStage;

	private FileChooser fileChooser;

	private String imagePath;

	private List<Ingredient> deletedIngredients;

	private List<Step> deletedSteps;

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

		this.deletedIngredients = new ArrayList<Ingredient>();

		this.deletedSteps = new ArrayList<Step>();

		this.initialSetAllElementProperities();

	}

	/**
	 * Initialization for elements including all the element for JavaFX. Including
	 * binding ActionListeners, fill in the TextAreas and Labels.
	 */
	private void initialSetAllElementProperities() {

		// Set listener to nameOfRecipe TextArea, every time user changes contents in
		// the TextArea, do validation check.
		this.nameofRecipe.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue.trim().isEmpty()) {

					setRecipeNamePromptWarning();

				} else {
					restoreRecipeNamePromptWarning();
				}

			}
		});

		// Fill the table column with object data.
		ingredientNameColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperityIngredientName());
		ingredientQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getDoubleProperityQuantity());
		ingredientUnitColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperityUnit());
		ingredientCommentColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperityComment());

		stepOrderColumn.setCellValueFactory(cellData -> cellData.getValue().getIntegerProperityStepOrder());
		stepContentColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperityStepContent());

		// Actions when the cancel button is clicked.
		this.cancel.setOnAction((event) -> {
			Alert alert = new Alert(AlertType.WARNING, "Do you want to quit editting?", ButtonType.YES,
					ButtonType.CANCEL);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES) { // User confirms the deletion.
				this.editStage.close();
			}

		});

		// Set edit events in ingredients table.
		ingredients.setEditable(true);

		// Set actions when editing ingredientNameColumn.
		ingredientNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		ingredientNameColumn.setOnEditCommit((event) -> {
			event.getTableView().getItems().get(event.getTablePosition().getRow())
					.setIngredientName(event.getNewValue());
		});

		// Set actions when editing ingredientQuantityColumn.
		ingredientQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

			@Override
			public Double fromString(String string) {
				return Double.valueOf(string);
			}

			@Override
			public String toString(Double object) {
				return String.valueOf(object);
			}
		}));

		// Set actions when editing ingredientQuantityColumn.
		ingredientQuantityColumn.setOnEditCommit((event) -> {
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setQuantity(event.getNewValue());
		});

		// Set actions when editing ingredientUnitColumn.
		ingredientUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		ingredientUnitColumn.setOnEditCommit((event) -> {
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setUnit(event.getNewValue());
		});

		// Set actions when editing ingredientCommentColumn.
		ingredientCommentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		ingredientCommentColumn.setOnEditCommit((event) -> {
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setComment(event.getNewValue());
		});

		// Actions when the add row button is clicked.
		this.addIngredient.setOnAction((event) -> {
			Ingredient ingredient;
			ingredient = new Ingredient("", 0.0d, 0, Ingredient.GRAM, "");
			int row = ingredients.getSelectionModel().getSelectedIndex();
			if (row == -1) { // if no row is selected
				row = ingredients.getItems().size() - 1;
				this.ingredients.getItems().add(ingredient);
				ingredients.scrollTo(ingredient);

			} else {

				this.ingredients.getItems().add(row + 1, ingredient);

			}

			ingredients.getSelectionModel().select(row + 1);
		});

		// Actions when the remove row button is clicked.
		this.removeIngredient.setOnAction((event) -> {

			int row = ingredients.getSelectionModel().getSelectedIndex();

			if (row < 0) {

				new Alert(AlertType.ERROR, "Please select one row ! ", ButtonType.CLOSE).showAndWait();

			} else {

				Ingredient ingredient = ingredients.getItems().get(row);

				if (ingredient.getIngredientID() != null) { // means the old ingredients.

					this.deletedIngredients.add(ingredient);

				}
				//
				// Alert alert = new Alert(AlertType.WARNING,"Do you want to delete ingredient "
				// +ingredient.getIngredientName() +"?",ButtonType.YES,ButtonType.NO);
				//
				// Optional<ButtonType> result = alert.showAndWait();
				//
				// if(result.get() == ButtonType.YES) {
				//
				// if(IngredientDAO.deleteIngredientById(ingredient.getIngredientID())) {
				//
				// new Alert(AlertType.CONFIRMATION,"Ingredient has been deleted",);
				//
				// }else {
				//
				// }
				//
				//
				// }
				//
				// }

				this.ingredients.getItems().remove(row);
				if (row < this.ingredients.getItems().size()) {
					// If selected within the range of table, cursor stay
					this.ingredients.getSelectionModel().select(row);
				} else {
					// If selected the last row of the table, cursor goes upward.
					this.ingredients.getSelectionModel().select(row - 1);
				}

			}

		});

		// Set edit events in steps table.
		steps.setEditable(true);

		// Set actions when editing stepContentColumn.
		stepContentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		stepContentColumn.setOnEditCommit((event) -> {
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setContent(event.getNewValue());
		});

		// Set actions when editing stepOrderColumn.
		stepOrderColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

			@Override
			public Integer fromString(String string) {
				return Integer.valueOf(string);
			}

			@Override
			public String toString(Integer object) {
				return String.valueOf(object);
			}
		}));

		// Actions when the addStep button is clicked.
		this.addStep.setOnAction((event) -> {

			Step step = new Step("", 1, 0);
			// int row = ingredients.getItems().size() - 1;
			int row = steps.getSelectionModel().getSelectedIndex();
			if (row == -1 && steps.getItems().size() == 0) { // if the table is empty.

				this.steps.getItems().add(step);
				steps.scrollTo(step);

			} else if (row == -1 && steps.getItems().size() > 0) { // if no row is selected.

				step.setStepOrder(steps.getItems().size() + 1);
				this.steps.getItems().add(step);
				steps.scrollTo(step);

			} else {

				step.setStepOrder(row + 1);
				this.steps.getItems().add(row + 1, step);

				for (int i = row + 1; i < steps.getItems().size(); i += 1) {
					steps.getItems().get(i).setStepOrder(i + 1);
				}

			}

			steps.getSelectionModel().select(row + 1);

		});

		// Actions when the removeStep button is clicked.
		this.removeStep.setOnAction((event) -> {

			int row = steps.getSelectionModel().getSelectedIndex();
			Step step;

			if (row < 0) {
				new Alert(AlertType.ERROR, "Please select one row ! ", ButtonType.CLOSE).showAndWait();
			} else {

				step = steps.getItems().get(row);

				if (step.getStepID() != null) {

					this.deletedSteps.add(step);

				}

				// Set the step order to their proper form.
				for (int i = row + 1; i < steps.getItems().size(); i += 1) {
					steps.getItems().get(i).setStepOrder(i);
				}

				this.steps.getItems().remove(row);
				if (row < this.steps.getItems().size()) {
					// If selected within the range of table, cursor stay
					this.steps.getSelectionModel().select(row);
				} else {
					// If selected the last row of the table, cursor up
					this.steps.getSelectionModel().select(row - 1);
				}

			}

		});

		// Actions when the choosePicture button is clicked.
		choosePicture.setOnAction((event) -> {
			File selectedFile;
			this.fileChooser = new FileChooser();
			fileChooser.setTitle("Pick the image for the recipe");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
					new FileChooser.ExtensionFilter("JPEG", "*.jpeg"), new FileChooser.ExtensionFilter("JPG", "*.jpg"),
					new FileChooser.ExtensionFilter("PNG", "*.png"));
			selectedFile = fileChooser.showOpenDialog(this.parentStage);
			if (selectedFile != null) {
				// resolve the different notation for system path for Windows and MacOS
				String systemPath = System.getProperty("user.dir") + "/" + MainFrameController.RECIPE_IMAGE_DEFAULT_PATH
						+ selectedFile.getName();
				try {
					this.imagePath = selectedFile.getName();
					FileUtil.copyFile(selectedFile, systemPath);
					this.newRecipeImage.setImage(new Image(selectedFile.toURI().toString(), 100, 100, false, false));
					this.defaultImageView.setText("");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		// Actions when the amountOfPeople textArea is changed.
		amountOfPeople.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (StringUtil.isNumeric(newValue)) {
					restoreNumberPromptWarning();

				} else {
					setNumberPromptWarning();
				}

			}

		});

		// Actions when the preparingTime textArea is changed.
		preparingTime.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (StringUtil.isNumeric(newValue)) {
					restoreNumberPromptWarning();

				} else {
					setNumberPromptWarning();
				}

			}

		});

		// Actions when the preparingTime textArea is changed.
		cookingTime.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (StringUtil.isNumeric(newValue)) {
					restoreNumberPromptWarning();

				} else {
					setNumberPromptWarning();
				}

			}

		});

		// Actions when the saveRecipe button is clicked.
		saveRecipe.setOnAction((event) -> {

			if (this.formValidationCheck()) {

				if (this.isAddingRecipe) { // add new recipe add form validation.

					boolean flag = false;

					Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to save this Recipe?",
							ButtonType.APPLY, ButtonType.CANCEL);

					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == ButtonType.APPLY) {

						flag = this.insertRecipeIntoDB();

					}

					if (flag) {

						new Alert(AlertType.INFORMATION, "Recipe Successfully Inserted !", ButtonType.OK).showAndWait();

						Object controllerObject = Template.getiFxmlLoader().getController();

						if (controllerObject instanceof MainFrameController) {
							MainFrameController controller = (MainFrameController) controllerObject;

							controller.refreshWholeInterface();
						}

						this.editStage.close();

					}

				} else { // Edit recipe

					boolean flag = false;

					Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to update this Recipe?",
							ButtonType.APPLY, ButtonType.CANCEL);

					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == ButtonType.APPLY) {

						flag = this.updateRecipeIntoDB();

					}

					if (flag) {

						new Alert(AlertType.INFORMATION, "Recipe Successfully Updated !", ButtonType.OK).showAndWait();

						Object controllerObject = Template.getiFxmlLoader().getController();

						if (controllerObject instanceof MainFrameController) {
							MainFrameController controller = (MainFrameController) controllerObject;

							controller.refreshWholeInterface();
						}

						this.editStage.close();

					}

				}

			}

		});

	}

	/**
	 * @param editedRecipe
	 *            the editedRecipe to set
	 */
	public void setEditedRecipe(Recipe editedRecipe) {
		this.editedRecipe = editedRecipe;
		this.fillInBlanksOfRecipe();
	}

	/**
	 * @return the editedRecipe
	 */
	public Recipe getEditedRecipe() {
		return editedRecipe;
	}

	/**
	 * @param editStage
	 *            the editStage to set
	 */
	public void setEditStage(Stage editStage) {
		this.editStage = editStage;
	}

	/**
	 * @return the parentStage
	 */
	public Stage getParentStage() {
		return parentStage;
	}

	/**
	 * @param parentStage
	 *            the parentStage to set
	 */
	public void setParentStage(Stage parentStage) {
		this.parentStage = parentStage;
	}

	/**
	 * When call this function, the system will fill in all the blanks regarding the
	 * basic information of an recipe, together with its corresponding ingredients
	 * and steps in the user interface.
	 */
	private void fillInBlanksOfRecipe() {

		if (this.editedRecipe != null) { // edit recipe
			this.isAddingRecipe = false;
			defaultImageView.setText("");
			// Save the image path.
			imagePath = editedRecipe.getImagePath();
			newRecipeImage.setImage(
					new Image(new File(MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + editedRecipe.getImagePath())
							.toURI().toString(), 100, 100, false, false));
			// print basic information of recipe.
			description.setText(editedRecipe.getDescription());
			nameofRecipe.setText(editedRecipe.getRecipeName());
			amountOfPeople.setText(new Integer(editedRecipe.getAvailablePeople()).toString());
			preparingTime.setText(new Integer(editedRecipe.getPreparationTime()).toString());
			cookingTime.setText(new Integer(editedRecipe.getCookingTime()).toString());
			if (editedRecipe.getSteps() != null && editedRecipe.getSteps().size() > 0) {
				// Add steps into the step table.
				stepData = FXCollections.observableArrayList();
				for (Step step : editedRecipe.getSteps()) {
					stepData.add(step);
				}
				steps.setItems(stepData);
			}
			if (editedRecipe.getIngredients() != null && editedRecipe.getIngredients().size() > 0) {
				// Add ingredients into the ingredient table.
				ingredientData = FXCollections.observableArrayList();
				for (Ingredient ingredient : editedRecipe.getIngredients()) {
					ingredientData.add(ingredient);
				}
				ingredients.setItems(ingredientData);
			}
		} else { // Add new recipe
			this.isAddingRecipe = true;
		}
	}

	/**
	 * Update the recipe the user has altered after the user clicks the Save button
	 * and confirm the alternation. This function includes update the current-exists
	 * ingredients, steps and basic information of the recipe as well as do
	 * insertion/deletion when we detected new/old ingredients or steps has
	 * added/removed to/from the database.
	 */
	private boolean updateRecipeIntoDB() {
		boolean flag = true;

		editedRecipe.setRecipeName(this.nameofRecipe.getText().trim());
		editedRecipe.setDescription(this.description.getText().trim());
		editedRecipe.setAvailablePeople(Integer.valueOf(this.amountOfPeople.getText()));
		editedRecipe.setPreparationTime(Integer.valueOf(this.preparingTime.getText()));
		editedRecipe.setCookingTime(Integer.valueOf(this.cookingTime.getText()));
		editedRecipe.setImagePath(this.imagePath.trim());
		editedRecipe.setStatus(1);
		editedRecipe.setOwnerId(Template.getCurrentUser().getUserId());

		// update just the basic information of the recipe;
		flag = RecipeDAO.updateRecipe(editedRecipe);

		if (flag) {
			// Add or update ingredients.
			List<Ingredient> newIngredients = new ArrayList<Ingredient>();
			List<Ingredient> updateIngredients = new ArrayList<Ingredient>();
			Ingredient ingredient;
			// Get all the ingredients
			for (int i = 0; i < this.ingredients.getItems().size(); i += 1) {
				ingredient = this.ingredients.getItems().get(i);
				ingredient.setRecipeID(editedRecipe.getRecipeID());

				if (ingredient.getIngredientID() == null) { // no ingredient ID means that is new Ingredients.

					ingredient.setStatus(1);
					newIngredients.add(ingredient);

				} else { // not a new Ingredient, add it to the update List.

					updateIngredients.add(ingredient);

				}

			}

			IngredientDAO.batchDeleteIngredients(deletedIngredients);
			IngredientDAO.updateBatchIngredients(updateIngredients);
			IngredientDAO.addBatchIngredients(newIngredients);
		}

		if (flag) {
			List<Step> newSteps = new ArrayList<Step>();
			List<Step> updateSteps = new ArrayList<Step>();
			Step step;
			// Get all the steps
			for (int i = 0; i < this.steps.getItems().size(); i += 1) {
				step = this.steps.getItems().get(i);
				// set the OwnerID
				step.setRecipeID(editedRecipe.getRecipeID());

				if (step.getStepID() == null) { // no step ID means that is new Step.

					newSteps.add(step);

				} else { // not a new Step, add it to the update List.

					updateSteps.add(step);

				}

			}

			StepDAO.batchDeleteSteps(this.deletedSteps);
			StepDAO.addBatchSteps(newSteps);
			StepDAO.updateBatchSteps(updateSteps);
		}

		return flag;
	}

	/**
	 * Insert the recipe into the database together with its corresponding
	 * ingredients and steps after the user has clicked the Save button and confirm
	 * the insertion action.
	 */
	private boolean insertRecipeIntoDB() {
		boolean flag = false;

		// load the recipe as new Recipe
		Recipe newRecipe = new Recipe();
		newRecipe.setRecipeName(this.nameofRecipe.getText().trim());
		newRecipe.setDescription(this.description.getText().trim());
		newRecipe.setAvailablePeople(Integer.valueOf(this.amountOfPeople.getText()));
		newRecipe.setPreparationTime(Integer.valueOf(this.preparingTime.getText()));
		newRecipe.setCookingTime(Integer.valueOf(this.cookingTime.getText()));
		newRecipe.setImagePath(this.imagePath.trim());
		newRecipe.setStatus(1);
		newRecipe.setOwnerId(Template.getCurrentUser().getUserId());

		RecipeDAO.addRecipe(newRecipe);

		newRecipe.setRecipeID(RecipeDAO.getRecipeID(newRecipe));

		List<Ingredient> newIngredients = new ArrayList<Ingredient>();
		Ingredient newIngredient;
		// Get all the ingredients
		for (int i = 0; i < this.ingredients.getItems().size(); i += 1) {
			newIngredient = this.ingredients.getItems().get(i);

			newIngredient.setRecipeID(newRecipe.getRecipeID());

			// set the OwnerID
			newIngredients.add(newIngredient);
		}

		List<Step> newSteps = new ArrayList<Step>();
		Step newStep;
		// Get all the steps
		for (int i = 0; i < this.steps.getItems().size(); i += 1) {
			newStep = this.steps.getItems().get(i);

			// set the OwnerID
			newStep.setRecipeID(newRecipe.getRecipeID());

			newSteps.add(newStep);
		}

		IngredientDAO.addBatchIngredients(newIngredients);

		flag = StepDAO.addBatchSteps(newSteps);

		// System.out.println(newRecipe);
		// System.out.println(newIngredients);
		// System.out.println(newSteps);

		return flag;
	}

	/**
	 * To check the validity of the form.
	 * 
	 * @return whether the form is valid.
	 */
	private boolean formValidationCheck() {
		boolean flag = true;

		// check name of recipe is filled in.
		if (this.nameofRecipe.getText().trim().isEmpty()) {
			this.setRecipeNamePromptWarning();
			this.nameofRecipe.requestFocus();
			// new Alert(AlertType.WARNING, "Enter Recipe Name!",
			// ButtonType.OK).showAndWait();
			return false;
		}

		if (!StringUtil.isNumeric(this.amountOfPeople.getText())) {
			this.setNumberPromptWarning();
			this.amountOfPeople.requestFocus();
			return false;
		}

		if (!StringUtil.isNumeric(this.preparingTime.getText())) {
			this.setNumberPromptWarning();
			this.preparingTime.requestFocus();
			return false;
		}

		if (!StringUtil.isNumeric(this.cookingTime.getText())) {
			this.setNumberPromptWarning();
			this.cookingTime.requestFocus();
			return false;
		}

		// when check ImageView, use imagePath.
		if (this.imagePath == null || this.imagePath.isEmpty()) {
			new Alert(AlertType.ERROR, "Choose Image for recipe!", ButtonType.OK).showAndWait();
			return false;
		}

		return flag;
	}

	/**
	 * When the recipeNanme attribute is not valid, set the prompt text warning the
	 * user as well as disables the Save button.
	 */
	protected void setRecipeNamePromptWarning() {
		this.recipeNameWarning.setText("Enter Recipe Name!");
		this.saveRecipe.setDisable(true);
	}

	/**
	 * Restore the prompt text and the disability of the Save button after the user
	 * has entered the valid text.
	 */
	protected void restoreRecipeNamePromptWarning() {
		this.recipeNameWarning.setText("");
		this.saveRecipe.setDisable(false);
	}

	/**
	 * When all the number attributes(peopleServing, Cooking time and
	 * PreparationTime) are not valid, set the prompt text warning the user as well
	 * as disables the Save button.
	 */
	protected void setNumberPromptWarning() {
		invalidInputPrompt.setText("You can only enter numbers in these three blocks and cannot be empty");
		saveRecipe.setDisable(true);
	}

	/**
	 * Restore the prompt text and the disability of the Save button after the user
	 * has entered the valid text.
	 */
	protected void restoreNumberPromptWarning() {
		invalidInputPrompt.setText("");
		saveRecipe.setDisable(false);
	}

}
