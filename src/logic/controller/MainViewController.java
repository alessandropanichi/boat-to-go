package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import logic.Main;
import logic.model.BookBoatShopController;
import logic.model.LoginController;
import logic.view.LoginView;
import logic.view.MainView;

/**
 *         MVC Controller for the MainView.
 */
public abstract class MainViewController {

	/**
	 * Reference to the view
	 */
	protected MainView view;

	/**
	 * Reference to the model
	 */
	protected BookBoatShopController model;

	/**
	 * Constructor of the class, it maintains the link with the view and the model.
	 * 
	 * @param view  the view.
	 * @param model the model.
	 */
	public MainViewController(MainView view, BookBoatShopController model) {

		this.view = view;
		this.model = model;

		if (LoginController.getInstance().isLogged())
			this.view.loggedView(LoginController.getInstance().getUsername());

		this.view.addLoginListener(new LoginHandler());
		this.view.addUserProfileHandler(new UserProfileHandler());

	}

	/**
	 * Constructor, it is implemented by BoatShopViewController.
	 * 
	 * @param view
	 */
	public MainViewController(MainView view) {

		this.view = view;

		if (LoginController.getInstance().isLogged())
			this.view.loggedView(LoginController.getInstance().getUsername());

		this.view.addLoginListener(new LoginHandler());
		this.view.addUserProfileHandler(new UserProfileHandler());

	}

	/**
	 *         This class implements the EventHandler interface for the login
	 *         button.
	 */
	private class LoginHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			Stage stage = new Stage();
			try {
				LoginView window = new LoginView();
				new LoginViewController(window, LoginController.getInstance());
				window.start(stage);
				if (LoginController.getInstance().isLogged())
					view.loggedView(LoginController.getInstance().getUsername());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	/**
	 *         This class provide the implementation of the EventHandler interface
	 *         for the user profile button.
	 */
	private class UserProfileHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if (LoginController.getInstance().isLogged()) {
				
				new UserProfileViewController(Main.getInstance().getUserProfileView(),
							BookBoatShopController.getInstance());
				Main.getInstance().changeToUserProfileView();				

			}

		}

	}

}
