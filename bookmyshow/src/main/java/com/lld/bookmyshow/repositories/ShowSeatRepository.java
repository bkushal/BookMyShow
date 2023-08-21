package com.lld.bookmyshow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.lld.bookmyshow.models.Seat;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.ShowSeat;

import jakarta.persistence.LockModeType;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats, Show show);
	ShowSeat save(ShowSeat showSeat);

}
