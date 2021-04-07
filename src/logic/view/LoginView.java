package logic.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginView extends Application {

	private Button btnLogin = new Button("Login");

	private Button btnCancel = new Button("Cancel");

	private TextField txtFieldUsername = new TextField();

	private PasswordField txtFieldPassword = new PasswordField();

	private Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Login");
		window.setWidth(300);
		window.setHeight(200);

		Label lblUsername = new Label("Username");
		Label lblpassword = new Label("Password");

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(lblUsername, 0, 0);
		gridPane.add(txtFieldUsername, 1, 0);
		gridPane.add(lblpassword, 0, 1);
		gridPane.add(txtFieldPassword, 1, 1);
		gridPane.add(btnLogin, 0, 2);
		gridPane.add(btnCancel, 1, 2);

		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.showAndWait();

	}

	public void closeWindow() {

		window.close();

	}

	public void setLoginHandler(EventHandler<ActionEvent> loginHandler) {

		btnLogin.setOnAction(loginHandler);

	}

	public void setCancelHandler(EventHandler<ActionEvent> cancelHandler) {

		btnCancel.setOnAction(cancelHandler);

	}

	public String getUsername() {

		return txtFieldUsername.getText();

	}

	public String getPassword() {

		return txtFieldPassword.getText();

	}

}
