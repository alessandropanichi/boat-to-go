package logic.model.dao;

import java.util.List;

import logic.bean.BoatBean;

public interface BoatDAO {

	/**
	 * Find all boats contained in a boat shop.
	 * 
	 * @param boatShopId the id of the boat shop.
	 * @return the list of the boats contained in the boat shop.
	 */
	public List<BoatBean> getAllBoatOfABoatShop(int boatShopId);

	/**
	 * Create a new boat in the boat shop with the specific id.
	 * 
	 * @param boat    the new boat.
	 * @return the id of the new boat.
	 */
	public int createBoat(BoatBean boat,int boatShopId);

	/**
	 * Update the boat with the new data.
	 * 
	 * @param boat - boat to be updated with the new data.
	 * @return true if update go well, false otherwise.
	 */
	public boolean updateBoat(BoatBean boat);


}
