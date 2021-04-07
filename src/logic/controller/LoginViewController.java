package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.bean.LoginBean;
import logic.model.LoginController;
import logic.view.LoginView;

/**
 * MVC controller of LoginView.
 */
public class LoginViewController {

	protected LoginView view;

	protected LoginController model;

	public LoginViewController(LoginView view, LoginController model) {

		this.view = view;
		this.model = model;

		this.view.setLoginHandler(new LoginHandler());
		this.view.setCancelHandler(new CancelHandler());

	}

	/**
	 *         This class implements the EventHandler interface for the login
	 *         button.
	 */
	private class LoginHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			LoginBean loginBean = new LoginBean(view.getUsername(), view.getPassword());

			if (!model.theUserExist(loginBean)) {

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

	/**
	 *         This class implements the EventHandler interface for the cancel
	 *         button.
	 */
	private class CancelHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			view.closeWindow();

		}

	}

}
