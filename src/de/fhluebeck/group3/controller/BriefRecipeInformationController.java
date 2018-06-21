package de.fhluebeck.group3.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.util.StringUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The controller of the BriefRecipeInformation view -
 * BriefRecipeInformation.fxml. This controller includes basic view loading
 * functions and logical functions for basic operations on the view. The
 * functions in this controller is called directly by the MainFrameController.
 * 
 * @author Hua Yichen on 2018.06.02.
 */
public final class BriefRecipeInformationController implements Initializable {

	@FXML
	private ImageView recipeImg;

	@FXML
	private Label recipeName;

	@FXML
	private Label recipeDescription;

	private Recipe selectedRecipe;

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
		/* do nothing here */
	}

	/**
	 * Set the basic information of the recipe.
	 * 
	 * @param selectedRecipe selected Recipe
	 */
	public void setSelectedRecipe(Recipe selectedRecipe) throws NullPointerException {

		this.selectedRecipe = selectedRecipe;

		recipeName.setText(selectedRecipe.getRecipeName());

		recipeDescription.setText(StringUtil.textProcessingBeforeOutput(selectedRecipe.getDescription(), 27, 65));

		String uri = MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + selectedRecipe.getImagePath();

		recipeImg.setImage(new Image(new File(uri).toURI().toString(), 80, 80, false, false));

	}

	/**
	 * @return the selectedRecipe
	 */
	public Recipe getSelectedRecipe() {
		return selectedRecipe;
	}

}
