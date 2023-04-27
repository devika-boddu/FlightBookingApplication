package com.flights.project.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.flights.project.Dao.FlightPackagesDao;
import com.flights.project.Dao.CustomerBookingsDao;
import com.flights.project.Dao.CustomerDao;
import com.flights.project.Exception.CustomerException;
import com.flights.project.Pojo.FlightPackages;
import com.flights.project.Pojo.Customer;

import com.flights.project.Pojo.CustomerFlightBookings;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class FlightCustomerController {
	

	String id = null;
	int count =0 ;
	List<FlightPackages> cartItemsList = new ArrayList<FlightPackages>();
	List<FlightPackages> wishlistItemsList = new ArrayList<FlightPackages>();
	List<FlightPackages> searchedItems = new ArrayList<FlightPackages>();
	List<FlightPackages> sortedItems = new ArrayList<FlightPackages>();
	List<FlightPackages> ordersList = new ArrayList<FlightPackages>();
	List<FlightPackages> cartList = new ArrayList<FlightPackages>();
	List<FlightPackages> wishList = new ArrayList<FlightPackages>();
	List<FlightPackages> orderCartList = new ArrayList<FlightPackages>();
	List<FlightPackages> previousOrderList = new ArrayList<FlightPackages>();
	List<FlightPackages> fpList = new ArrayList<FlightPackages>();
	List<String> seatsList = new ArrayList<String>();

	
	int totalCost = 0;
	int aTotalCost = 0;
	int optionSelected = 0;
	
	@Autowired
	private FlightPackagesDao flightPackDao;
	
	FlightPackages flightPackages;
	
	
	@GetMapping("/products.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("flightPackage") FlightPackages flightPackage, BindingResult result) throws CustomerException {
		Customer currentUser = (Customer) session.getAttribute("currentCustomer");
		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		if(userSelectedOption.contains("Add To Cart")) {
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);		
			
			FlightPackages flightBooked =flightPackDao.getSelectedProduct(tid);
			session.setAttribute("flightBooked", flightBooked);
			return new ModelAndView("seatSelection");
		}
		else if(userSelectedOption.contains("S")) {
			int seatCount =0;
			System.out.println(userSelectedOption);
			FlightPackages flightBooked =(FlightPackages) session.getAttribute("flightBooked");
			String selectedSeat = request.getParameter("userSelectedOption");
			//maintain the selected seat in customerflightbooking table
			
			CustomerBookingsDao cdao = new CustomerBookingsDao();
			cdao.create(new CustomerFlightBookings(currentUser, flightBooked, selectedSeat));
			List<CustomerFlightBookings> getSeats = cdao.getAllSeatsByFlightId(flightBooked);
			for(CustomerFlightBookings i:getSeats) {
				System.out.println(i.getSeat());
			}
			session.setAttribute("getSeats", getSeats);
		
	}
	
		return new ModelAndView("flightDashboard");

}}
