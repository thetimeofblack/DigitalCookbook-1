package de.fhluebeck.group3.controller;

import java.awt.Button;
import java.awt.Image;
import java.awt.TextArea;

import com.itextpdf.text.pdf.TextField;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Eason.Hua on 2018/06/05.
 */
public final class AddOrEditRecipeController 
{
	@FXML
	private TextArea nameofRecipe;
	private TextField Description;
	private TextArea amountOfPeople;
	private TextArea preparingTime;
	private TextArea cookingTime;
	private Button choosePicture;
	private Button addIngredient;
	private Button removeIngredient;
	private Button removeStep;
	private Button addStep;
	private Button save;
	private TableView ingredients;
	private TableView steps;
	private ImageView newRecipeImage;
	
	
	
}
