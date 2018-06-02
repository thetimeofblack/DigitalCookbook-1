package de.fhluebeck.group3.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.view.Template;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * */
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

		recipeDescription.setText(textProcessingBeforeOutput());

		String uri = MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + selectedRecipe.getImagePath();
		
		recipeImg.setImage(new Image(new File(uri).toURI().toString(), 80, 80, false, false));
		

	}

	/**
	 * Handle the text before output. This function will make the text more
	 * beautiful and readable and set the text suitable for presentation.
	 * 
	 * @return formatted string.
	 */
	private String textProcessingBeforeOutput() {

		StringBuilder stringBuilder = new StringBuilder();
		try {

			char[] text = selectedRecipe.getDescription().toCharArray();

			int textSize = text.length;

			for (int i = 0; i < textSize; i++) {

				stringBuilder.append(text[i]);

				if (i % 30 == 0 && i != 0) {

					stringBuilder.append("-\n");

				}

				if (i > 65) {

					stringBuilder.append("...");

					break;

				}

			}

		} catch (NullPointerException exception) {

		} finally {

			return stringBuilder.toString();

		}

	}

}
