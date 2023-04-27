package com.flights.project.Dao;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;

import com.flights.project.Pojo.Customer;
import com.flights.project.Pojo.CustomerFlightBookings;
import com.flights.project.Pojo.FlightPackages;

import org.hibernate.query.Query;




public class CustomerBookingsDao extends DAO{
	public CustomerFlightBookings create(CustomerFlightBookings customerFlightBookings) {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(customerFlightBookings);
        	commit();
        	close();
        	
        	
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            e.printStackTrace();
            
            
        }
        return customerFlightBookings;
    }
	
    
	
	
	
	public List<CustomerFlightBookings> getAllSeatsByFlightId(FlightPackages flightPackages){
		List<CustomerFlightBookings> customerFlightBookings = null;
		
		try {
            //save user object in the database
        	begin();
        	System.out.println("Getting all the CustomerFlightBookings added to the cart: -----");
        	Query<CustomerFlightBookings> query = getSession().createQuery("From CustomerFlightBookings cfb WHERE cfb.flightPackages = :flightPackages",CustomerFlightBookings.class);
        	query.setParameter("flightPackages", flightPackages);
        	customerFlightBookings = query.list();
//        	query.executeUpdate();
        	System.out.println("userProducts from DB"+ customerFlightBookings);
        	commit();
        	close();
        	
        	
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            e.printStackTrace();
            
            
        }
	
		return customerFlightBookings;
	}
	
//	public List<String> updateSeatList(List<String> seatList, FlightPackages flightPackages){
//		List<CustomerFlightBookings> customerFlightBookings = getAllSeatsByFlightId(flightPackages);
//		List<String> updateSeatsList = new ArrayList<>();
//		begin();
//		if(!customerFlightBookings.isEmpty()) {
//			for(CustomerFlightBookings i : customerFlightBookings) {
//				updateSeatsList.addAll(i.getSeats());
//			}
//		}
//		else {
//			updateSeatsList.addAll(seatList);
//		}
//		
//		flightPackages.setSeats(updateSeatsList);
//		getSession().merge(flightPackages);
//		
//		return updateSeatsList;
//		
//		
//	}
	
}	