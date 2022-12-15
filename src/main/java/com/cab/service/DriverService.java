package com.cab.service;

import java.util.List;

import com.cab.exception.DriverException;
import com.cab.model.Driver;
import com.cab.model.DriverDTO;

public interface DriverService {

	public Driver registerDriver(DriverDTO driverDTO) throws DriverException;

	public List<Driver> getAllDrivers() throws DriverException;

}
