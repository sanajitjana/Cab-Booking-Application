package com.cab.model;

import lombok.Data;

@Data
public class UserDTO {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private Integer[] currentPosition = new Integer[2];
	private String password;
}
