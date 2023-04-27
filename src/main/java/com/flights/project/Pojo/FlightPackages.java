package com.flights.project.Pojo;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class FlightPackages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer flightId;
	private String flightName;
	private String source;
	private String destination;
	private String flightDescription;
	private Integer flightPrice;
	private String flightImage;
	private List<String> seats;
	
	@OneToMany(mappedBy = "fightPackages", cascade = CascadeType.ALL)
	private Set<CustomerFlightBookings> customers = new HashSet<CustomerFlightBookings>();
	
	public Set<CustomerFlightBookings> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<CustomerFlightBookings> customers) {
		this.customers = customers;
	}


	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlightDescription() {
		return flightDescription;
	}
	public void setFlightDescription(String flightDescription) {
		this.flightDescription = flightDescription;
	}
	public Integer getFlightPrice() {
		return flightPrice;
	}
	public void setFlightPrice(Integer flightPrice) {
		this.flightPrice = flightPrice;
	}
	public String getFlightImage() {
		return flightImage;
	}
	public void setFlightImage(String flightImage) {
		this.flightImage = flightImage;
	}
	
	

}
