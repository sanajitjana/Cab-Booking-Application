package com.cab.service;

import java.util.List;

import com.cab.exception.DriverException;
import com.cab.exception.UserException;
import com.cab.model.Driver;
import com.cab.model.RideDetails;
import com.cab.model.User;
import com.cab.model.UserDTO;

public interface UserService {

	public User registerUser(UserDTO userDTO) throws UserException;

	public List<Driver> findRide(Integer userId) throws DriverException, UserException;

	public RideDetails bookRide(Integer userId, Integer driverId, Integer x, Integer y)
			throws DriverException, UserException;

	public List<User> getAllUsers() throws UserException;
}
