package com.boatToGo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.CityDateBean;
import logic.bean.BoatShopBean;
import logic.bean.BoatBean;
import logic.model.BookBoatShopController;
import logic.model.RentalShop;
import logic.model.dao.BoatShopDAOImpl;
import logic.util.Util;

/**
 * Servlet implementation class BoatShopView
 */
@WebServlet("/ShopView")
public class ShopView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopView() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String url = "/ShopView.jsp";
		
		String city = request.getParameter("city");
		LocalDate checkIn = Util.parseDate(request.getParameter("checkin"));
		LocalDate checkOut = Util.parseDate(request.getParameter("checkout"));
		int personCount = Integer.parseInt(request.getParameter("personcount"));		
		CityDateBean fields = new CityDateBean(city, checkIn, checkOut, personCount);
		
		int id = Integer.parseInt(request.getParameter("boatShopid"));
		RentalShop boatShop = BookBoatShopController.getInstance().getRentalShop(id );
		BoatShopBean boatShopBean = new BoatShopDAOImpl().getBoatShop(id);
		List<BoatBean> boats = boatShop.getAvailableBoats(fields);
		
		request.getSession().setAttribute("boatShopid", id);
		request.setAttribute("boats", boats);
		request.setAttribute("boatShop", boatShopBean);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
