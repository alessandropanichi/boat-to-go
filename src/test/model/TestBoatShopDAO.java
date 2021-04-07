package test.model;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import logic.bean.BoatShopBean;
import logic.model.dao.BoatShopDAO;
import logic.model.dao.BoatShopDAOImpl;

public class TestBoatShopDAO {

	private BoatShopDAO boatShopDao;
	private static final String OWNER_1 = "owner";
	
	
	@Test
	public void testGetBoatShop() {
		boatShopDao = new BoatShopDAOImpl();
		BoatShopBean boatShop = boatShopDao.getBoatShop(1);
		Assert.assertEquals(1 , boatShop.getId());
		Assert.assertEquals("Sea Lovers", boatShop.getName());
		Assert.assertEquals("Piazzale De Rossi", boatShop.getAddress());
	
	}
	
	@Test 
	public void testCreateBoatShop() {
		boatShopDao = new BoatShopDAOImpl();
		List<BoatShopBean> boatShops = boatShopDao.getAllBoatShopByOwner(OWNER_1);
		int size = boatShops.size();
		BoatShopBean boatShop = new BoatShopBean("Negozio 1", "Indirizzo 1", "Torvajanica", "Descrizione", OWNER_1 , 2);
		boatShopDao.createBoatShop(boatShop);
		List<BoatShopBean> boatShops2 = boatShopDao.getAllBoatShopByOwner(OWNER_1);
		int size1 = boatShops2.size();
		Assert.assertEquals(size +1, size1);
		
	}
	
	@Test
	public void testGetAllBoatShopByOwner() {
		boatShopDao = new BoatShopDAOImpl();
	
		List<BoatShopBean> boatShops = boatShopDao.getAllBoatShopByOwner(OWNER_1);
		Assert.assertEquals(2, boatShops.size());
	
		
		
	}
}	
