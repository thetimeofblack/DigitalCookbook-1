package de.fhluebeck.group3.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import de.fhluebeck.group3.model.Ingredient;
import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.model.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Eason.Hua on 2018/06/05.
 */
public final class AddOrEditRecipeController implements Initializable {

	@FXML
	private TextArea nameofRecipe;

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
	private Button saveRecipe;

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
	
	protected ObservableList<Step> stepData;

	protected ObservableList<Ingredient> ingredientData;

	@FXML
	private ImageView newRecipeImage;

	private Recipe editedRecipe;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.initialSetAllElementProperities();

	}

	/**
	 * 
	 * */
	private void initialSetAllElementProperities() {

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
	 * 
	 * */
	private void fillInBlanksOfRecipe() {
		
		defaultImageView.setText("");
		newRecipeImage.setImage(new Image(new File(MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + editedRecipe.getImagePath()).toURI().toString(), 100, 100, false, false));
		// print basic information of recipe.
		description.setText(editedRecipe.getDescription());
		nameofRecipe.setText(editedRecipe.getRecipeName());
		amountOfPeople.setText(new Integer(editedRecipe.getAvailablePeople()).toString());
		preparingTime.setText(new Integer(editedRecipe.getPreparationTime()).toString());
		cookingTime.setText(new Integer(editedRecipe.getCookingTime()).toString());
		
		// Add steps into the step table.
		stepData = FXCollections.observableArrayList();
		for (Step step : editedRecipe.getSteps()) {
			stepData.add(step);
		}
		steps.setItems(stepData);
		stepOrderColumn.setCellValueFactory(cellData -> cellData.getValue().getIntegerProperityStepOrder());
		stepContentColumn
				.setCellValueFactory(cellData -> cellData.getValue().getStringProperityStepContent());
		// Add ingredients into the ingredient table.
		ingredientData = FXCollections.observableArrayList();
		for (Ingredient ingredient : editedRecipe.getIngredients()) {
			ingredientData.add(ingredient);
		}
		ingredients.setItems(ingredientData);
		ingredientNameColumn
				.setCellValueFactory(cellData -> cellData.getValue().getStringProperityIngredientName());
		ingredientQuantityColumn
				.setCellValueFactory(cellData -> cellData.getValue().getDoubleProperityQuantity());
		ingredientUnitColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperityUnit());
		ingredientCommentColumn
				.setCellValueFactory(cellData -> cellData.getValue().getStringProperityComment());
	}

}
