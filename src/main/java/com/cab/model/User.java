package com.cab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "First name can't be null")
	private String firstName;

	@NotNull(message = "Last name can't be null")
	private String lastName;

	@NotNull(message = "Mobile no can't be null")
	@Size(min = 10, max = 10, message = "Mobile no requires 10 digits only")
	@Column(unique = true)
	private String mobileNumber;

	@NotNull(message = "Curerent position can't be null")
	private Integer[] currentPosition = new Integer[2];

	@NotNull(message = "Password can't be null")
	@Size(min = 6, max = 12, message = "Password should contain 6 to 12 character only")
	private String password;
}
