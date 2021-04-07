package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BoatShopBean;
import logic.model.dao.BoatShopDAO;
import logic.model.dao.BoatShopDAOImpl;

public class ManageBoatShopList {

	private List<RentalShop> rentalShops;

	private static ManageBoatShopList instace = null;

	public ManageBoatShopList() {

		this.rentalShops = new ArrayList<>();
	}
	
	public List<BoatShopBean> retrieveBoatShopByOwner(String owner) {

		BoatShopDAO dao = new BoatShopDAOImpl();
		return dao.getAllBoatShopByOwner(owner);
	}
	
	
	public RentalShop getRentalShop(int id) {

		BoatShopDAO dao = new BoatShopDAOImpl();
		BoatShopBean boatShopBean = dao.getBoatShop(id);
		RentalShop boatShop = new BoatShop(boatShopBean);

		this.rentalShops.add(boatShop);

		return boatShop;

	}


	public RentalShop getRentalShop(String name) {

		for (RentalShop rentalShop : this.rentalShops) {

			if (rentalShop.getName().equals(name))
				return rentalShop;

		}
		return null;
	}


	public static synchronized  ManageBoatShopList getInstance() {

		if (ManageBoatShopList.instace == null)

			ManageBoatShopList.instace = new ManageBoatShopList();

		return ManageBoatShopList.instace;

	}
	
	public static void createBoatShop(BoatShopBean bean) {
	
		BoatShopDAO dao = new BoatShopDAOImpl();
		 dao.createBoatShop(bean);
	}
	
	public static void deleteBoatShop(int id){
		BoatShopBean hBean = new BoatShopDAOImpl().getBoatShop(id);
		BoatShop boatShop = new BoatShop(hBean);
		BoatShopDAO dao = new BoatShopDAOImpl();
		dao.deleteBoatShop(boatShop);
	}


	
}
