package com.cab.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exception.DriverException;
import com.cab.exception.UserException;
import com.cab.model.Driver;
import com.cab.model.RideDetails;
import com.cab.model.User;
import com.cab.model.UserDTO;
import com.cab.repository.DriverRepo;
import com.cab.repository.RideDetailsRepo;
import com.cab.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private DriverRepo driverRepo;

	@Autowired
	private RideDetailsRepo rideDetailsRepo;

	// finding user by id
	public User findUser(Integer userId) throws UserException {

		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty())
			throw new UserException("User not found with this id " + userId);
		return user.get();
	}

	// finding driver by id
	public Driver findDriver(Integer driverId) throws DriverException {

		Optional<Driver> driver = driverRepo.findById(driverId);
		if (driver.isEmpty())
			throw new DriverException("User not found with this id " + driverId);
		return driver.get();
	}

	// checking driver only inside 5 KM
	public void checkDriverDistance(Integer userId, Integer driverId) throws DriverException, UserException {

		List<Driver> availableDriver = findRide(userId);
		boolean flag = true;

		for (Driver driver : availableDriver) {
			if (driver.getId() == driverId)
				flag = false;
		}
		if (flag)
			throw new DriverException("The driver hasn't inside 5 KM area!");
	}

	@Override
	public User registerUser(UserDTO userDTO) throws UserException {

		if (userDTO == null)
			throw new UserException("User details can't be null");

		User user = userRepo.findByMobileNumber(userDTO.getMobileNumber());
		if (user != null)
			throw new UserException("User already exists with mobile number " + userDTO.getMobileNumber());

		User newUser = new User();
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setMobileNumber(userDTO.getMobileNumber());
		newUser.setCurrentPosition(userDTO.getCurrentPosition());
		newUser.setPassword(userDTO.getPassword());

		return userRepo.save(newUser);
	}

	@Override
	public List<Driver> findRide(Integer userId) throws DriverException, UserException {

		List<Driver> availableDrivers = new ArrayList<>();

		if (userId == null)
			throw new UserException("User id can't be null");

		User user = findUser(userId); // get user by id from DB
		int x1 = user.getCurrentPosition()[0];
		int y1 = user.getCurrentPosition()[1];

		List<Driver> allDrivers = driverRepo.findAll(); // get all driver from DB

		for (Driver driver : allDrivers) {

			int x2 = driver.getCurrentPosition()[0];
			int y2 = driver.getCurrentPosition()[1];

			double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			if (distance <= 5)
				availableDrivers.add(driver);
		}

		if (availableDrivers.isEmpty())
			throw new DriverException("No driver found within 5 KM");

		return availableDrivers;
	}

	@Override
	public RideDetails bookRide(Integer userId, Integer driverId, Integer x, Integer y)
			throws DriverException, UserException {

		if (driverId == null || userId == null || x == null || y == null)
			throw new UserException("Input date can't be null");

		User user = findUser(userId); // get user by id from DB
		checkDriverDistance(userId, driverId); // checking the driver id who has inside 5 KM
		Driver driver = findDriver(driverId); // get driver by id from DB

		Integer[] arr = new Integer[2];
		arr[0] = x;
		arr[1] = y;

		RideDetails newRide = new RideDetails();
		newRide.setUserId(userId);
		newRide.setDriverId(driverId);
		newRide.setPickup(user.getCurrentPosition());
		newRide.setDestination(arr);
		newRide.setDateTime(LocalDateTime.now());
		driver.setCurrentPosition(arr);

		driverRepo.save(driver);
		return rideDetailsRepo.save(newRide);

	}

	@Override
	public List<User> getAllUsers() throws UserException {

		List<User> listOfUsers = userRepo.findAll();
		if (listOfUsers.isEmpty())
			throw new UserException("No user found in database!");

		return listOfUsers;
	}
}
