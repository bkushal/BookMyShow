package com.lld.bookmyshow.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.lld.bookmyshow.exceptions.InvalidArgumentException;
import com.lld.bookmyshow.exceptions.SeatNotAvailableException;
import com.lld.bookmyshow.models.Seat;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.ShowSeat;
import com.lld.bookmyshow.models.ShowSeatStatus;
import com.lld.bookmyshow.models.Ticket;
import com.lld.bookmyshow.models.TicketStatus;
import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.repositories.SeatRepository;
import com.lld.bookmyshow.repositories.ShowRepository;
import com.lld.bookmyshow.repositories.ShowSeatRepository;
import com.lld.bookmyshow.repositories.TicketRepository;
import com.lld.bookmyshow.repositories.UserRepository;


@Service
public class TicketService {
	
	private final SeatRepository seatRepository;
	private final ShowSeatRepository showSeatRepository;
	private final ShowRepository showRepository;
	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	
	@Autowired
	public TicketService(SeatRepository seatRepository, ShowSeatRepository showSeatRepository,
			ShowRepository showRepository, UserRepository userRepository, TicketRepository ticketRepository) {
		this.seatRepository = seatRepository;
		this.showSeatRepository = showSeatRepository;
		this.showRepository = showRepository;
		this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
	public Ticket bookTicket(List<Long> seatIds, Long showId, Long userId) throws InvalidArgumentException, SeatNotAvailableException {
		
		/*
		 *For these seatIds, get corresponding showSeats
		 *
		 *First call getSeatsForIds(), then getShowSeatsForSeats() to get showSeats
		 *and check status of seats
		 *
		 *Check status of showSeats
		 *a: Every seat is available
		 *b: Some are not available
		 *
		 *If a: Lock every seat, (set status to be blocked)
		 *create Ticket and release lock and return Ticket.
		 *
		 *If b: throw exception
		 */
		
		List<Seat> seats = this.seatRepository.findAllById(seatIds);
		Optional<Show> showOptional = this.showRepository.findById(showId);
		if(showOptional.isEmpty()) {
			throw new InvalidArgumentException("Show by: "+showId + " does not exist");
		}
		Show show = showOptional.get();
		
		//Lock will be taken on the show seats
		List<ShowSeat> showSeats = this.showSeatRepository.findAllBySeatInAndShow(seats, show);
		
		for(ShowSeat showSeat : showSeats) {
			if(! showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE)) {
		//			|| (showSeat.getStatus().equals(ShowSeatStatus.LOCKED) && (new Date() - showSeat.getLockedAt())) {
				throw new SeatNotAvailableException("Not all selected seats are available");
			}
		}
		
		List<ShowSeat> savedShowSeats = new ArrayList<>();
		
		for(ShowSeat showSeat : showSeats) {
			showSeat.setStatus(ShowSeatStatus.LOCKED);
			showSeat.setLockedAt(new Date());
			savedShowSeats.add(this.showSeatRepository.save(showSeat));
		}
		
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isEmpty()) {
			throw new InvalidArgumentException("User with id: "+userId+" does not exist.");
		}
		Ticket ticket = new Ticket();
		ticket.setBookedBy(userOptional.get());
		ticket.setStatus(TicketStatus.PROCESSING);
		ticket.setShow(show);
		ticket.setSeats(seats);
		ticket.setAmount(0);
		ticket.setTimeOfBooking(new Date());
		Ticket savedTicket = this.ticketRepository.save(ticket);
		
		return savedTicket;
	}
	
	// commit;
	// Lock will be released

}
