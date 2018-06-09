package de.fhluebeck.group3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.pdf.TextField;

import de.fhluebeck.group3.model.Ingredient;
import de.fhluebeck.group3.model.Step;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Eason.Hua on 2018/06/05.
 */
public final class AddOrEditRecipeController implements Initializable{
	
	@FXML
	private TextArea nameofRecipe;
	
	@FXML
	private TextField Description;
	
	@FXML
	private TextArea amountOfPeople;	
	
	@FXML
	private TextArea preparingTime;
	
	@FXML
	private TextArea cookingTime;
	
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
	
	@FXML
	private ImageView newRecipeImage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
}
