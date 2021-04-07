package com.boatToGo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookingBean;
import logic.bean.LoginBean;
import logic.model.BookBoatShopController;
import logic.model.LoginController;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/UserProfile.jsp";
		LoginBean loginBean = new LoginBean(LoginController.getInstance().getUsername(), LoginController.getInstance().getPassword());
		List<BookingBean> bookings = BookBoatShopController.getInstance().retrieveBookingOfAnUser(loginBean);
		Collections.reverse(bookings);
		
		request.setAttribute("bookings", bookings);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingID = Integer.parseUnsignedInt(request.getParameter("bookingid"));
		BookBoatShopController.getInstance().deleteBooking(bookingID);
		
		doGet(request, response);
	}

}
