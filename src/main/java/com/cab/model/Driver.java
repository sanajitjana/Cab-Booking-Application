package com.cab.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Driver {

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

	@OneToOne(cascade = CascadeType.ALL)
	private Cab cab;

}
