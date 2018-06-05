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
 * 
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	 * Set the basic information of the recipe.
	 */
	public void setSelectedRecipe(Recipe selectedRecipe) throws NullPointerException {

		this.selectedRecipe = selectedRecipe;

		recipeName.setText(selectedRecipe.getRecipeName());

		recipeDescription.setText(StringUtil.textProcessingBeforeOutput(selectedRecipe.getDescription(), 27, 65));

		String uri = MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + selectedRecipe.getImagePath();

		recipeImg.setImage(new Image(new File(uri).toURI().toString(), 80, 80, false, false));

	}

	public Recipe getSelectedRecipe() {
		return selectedRecipe;
	}

}
