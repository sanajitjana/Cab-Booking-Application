package com.cab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.exception.DriverException;

import com.cab.model.Driver;
import com.cab.model.DriverDTO;
import com.cab.service.DriverService;

@RestController
@RequestMapping("/masaicab/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriver(@Valid @RequestBody DriverDTO driverDTO) throws DriverException {
		return new ResponseEntity<Driver>(driverService.registerDriver(driverDTO), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Driver>> getAllDrivers() throws DriverException {
		return new ResponseEntity<List<Driver>>(driverService.getAllDrivers(), HttpStatus.OK);
	}

}
