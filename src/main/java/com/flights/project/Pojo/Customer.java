package com.flights.project.Pojo;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.springframework.stereotype.Component;


import com.flights.project.Pojo.CustomerFlightBookings;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private String customerPassword;
	

	@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	 private Set<CustomerFlightBookings> customerFlights = new  HashSet<>();
	
	
	
	
	public Set<CustomerFlightBookings> getCustomerFlights() {
		return customerFlights;
	}
	public void setCustomerFlights(Set<CustomerFlightBookings> customerFlights) {
		this.customerFlights = customerFlights;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", customerEmail=" + customerEmail + ", customerPassword=" + customerPassword
				+ ", customerFlights=" + customerFlights + "]";
	}
	
	
	
}
