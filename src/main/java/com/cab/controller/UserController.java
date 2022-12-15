package com.cab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.exception.DriverException;
import com.cab.exception.UserException;
import com.cab.model.Driver;
import com.cab.model.RideDetails;
import com.cab.model.User;
import com.cab.model.UserDTO;
import com.cab.service.UserService;

@RestController
@RequestMapping("/masaicab/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) throws UserException {
		return new ResponseEntity<User>(userService.registerUser(userDTO), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser() throws UserException {
		return new ResponseEntity<String>("Login successfully done!", HttpStatus.OK);
	}

	@GetMapping("/findride")
	public ResponseEntity<List<Driver>> findRide(@Valid @RequestParam("userId") Integer userId)
			throws DriverException, UserException {
		return new ResponseEntity<List<Driver>>(userService.findRide(userId), HttpStatus.OK);
	}

	@PutMapping("/book/{driverId}/{x}/{y}")
	public ResponseEntity<RideDetails> bookRide(@Valid @PathVariable("driverId") Integer driverId,
			@PathVariable("x") Integer x, @PathVariable("y") Integer y, @RequestParam("userId") Integer userId)
			throws UserException, DriverException {
		return new ResponseEntity<RideDetails>(userService.bookRide(userId, driverId, x, y), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() throws UserException {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

}
