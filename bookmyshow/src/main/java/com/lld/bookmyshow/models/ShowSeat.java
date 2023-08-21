package com.lld.bookmyshow.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;


@Entity
public class ShowSeat extends BaseModel {
	
	@ManyToOne
	private Show show;
	
	@ManyToOne
	private Seat seat;
	
	@Enumerated(EnumType.ORDINAL)
	private ShowSeatStatus status;
	
	private Date lockedAt;
	
	public Show getShow() {
		return show;
	}

	public Date getLockedAt() {
		return lockedAt;
	}

	public void setLockedAt(Date lockedAt) {
		this.lockedAt = lockedAt;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public ShowSeatStatus getStatus() {
		return status;
	}

	public void setStatus(ShowSeatStatus status) {
		this.status = status;
	}

}
