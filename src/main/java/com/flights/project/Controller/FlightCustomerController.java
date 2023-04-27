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
		
		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		Customer currentUser = (Customer) session.getAttribute("currentCustomer");
		if(userSelectedOption.contains("Add To Cart")) {
//			cartList = (List<FlightPackages>) session.getAttribute("travelPackagesCart");
			int cartCount =0;
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);		
			
			FlightPackages flightBooked =flightPackDao.getSelectedProduct(tid);
			session.setAttribute("flightBooked", flightBooked);
			return new ModelAndView("seatSelection");
		}
		else if(userSelectedOption.contains("S")) {
			int seatCount =0;
			System.out.println(userSelectedOption);
			FlightPackagesDao fpdao = new FlightPackagesDao();
			FlightPackages selectedFlight =(FlightPackages) session.getAttribute("flightBooked");
			Integer selectedFlightId = selectedFlight.getFlightId();
			List<String> bookedSeats = fpdao.getAllBookedSeats(selectedFlightId);
			List<String> alreadySelectedSeats = fpdao.getAllBookedSeats(selectedFlightId);
			request.setAttribute("bookedSeats", bookedSeats);
			if(bookedSeats!=null) {
				for(String seat: bookedSeats) {
					if(userSelectedOption.equals(seat)) {
						seatCount = 0;
						request.setAttribute("seatSelectedError", "Seat Already Selected");
					}else {
						seatCount +=1;
					}
				}
				if(seatCount==0) {
					return new ModelAndView("seatSelection");
				}else {
					seatsList.add(userSelectedOption);
					flightPackages = (FlightPackages) session.getAttribute("flightBooked");
					
					FlightPackages fp1 = fpdao.getSelectedProduct(flightPackages.getFlightId());
					fpdao.updateSeatList(flightPackages.getFlightId(), seatsList);
					fpList = fpdao.getProducts();
					for(FlightPackages fp : fpList) {
						System.out.println(fp.getSeats());
					}
					CustomerBookingsDao cbdao = new CustomerBookingsDao();
					cbdao.create(new CustomerFlightBookings(currentUser,fp1, seatsList ));
					
					CustomerDao cdao = new CustomerDao();
					cdao.updateSeatListCustomer(currentUser.getCustomerId(), seatsList);
					session.setAttribute("seatsList", seatsList);
					return new ModelAndView("payment");
				}
			}else {
				seatsList.add(userSelectedOption);
				flightPackages = (FlightPackages) session.getAttribute("flightBooked");
				
				FlightPackages fp1 = fpdao.getSelectedProduct(flightPackages.getFlightId());
				fpdao.updateSeatList(flightPackages.getFlightId(), seatsList);
				fpList = fpdao.getProducts();
				for(FlightPackages fp : fpList) {
					System.out.println(fp.getSeats());
				}
				CustomerBookingsDao cbdao = new CustomerBookingsDao();
				
				
				CustomerDao cdao = new CustomerDao();
				cdao.updateSeatListCustomer(currentUser.getCustomerId(), seatsList);
				session.setAttribute("seatsList", seatsList);
				cbdao.create(new CustomerFlightBookings(currentUser,fp1,seatsList ));
				return new ModelAndView("payment");
			}
			
			
		}
		
		for(String s : seatsList) {
			System.out.println("Seats Booked: "+s);
		}
		return new ModelAndView("flightDashboard");
		
	}
	
	

}
