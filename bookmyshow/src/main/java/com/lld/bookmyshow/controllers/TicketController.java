package com.lld.bookmyshow.controllers;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lld.bookmyshow.dtos.BookTicketRequestDto;
import com.lld.bookmyshow.dtos.BookTicketResponseDto;
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
		
		/*try {
			ticketService.bookTicket(null, null, null);
		} catch(TimeoutException e) {
			response.setStatus("FAILURE");
			response.setMessage("Something is wrong");
		}*/
		
		return null;
		
	}

}
