package com.lld.bookmyshow.dtos;

public class CancelTicketResponseDto {

	private String message;
	private String Status;
	private Long ticketId;
	
	public CancelTicketResponseDto() {
		
	}
	
	public CancelTicketResponseDto(String message, String status, Long ticketId) {
		this.message = message;
		Status = status;
		this.ticketId = ticketId;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	
}
