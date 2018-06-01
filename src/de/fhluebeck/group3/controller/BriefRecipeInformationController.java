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

//	/**
//	 * 
//	 * */
//	public void setSelectedRecipe(Recipe selectedRecipe) throws NullPointerException {
//
//		this.selectedRecipe = selectedRecipe;
//
//		recipeName.setText(selectedRecipe.getRecipeName());
//
//		recipeDescription.setText(textProcessingBeforeOutput());
//
//		String uri = "src/de/fhluebeck/group3/resources/recipe/" + selectedRecipe.getImagePath();
//
//		if (Template.getCurrentUser().getFavoriteRecipes() == null) {
//
//			recipeImg.setImage(new Image(new File("src/resources/COOKING_NAVIGATOR.png").toURI().toString(), 80, 80,
//					false, false));
//
//		} else {
//			recipeImg.setImage(new Image(new File("src/resources/" + selectedRecipe.getThumbnail()).toURI().toString(),
//					80, 80, false, false));
//
//		}
//
//	}

	/**
	 * 
	 * */
	private String textProcessingBeforeOutput() {

		String outputText = "";
		try {

			char[] text = selectedRecipe.getDescription().toCharArray();

			int textSize = text.length;

			for (int i = 0; i < textSize; i++) {

				outputText += text[i];

				if (i % 25 == 0 && i != 0) {

					outputText += "-\n";

				}

				if (i > 65) {

					outputText += "...";

					break;

				}

			}

		} catch (NullPointerException exception) {

		} finally {

			return outputText;

		}

	}

}
