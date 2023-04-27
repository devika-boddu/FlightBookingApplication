package com.flights.project.Dao;

import org.hibernate.HibernateException;

import com.flights.project.Pojo.CustomerFlightBookings;



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
}	