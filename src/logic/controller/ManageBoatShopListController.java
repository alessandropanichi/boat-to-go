package logic.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import logic.Main;
import logic.bean.BoatShopBean;

import logic.model.BookBoatShopController;

import logic.model.ManageBoatShopList;
import logic.view.ManageBoatShopListView;



public class ManageBoatShopListController {

	private ManageBoatShopListView view;

	private ManageBoatShopList model;

	private String owner;

	public ManageBoatShopListController(ManageBoatShopListView view, ManageBoatShopList model, String owner) {

		this.view = view;
		this.model = model;
		this.owner = owner;

		this.view.addExitHandler(new ExitHandler());
		this.view.addCreateBoatShopHandler(new CreateHotelHandler());
		this.view.addProfileHandelr(new ProfileHandelr());
		
		/* Set the data found to the view. */
		this.view.populateView(this.model.retrieveBoatShopByOwner(this.owner), new MoreInformationHandler(),
				new DeleteHandler());

	}

	public class ExitHandler implements EventHandler<ActionEvent> {

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
	public class ProfileHandelr implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {

			try {
				new UserProfileViewController(Main.getInstance().getUserProfileView(),
						BookBoatShopController.getInstance());
				Main.getInstance().changeToUserProfileView();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

	private class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			try {

			
				Main.getInstance().changeToManageBoatListView();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	

	private class CreateHotelHandler implements EventHandler<ActionEvent>{
		BoatShopBean bean = new BoatShopBean("Boat Paradise", "Viale Totti 10", "Roma", "owner", owner, 4);
		@Override
		public void handle(ActionEvent event) {
			ManageBoatShopList.createBoatShop(bean);
			
			 new ManageBoatShopListController(view, model, owner);

			
		}
	}
	
	private class DeleteHandler implements EventHandler<ActionEvent>{
	
		@Override
		public void handle(ActionEvent event) {
			 throw new UnsupportedOperationException();
		}
		
	}
}


