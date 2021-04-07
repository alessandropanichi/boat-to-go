package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.bean.LoginBean;
import logic.model.LoginController;
import logic.view.LoginView;

public class LoginViewOwnerController extends LoginViewController {

	public LoginViewOwnerController(LoginView view, LoginController model) {
		super(view, model);
		super.view.setLoginHandler(new LoginHandler());
	}

	private class LoginHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			LoginBean loginBean = new LoginBean(view.getUsername(), view.getPassword());

			if (!model.theOwnerExist(loginBean)) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Username or password are wrong.");

				alert.showAndWait();

			} else {

				view.closeWindow();

			}

		}

	}

}
