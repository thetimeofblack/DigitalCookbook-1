package de.fhluebeck.group3.view;

import java.io.IOException;

import de.fhluebeck.group3.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * 
 * @author Eason.Hua on 2018/05/30.
 */
public class Template extends Application {

	private static Stage primaryStage;

	private static GridPane rootLayout;

	private static AnchorPane layOutAP;

	private static User currentUser;

	@Override
	public void start(Stage stage) throws Exception {

		Template.primaryStage = stage;

		initRootLayout();
	}

	/**
	 * Initializes the root layout.
	 */
	private void initRootLayout() {
		try {
			// Load root layout from FXML file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Template.class.getResource("./Template.fxml"));
			layOutAP = (AnchorPane) loader.load();

			// Show the scene containing the Root Layout(set as the layout).
			Scene scene = new Scene(layOutAP);

			Template.primaryStage.setScene(scene);
			Template.primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		Template.currentUser = currentUser;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

}
