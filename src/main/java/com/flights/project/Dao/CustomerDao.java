package com.flights.project.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.flights.project.Pojo.Customer;
import com.flights.project.Pojo.FlightPackages;
import com.flights.project.Exception.CustomerException;


@Component
public class CustomerDao extends DAO {
	
		public void save(Customer customer) throws Exception {
	        try {
	            //save Customer object in the database
	        	begin();
	        	getSession().persist(customer);
	        	commit();
	        } catch (HibernateException e) {
	            rollback();       
	            e.printStackTrace();
	            
	            throw new CustomerException("Exception while creating Customer: " + e.getMessage());
	    }
	 }
	 
	 public Customer getCustomer(String customerEmail) throws CustomerException{
	        
		 	try {
		 		Query<Customer> query =  getSession().createQuery("FROM Customer where customerEmail= :customerEmail", Customer.class);
			    query.setParameter("customerEmail", customerEmail);
			    Customer customer = query.uniqueResult();
			    System.out.println(customer);
		        return customer;
		 	}catch (HibernateException e) {
	             rollback();
	             throw new CustomerException("Could not get user ", e);
	         }
		 	
	    }
	 public Customer getCustomerByUserId(int customerId) throws CustomerException{
	        
		 	try {
		 		Query<Customer> query =  getSession().createQuery("FROM Customer where customerId= :customerId", Customer.class);
			    query.setParameter("customerId", customerId);
			    Customer customer = query.uniqueResult();
			    System.out.println(customer);
		        return customer;
		 	}catch (HibernateException e) {
	             rollback();
	             throw new CustomerException("Could not get user ", e);
	         }
		 	
	    }
	 	
	 public Customer updateUserPassword(String customerEmail, String newPassword) throws CustomerException{
		 Customer customer = getCustomer(customerEmail);
		 
		 if (customer != null) {
		        
			 	begin();
			 	customer.setCustomerPassword(newPassword); // Replace newPassword with the actual new password value
		       getSession().merge(customer);
		       System.out.println(customer);
		        commit();
		       
		    }
		 return customer;
		 
	 }
	 
	 public void delete(Customer customer) throws CustomerException {
		 try {
	         //delete user object in the database
	     	begin();
	     	getSession().remove(customer);
	     	commit();
	     	close();
	     } catch (HibernateException e) {
	         rollback();
	         throw new CustomerException("Exception while deleting Customer: " + e.getMessage());
	     }
	}
	 public void updateSeatListCustomer(int customerId, List<String> seatsList) throws CustomerException {
			
			Customer customer = getCustomerByUserId(customerId);
			if(customer!=null) {
			try {
				begin();
				
				customer.setSeats(seatsList);
				getSession().merge(customer);
				commit();
				close();
			}
			catch (HibernateException e) {
		         rollback();
		         throw new CustomerException("Exception while deleting Customer: " + e.getMessage());
		     }
			}
			
		}

}
