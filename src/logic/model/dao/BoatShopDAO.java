package logic.model.dao;

import java.util.List;

import logic.bean.BoatShopBean;
import logic.model.BoatShop;

public interface BoatShopDAO {

	/**
	 * Get all the boat shop placed in a city.
	 * 
	 * @param city the name of the city.
	 * @return the list of the boat shop placed in a specific city.
	 */
	public List<BoatShopBean> getAllBoatShopByCity(String city);

	/**
	 * Get all the boat shop of an owner.
	 * 
	 * @param owner the owner of the boat shop.
	 * @return the list of the boat shop of the owner.
	 */
	public List<BoatShopBean> getAllBoatShopByOwner(String owner);

	/**
	 * Find the boat shop with the specific id.
	 * 
	 * @param id the id of the boat shop.
	 * @return the boat shop that match the id.
	 */
	public BoatShopBean getBoatShop(int id);

	/**
	 * Create a new boat shop in the db.
	 * 
	 * @param boat shop the bean with the new data.
	 * @return the id of the boat shop created.
	 */
	public int createBoatShop(BoatShopBean boatShop);

	/**
	 * Update the boat shop with the new data.
	 * 
	 * @param boat shop - the bean that contains new data.
	 * @return true if the boat shop is modified, false otherwise.
	 */
	public boolean updateBoatShop(BoatShopBean boatShop);

	public BoatShop deleteBoatShop(BoatShop boatShop);

}
