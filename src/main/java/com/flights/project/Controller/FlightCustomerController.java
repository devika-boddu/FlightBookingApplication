package com.flights.project.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	List<FlightPackages> searchedFlightpackages = new ArrayList<FlightPackages>();
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
	List<CustomerFlightBookings> getSeats;
	List<String> seats = new ArrayList<>();
	
	@PostMapping("/products.htm")
	public String handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("flightPackage") FlightPackages flightPackage, BindingResult result) throws CustomerException {
		Customer currentUser = (Customer) session.getAttribute("currentCustomer");
		CustomerBookingsDao cdao = new CustomerBookingsDao();
		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		if(userSelectedOption.contains("Add To Cart")) {
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);		
			
			FlightPackages flightBooked =flightPackDao.getSelectedProduct(tid);
			session.setAttribute("flightBooked", flightBooked);
			getSeats = cdao.getAllSeatsByFlightId(flightBooked);
			for(CustomerFlightBookings i: getSeats) {
				seats.add(i.getSeat());
			}
			System.out.println("SeatsList------"+ seats);
			session.setAttribute("seats", seats);
			return "seatSelection";
		}
		else if(userSelectedOption.contains("Seat")) {
			int seatCount =0;
			System.out.println(userSelectedOption);
			FlightPackages flightBooked =(FlightPackages) session.getAttribute("flightBooked");
			String selectedSeat = request.getParameter("userSelectedOption");
			//maintain the selected seat in customerflightbooking table
			
			
			cdao.create(new CustomerFlightBookings(currentUser, flightBooked, selectedSeat));
			getSeats = cdao.getAllSeatsByFlightId(flightBooked);
			System.out.println("FlightId-----"+flightBooked.getFlightId());
			for(CustomerFlightBookings i: getSeats) {
				System.out.println("GetSeats------"+i.getSeat());
			}
			System.out.println("GetSeatsList------"+getSeats);
			
			return "seatSelection";
		
		}
		else if (userSelectedOption.contains("Search")) {
			String from = request.getParameter("fromEntered");
			String to = request.getParameter("toEntered");
			System.out.println("From:" +from);
			System.out.println("To:" +to);
			searchedFlightpackages=flightPackDao.getAllSearchedFlights(from, to);
			session.setAttribute("searchedFlightpackages", searchedFlightpackages);
			for(FlightPackages i : searchedFlightpackages) {
				System.out.println("SearchedFlights: "+i.getSource()+"------"+i.getDestination());
			}
			optionSelected=1;
			
			
		}else if (userSelectedOption.contains("Sort")) {
			sortedItems=flightPackDao.getSortedFlights();
			optionSelected=2;
		}
//		session.setAttribute("seats", seats);
		session.setAttribute("optionSelected", optionSelected);
		
		return "dashboard1";

}}
