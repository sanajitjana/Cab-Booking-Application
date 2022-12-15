package com.cab.model;

import lombok.Data;

@Data
public class DriverDTO {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private Integer[] currentPosition = new Integer[2];	
	private String vehicleNumber;
}
