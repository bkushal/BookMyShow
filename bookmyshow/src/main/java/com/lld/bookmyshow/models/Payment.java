package com.lld.bookmyshow.models;

import java.util.Date;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
	private int amount;
	
	@Enumerated(EnumType.ORDINAL)
	private PaymentProvider paymentProvider;
	private Date time;
	private String refId;
	
	@Enumerated(EnumType.ORDINAL)
	private PaymentStatus status;
	
	@Enumerated(EnumType.ORDINAL)
	private PaymentType paymentType;
}
