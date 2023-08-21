package com.lld.bookmyshow.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {
	
	private String name;
	
	@OneToMany
	private List<Seat> seats;
	
	@Enumerated(EnumType.ORDINAL)
	@ElementCollection
	private List<Feature> features;

}
