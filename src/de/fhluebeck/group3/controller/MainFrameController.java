package de.fhluebeck.group3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
	
	@FXML
	private Button homeButton;
	
	@FXML
	private Button FavButton;
	
	@FXML
	private Button LogoutButton;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private Button exportPDFButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//set button images.
		ImageView imageView = new ImageView(new Image("/de/fhluebeck/group3/resources/home.jpeg"));
		imageView.setFitWidth(homeButton.getPrefWidth()*0.75);
		imageView.setFitHeight(homeButton.getPrefHeight());
		homeButton.setGraphic(imageView);

	}

}
