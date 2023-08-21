package com.lld.bookmyshow.controllers;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lld.bookmyshow.dtos.BookTicketRequestDto;
import com.lld.bookmyshow.dtos.BookTicketResponseDto;
import com.lld.bookmyshow.dtos.CancelTicketRequestDto;
import com.lld.bookmyshow.dtos.CancelTicketResponseDto;
import com.lld.bookmyshow.exceptions.BookingTimeClosedException;
import com.lld.bookmyshow.exceptions.InvalidArgumentException;
import com.lld.bookmyshow.exceptions.SeatNotAvailableException;
import com.lld.bookmyshow.models.Ticket;
import com.lld.bookmyshow.services.TicketService;

@Controller
public class TicketController {
	
	private TicketService ticketService;
	
	@Autowired
	public TicketController(TicketService ticketService) {
		this.ticketService=ticketService;
	}
	
	public BookTicketResponseDto bookTicket(BookTicketRequestDto request) {
		BookTicketResponseDto response = new BookTicketResponseDto(); 
		Ticket ticket = null;
		
		try {
			ticket = ticketService.bookTicket(null, null, null);
			response.setMessage("Booked Successfully");
			response.setStatus("SUCCESS");
			response.setAmount(ticket.getAmount());
			response.setAuditoriumName(ticket.getShow().getAuditorium().getName());
			response.setTicketId(ticket.getId());
			
			//Assign the seat number returned
			//response.setSeatNumber(null);
		} catch(InvalidArgumentException e) {
			response.setStatus("FAILURE");
			response.setMessage("Something is wrong");
		} catch(SeatNotAvailableException e){
			response.setStatus("FAILURE");
			response.setMessage("Something is wrong");
		} catch (BookingTimeClosedException e) {
			response.setStatus("FAILURE");
			response.setMessage("Something is wrong");
		}
		
		return response;
	}
	
	public CancelTicketResponseDto cancelTicket(CancelTicketRequestDto request) {
		CancelTicketResponseDto response = new CancelTicketResponseDto(); 
		Long ticketId = request.getTicketId();
		response.setTicketId(ticketId);
		try {
			this.ticketService.cancelTicket(ticketId);
			response.setMessage("Successfully Cancelled");
			response.setMessage("SUCCESS");
		} catch(Exception e) {
			response.setStatus("FAILURE");
			response.setMessage("Something is wrong");
		}
		
		return response;
	}

}
