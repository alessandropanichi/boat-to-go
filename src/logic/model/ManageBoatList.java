 package logic.model;

import java.util.ArrayList;
import java.util.List;


import logic.bean.BoatBean;

import logic.model.dao.BoatDAO;
import logic.model.dao.BoatDAOImpl;

public class ManageBoatList {

	private static ManageBoatList instace = null;
	private List<BoatBean> boats;

	public ManageBoatList() {

		this.boats = new ArrayList<>();
	
	}
	
	
	public List<BoatBean> retrieveBoat(int id) {

		BoatDAO dao = new BoatDAOImpl();
		return dao.getAllBoatOfABoatShop(id);
	}
	
	public List<BoatBean> retrieveBoats() {

		return new ArrayList<>(this.boats);

	}

	public  List<BoatBean>  retrieveBoats(int boatShopId) {
		BoatDAO dao = new BoatDAOImpl();
		return dao.getAllBoatOfABoatShop(boatShopId);
		
		
	}
	
	
	
	
	public static synchronized  ManageBoatList getInstance() {

		if (ManageBoatList.instace == null)

			ManageBoatList.instace = new ManageBoatList();

		return ManageBoatList.instace;

	}
	
	public static void createBoat(int id) {
		BoatBean bean1 =new BoatBean("Boat", 2, 15, 1, 4);
		
		BoatDAO dao = new BoatDAOImpl();
		dao.createBoat(bean1, id);
	}

}
