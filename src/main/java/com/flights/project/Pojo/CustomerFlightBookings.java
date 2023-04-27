package com.flights.project.Pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomerFlightBookings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="customer_flight_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="flightId")
	private FlightPackages flightPackages;
    
	private String seat;
	
	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}


	public CustomerFlightBookings(Customer customer, FlightPackages flightPackages, String seat) {
		this.customer = customer;
		this.flightPackages = flightPackages;
		this.seat = seat;
	}

	
	public CustomerFlightBookings() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public FlightPackages getFlightPackages() {
		return flightPackages;
	}

	public void setFlightPackages(FlightPackages flightPackages) {
		this.flightPackages = flightPackages;
	}

	
	
}
