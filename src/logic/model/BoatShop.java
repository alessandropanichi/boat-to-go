package logic.model;

import java.util.List;

import logic.bean.BoatShopBean;
import logic.bean.BoatBean;
import logic.model.dao.BoatDAO;
import logic.model.dao.BoatDAOImpl;

/**
 *         This class represent a boat shop, the concrete class that extends the
 *         RentalShop class.
 */
public class BoatShop extends RentalShop {

	/**
	 * Constructor of the class, it just call the super constructor.
	 * 
	 * @param name    name of the boat shop.
	 * @param address address of the boat shop.
	 * @param city    city where boat shop is located.
	 */
	public BoatShop(String name, String address, String city, String owner) {

		super(name, address, city, owner);

	}

	/**
	 * Constructor of the class.
	 * 
	 * @param name    name of the boat shop.
	 * @param address address of the boat shop.
	 * @param city    city where boat shop is located.
	 * @param boats   boats available in the boat shop.
	 */
	public BoatShop(String name, String address, String city, List<Boat> boats, String owner) {

		super(name, address, city, boats, owner);

	}

	public BoatShop(BoatShopBean bean) {

		super(bean.getName(), bean.getAddress(), bean.getCity(), bean.getDescription(), bean.getOwner());

		BoatDAO dao = new BoatDAOImpl();
		List<BoatBean> boatBeans = dao.getAllBoatOfABoatShop(bean.getId());

		for (BoatBean boatBean : boatBeans) {
			super.boats.add(new Boat(boatBean));
		}
	}

}