package com.cab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cab.model.SecurityUser;
import com.cab.model.User;
import com.cab.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

		User user = userRepo.findByMobileNumber(mobileNumber);

		if (user != null) {
			return new SecurityUser(user);
		} else {
			throw new UsernameNotFoundException("user not found!!");
		}

	}

}