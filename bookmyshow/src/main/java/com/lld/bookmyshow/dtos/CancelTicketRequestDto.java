package com.lld.bookmyshow.dtos;

public class CancelTicketRequestDto {
	
	private Long ticketId;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public CancelTicketRequestDto(Long ticketId) {
		super();
		this.ticketId = ticketId;
	}

	public CancelTicketRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
