package logic.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *         Extends this view if you need the main top bar of the page. This view
 *         provides the title and the login button.
 */
public abstract class MainView extends Application {

	protected BorderPane borderPane;

	private HBox hBoxLogin;

	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private Button btnLoginAsOwner = new Button("Login as Owner");
	private Button btnUserProfile = new Button("User Profile");

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Boat To Go");

		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));

		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);

		Text title = new Text("Boat To Go");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		hBoxTitle.getChildren().add(title);
		hBoxLogin = new HBox(10);
		hBoxLogin.setAlignment(Pos.CENTER_RIGHT);
		hBoxLogin.getChildren().addAll(btnLogin, btnSignIn, btnLoginAsOwner);

		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		hBoxTop.getChildren().addAll(hBoxTitle, hBoxLogin);

		borderPane.setTop(hBoxTop);

		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void loggedView(String username) {

		this.hBoxLogin.getChildren().clear();
		this.hBoxLogin.getChildren().addAll(new Text(username), this.btnUserProfile);

	}

	public void addUserProfileHandler(EventHandler<ActionEvent> handler) {

		this.btnUserProfile.setOnAction(handler);

	}

	public void addLoginListener(EventHandler<ActionEvent> loginHandler) {

		this.btnLogin.setOnAction(loginHandler);

	}

	public void addSignInListener(EventHandler<ActionEvent> signInHandler) {

		this.btnSignIn.setOnAction(signInHandler);

	}

	public void addLogInAsOwnerListener(EventHandler<ActionEvent> logInAsOwnerHandler) {

		this.btnLoginAsOwner.setOnAction(logInAsOwnerHandler);
	}

}
