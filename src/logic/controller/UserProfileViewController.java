package logic.controller;

import java.util.Collections;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import logic.Main;
import logic.bean.BookingBean;
import logic.bean.LoginBean;
import logic.model.BookBoatShopController;
import logic.model.LoginController;
import logic.view.UserProfileView;

/**
 * Controller MVC of the UserProfileView.
 */
public class UserProfileViewController {
	
	/**
	 * Reference of the view.
	 */
	private UserProfileView view;
	
	/**
	 * Reference of the model.
	 */
	private BookBoatShopController model;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param view		the view.
	 * @param model		the model.
	 * @param loginBean	bean that contain input of the user.
	 */
	public UserProfileViewController(UserProfileView view, BookBoatShopController model) {
		
		this.view = view;
		this.model = model;
		
		if(LoginController.getInstance().isLogged())
			view.setUsername(LoginController.getInstance().getUsername());
		
		LoginBean loginBean = new LoginBean(LoginController.getInstance().getUsername(), LoginController.getInstance().getPassword());
		List<BookingBean> bookings = this.model.retrieveBookingOfAnUser(loginBean);
		Collections.reverse(bookings);
		this.view.setBookings(bookings, new DeleteHandler(), new ResubmitHandler());
		
		this.view.addMainMenuHandler(new MainMenuHandler());
		
	}
	
	private class ResubmitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int id = Integer.parseInt(((Control)event.getSource()).getId());	// The id of the booking selected.
			model.resubmitBooking(id);
			new UserProfileViewController(Main.getInstance().getUserProfileView(), BookBoatShopController.getInstance());
			
		}
		
	}
	
	private class DeleteHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int id = Integer.parseInt(((Control)event.getSource()).getId());	// The id of the booking selected.
			
			model.deleteBooking(id);
			
			new UserProfileViewController(Main.getInstance().getUserProfileView(), BookBoatShopController.getInstance());
			
		}
		
	}
	
	/**
	 * This class implements the EventHandler interface for the MainMenu Button.
	 */
	private class MainMenuHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				Main.getInstance().changeToMainMenuView();
				new MainMenuController(Main.getInstance().getMainMenuView(), BookBoatShopController.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
				
	}

}
