package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;

import logic.model.LoginController;
import logic.model.ManageBoatShopList;
import logic.model.ManageBoatList;

import logic.view.ManageBoatListView;

public class ManageBoatListController {

	private ManageBoatList model;

	private ManageBoatListView view;

	public ManageBoatListController(ManageBoatListView view, ManageBoatList model) {

		this.view = view;
		this.model = model;

		this.view.addBackHandler(new BackHandler());
		this.view.populateView(this.model.retrieveBoats(), new MoreInformationHandler(), new DeleteHandler());

	}

	public class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			try {
				Main.getInstance().changeToManageBoatShopListView();

				new ManageBoatShopListController(Main.getInstance().getManageBoatShopListView(),
						ManageBoatShopList.getInstance(), LoginController.getInstance().getUsername());

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

	private class DeleteHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			throw new UnsupportedOperationException();



		}
	}

}