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
	private FlightPackages fightPackages;
    
	private List<String> seats;
	
	public CustomerFlightBookings(Customer customer, FlightPackages fightPackages, List<String> seats) {
		this.customer = customer;
		this.fightPackages = fightPackages;
		this.seats = seats;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public CustomerFlightBookings() {
		super();
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

	public FlightPackages getFightPackage() {
		return fightPackages;
	}

	public void setFightPackage(FlightPackages fightPackages) {
		this.fightPackages = fightPackages;
	}
	
}
