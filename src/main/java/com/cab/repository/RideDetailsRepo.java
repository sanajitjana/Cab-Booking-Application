package com.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.model.RideDetails;

@Repository
public interface RideDetailsRepo extends JpaRepository<RideDetails, Integer> {

}
