package com.barath.app.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Customer {
	
	private Long customerId;
	
	private String customerName;
	
	private CustomerGender customerGender;
	
	
	public enum CustomerGender{
		MALE,
		FEMALE
	}
	

}
