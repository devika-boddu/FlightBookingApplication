package com.flights.project.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.flights.project.Exception.CustomerException;
import com.flights.project.Pojo.FlightPackages;



@Component
public class FlightPackagesDao extends DAO {
	
	public void save(FlightPackages flightPackages) throws Exception {
        try {
        	begin();
        	getSession().persist(flightPackages);
        	commit();
        } catch (HibernateException e) {
            rollback();       
            e.printStackTrace();
            
            throw new CustomerException("Exception while creating FlightPackages: " + e.getMessage());
        }
	}
	
	public FlightPackages getSelectedProduct(Integer flightId) throws CustomerException{
		FlightPackages flightPackage = null;  
		
		
		try {
			Query<FlightPackages> query =  getSession().createQuery("FROM FlightPackages where flightId= :flightId", FlightPackages.class);
		    query.setParameter("flightId", flightId);
		    flightPackage = query.uniqueResult();
		    
			System.out.println(flightPackage);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
        return flightPackage;
    }
	
	public void delete(FlightPackages flightPackage) throws CustomerException {
		 try {
	         //delete Flight Package object in the database
	     	begin();
	     	getSession().remove(flightPackage);
	     	commit();
	     	close();
	     } catch (HibernateException e) {
	         rollback();
	         throw new CustomerException("Exception while deleting Package: " + e.getMessage());
	     }
	}
	
	public void update(Integer flightId, String flightName, String flightSource, String flightDestination ,String flightDescription,Integer flightPrice, String flightImageURL ) throws CustomerException {
		FlightPackages flightPackage = getSelectedProduct(flightId);
		
		if(flightPackage!=null) {
			try {
		         //Updates Flight Package object in the database
		     	begin();
		     	flightPackage.setFlightName(flightName);
		     	flightPackage.setSource(flightSource);
		     	flightPackage.setDestination(flightDestination);
		     	flightPackage.setFlightDescription(flightDescription);
		     	flightPackage.setFlightPrice(flightPrice);
		     	flightPackage.setFlightImage(flightImageURL);
		     	
		     	getSession().merge(flightPackage);
		     	commit();
		     	close();
		     } catch (HibernateException e) {
		         rollback();
		         throw new CustomerException("Exception while deleting Package: " + e.getMessage());
		     }
		}
		 
	}
	
	public List<FlightPackages>	 getProducts() throws CustomerException{
			
			List<FlightPackages> myPackages = null;
			try {
				myPackages = getSession().createQuery("FROM FlightPackages", FlightPackages.class).getResultList();
				System.out.println(myPackages);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
	        return myPackages;
	    }
	
	public void updateSeatList(int flightId, List<String> seatsList) throws CustomerException {
		
		FlightPackages flightPackage = getSelectedProduct(flightId);
		if(flightPackage!=null) {
			begin();
			
			flightPackage.setSeats(seatsList);
			getSession().merge(flightPackage);
			commit();
			close();
		}
	}
	
	public List<String> getAllBookedSeats(int flightId) throws CustomerException {
			List<String> seatsList = null;
			FlightPackages flightPackage = getSelectedProduct(flightId);
			try {
				if(flightPackage!=null) {
					begin();
					seatsList = flightPackage.getSeats();
					commit();
					close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return seatsList;
		}

}
