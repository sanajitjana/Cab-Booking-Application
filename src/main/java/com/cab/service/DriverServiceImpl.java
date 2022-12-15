package com.cab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exception.DriverException;
import com.cab.model.Cab;
import com.cab.model.Driver;
import com.cab.model.DriverDTO;

import com.cab.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo driverRepo;

	@Override
	public Driver registerDriver(DriverDTO driverDTO) throws DriverException {

		if (driverDTO == null)
			throw new DriverException("Driver details can't be null");

		Driver driver = driverRepo.findByMobileNumber(driverDTO.getMobileNumber());
		if (driver != null)
			throw new DriverException("Driver already exists with mobile number " + driverDTO.getMobileNumber());

		Driver newDriver = new Driver();
		newDriver.setFirstName(driverDTO.getFirstName());
		newDriver.setLastName(driverDTO.getLastName());
		newDriver.setMobileNumber(driverDTO.getMobileNumber());
		newDriver.setCurrentPosition(driverDTO.getCurrentPosition());

		Cab newCab = new Cab();
		newCab.setCabNumber(driverDTO.getVehicleNumber());
		newDriver.setCab(newCab);

		return driverRepo.save(newDriver);
	}

	@Override
	public List<Driver> getAllDrivers() throws DriverException {

		List<Driver> listOfDrivers = driverRepo.findAll();
		if (listOfDrivers.isEmpty())
			throw new DriverException("No driver found in database!");

		return listOfDrivers;
	}

}
