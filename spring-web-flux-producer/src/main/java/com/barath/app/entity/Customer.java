package com.barath.app.entity;


import javax.persistence.*;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	@Id
	@Column(name="CUSTOMER_ID")
	private Long customerId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Enumerated(EnumType.STRING)
	@Column(name="CUSTOMER_GENDER")
	private CustomerGender customerGender;
	
	
	public enum CustomerGender{
		MALE,
		FEMALE
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public CustomerGender getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(CustomerGender customerGender) {
		this.customerGender = customerGender;
	}

	public Customer(Long customerId, String customerName, CustomerGender customerGender) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerGender = customerGender;
	}

	public Customer() {
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				", customerGender=" + customerGender +
				'}';
	}
}
