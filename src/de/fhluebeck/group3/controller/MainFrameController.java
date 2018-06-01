package de.fhluebeck.group3.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import de.fhluebeck.group3.view.Template;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	
	private final String systemImageBasePath = "src/de/fhluebeck/group3/resources/system/";

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
	private RadioButton searchByName;

	@FXML
	private RadioButton searchByIngredient;

	@FXML
	private Label preparationTimeLabel;

	// @FXML
	// private ListView<T> recipesList;

	@FXML
	private Label ServingPeopleLabel;

	@FXML
	private Label cookingTimeLabel;

	@FXML
	private Label DescriptionLabel;

	// @FXML
	// private TableView<?> ingredientTable;

	// @FXML
	// private TableView<?> stepsTable;

	@FXML
	private ImageView recipePic;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO set button images.
		this.setIconImage("src/de/fhluebeck/group3/resources/system/home_out.png", this.homeButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/like_out.png", this.FavButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/logout_out.png", this.LogoutButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/search_out.png", this.searchButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/pdf_out.png", this.exportPDFButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/edit_out.png", this.editRecipeButton);
		this.setIconImage("src/de/fhluebeck/group3/resources/system/delete_out.png", this.deleteRecipeButton);
		//TODO add favorite button.delete_out
		
		// set radio button as a group
		this.searchByIngredient.setToggleGroup(radioGroup);
		this.searchByName.setToggleGroup(radioGroup);
		
		setButtonIconAction(this.homeButton,"home_on.png","home_out.png");
		setButtonIconAction(this.FavButton,"like_on.png","like_out.png");
		setButtonIconAction(this.LogoutButton,"logout_on.png","logout_out.png");
		setButtonIconAction(this.searchButton,"search_on.png","search_out.png");
		setButtonIconAction(this.exportPDFButton,"pdf_on.png","pdf_out.png");
		setButtonIconAction(this.editRecipeButton,"edit_on.png","edit_out.png");
		setButtonIconAction(this.deleteRecipeButton,"delete_on.png","delete_out.png");
		
		//when click the home button, return to the home page.
		this.homeButton.setOnAction((event) ->{
			//TODO show all the recipes.
		});
		
		//when click the home button, return to the home page.
		this.FavButton.setOnAction((event) ->{
			//TODO show favorite recipes of current user.
		});
		
		//when click the home button, return to the home page.
		this.LogoutButton.setOnAction((event) ->{
			// shift the stage to the main Scene.
			try {
				Template.clearCurrentUser();
				Template.replaceSceneContent("./Template.fxml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	/******************************* Private Zone ******************************/

	/**
	 * 
	 * */
	private void setIconImage(String imagePath, Button button) {
		button.setGraphic(new ImageView(new Image(new File(imagePath).toURI().toString(), 35,
				30, false, false)));
	}
	
	private void setButtonIconAction(Button button, String mouseIn, String mouseOut) {
		
		button.setOnMouseEntered((event) -> {
			this.setIconImage(systemImageBasePath + mouseIn, button);
		});
		
		button.setOnMouseExited((event) -> {
			this.setIconImage(systemImageBasePath + mouseOut, button);
		});
		
	}

}
