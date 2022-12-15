package com.cab.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class RideDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;
	private Integer driverId;
	private Integer[] pickup = new Integer[2];
	private Integer[] destination = new Integer[2];
	private LocalDateTime dateTime;

}
