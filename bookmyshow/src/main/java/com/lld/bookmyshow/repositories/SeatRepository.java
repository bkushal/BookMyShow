package com.lld.bookmyshow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lld.bookmyshow.models.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
	
	List<Seat> findAllByIdIn(List<Long> seatIds);

}
