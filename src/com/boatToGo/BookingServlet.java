package com.boatToGo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookingBean;
import logic.bean.CityDateBean;
import logic.bean.BoatShopBean;
import logic.bean.LoginBean;
import logic.bean.BoatBean;
import logic.model.BookBoatShopController;
import logic.model.LoginController;
import logic.model.Person;
import logic.model.RentalShop;
import logic.model.dao.BoatShopDAOImpl;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/booking.jsp";
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, Integer> parsedValues = new HashMap<>();
		int id = (int) request.getSession().getAttribute("boatShopid");
		
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String [] parameterValues = request.getParameterValues(parameterName);
			
			for(int i = 0; i < parameterValues.length; i++) {
				parsedValues.put(parameterName, Integer.parseInt(parameterValues[i]));
			}
			
		}
		
		CityDateBean fields = (CityDateBean) request.getSession().getAttribute("fields");
		int personCount = fields.getPersonCount();
		int totalSeats = 0;
		
		for(Map.Entry<String, Integer> entry : parsedValues.entrySet())  {
			int seats = Integer.parseInt(entry.getKey());
			totalSeats += seats*entry.getValue();
		}
		
		RentalShop boatShop = BookBoatShopController.getInstance().getRentalShop(id);
		List<BoatBean> boats = boatShop.getAvailableBoats(fields);
		BoatShopBean boatShopBean = new BoatShopDAOImpl().getBoatShop(id);
		
		if(totalSeats < personCount || !checkAvailability(parsedValues, boats)) {
			url = "/ShopView.jsp";
			request.setAttribute("error", "Invalid combination.");
		}
		
		request.getSession().setAttribute("boats", boats);
		request.setAttribute("boatShop", boatShopBean);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/UserProfile.jsp";
		CityDateBean fields = (CityDateBean) request.getSession().getAttribute("fields");
		int personCount = fields.getPersonCount();
		List<Person> peopleList = new ArrayList<>();
		
		for(int i = 1; i <= personCount; i++) {
			Person person = retrievePersonData(request, i);
			peopleList.add(person);
		}
		
		int boatShopID = (int) request.getSession().getAttribute("boatShopid");
		RentalShop boatShop = BookBoatShopController.getInstance().getRentalShop(boatShopID);
		
		List<BoatBean> boatBeans = (List<BoatBean>)request.getSession().getAttribute("boats");
		
		BookBoatShopController.getInstance().createBooking(fields, peopleList, boatBeans, boatShop);
		
		LoginBean loginBean = new LoginBean(LoginController.getInstance().getUsername(), LoginController.getInstance().getPassword());
		List<BookingBean> bookings = BookBoatShopController.getInstance().retrieveBookingOfAnUser(loginBean);
		Collections.reverse(bookings);
		
		request.setAttribute("bookings", bookings);		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	/**
	 * Find data from request and return a Person bean.
	 * @param request 	HTTP request
	 * @param i			the number of the person in the list.
	 * @return			a person with data found in the request
	 */
	private Person retrievePersonData(HttpServletRequest request, int i) {
		String paramName = String.valueOf(i) + "fname";
		String paramLName = String.valueOf(i) + "lname";
		String paramCF = String.valueOf(i) + "cf";
		
		String fName = request.getParameter(paramName);
		String lName = request.getParameter(paramLName);
		String cf = request.getParameter(paramCF);
		
		return new Person(fName, lName, cf);
	}

	/**
	 * return true if the boat choices are available, false otherwise.
	 * 
	 * @param inputValues		Map that contains (key: number of seats, value: number of boat choices by user).
	 * @param availableBoats	List of boats available.
	 * @return					true if boats are available, false otherwise.
	 */
	private boolean checkAvailability(Map<String, Integer> inputValues, List<BoatBean> availableBoats) {
		
		for(Map.Entry<String, Integer> entry : inputValues.entrySet()) {
			int seats = Integer.parseInt(entry.getKey());
			for(BoatBean boat : availableBoats) {
				if(seats == boat.getSeats() && entry.getValue() > boat.getAvailability()) {
					return false;
				} else if(seats == boat.getSeats() && entry.getValue() <= boat.getAvailability()){
					boat.setBoatChoise(String.valueOf(entry.getValue()));
				}
			}
		}
		return true;
		
	}

}
