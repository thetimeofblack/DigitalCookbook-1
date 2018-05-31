package de.fhluebeck.group3.view;


import de.fhluebeck.group3.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * 
 * @author Eason.Hua on 2018/05/30.
 */
public class Template extends Application {

	private static Stage primaryStage;

//	private static GridPane rootLayout;

//	private static AnchorPane layOutAP;

	/**
	 * Current login user, when the user login, we load all the relevant informations into the user.
	 * */
	private static User currentUser;

	@Override
	public void start(Stage stage) throws Exception {

		Template.primaryStage = stage;

		initLayout();
	}

	/**
	 * Initializes the root layout.
	 */
	private void initLayout() {
		try {
//			// Load root layout from FXML file.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Template.class.getResource("./Template.fxml"));
//			layOutAP = (AnchorPane) loader.load();
//
//			// Show the scene containing the Root Layout (set as the layout).
//			Scene scene = new Scene(layOutAP);
//
//			Template.primaryStage.setScene(scene);
//			replaceSceneContent("./Template.fxml");
			replaceSceneContent("./MainRecipeFrame.fxml");
			Template.primaryStage.show();

		} catch (Exception e) {
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
	
	/**
	 * The common function for scene change in the root stage. This is quite useful and effieient 
	 * when we want to change the scene.
	 * 
	 * @author huayichen
	 * */
	public static Parent replaceSceneContent(String fxml) throws Exception{
		Parent page = (Parent) FXMLLoader.load(Template.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = primaryStage.getScene();
        if (scene == null) {	//at the first time, create a new scene.
            scene = new Scene(page);
//            scene.getStylesheets().add(Template.class.getResource("demo.css").toExternalForm());
            primaryStage.setScene(scene);
        } else {
        	primaryStage.getScene().setRoot(page);
        }
        primaryStage.sizeToScene();
        return page;
	}

}
