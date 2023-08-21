package com.lld.bookmyshow.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City extends BaseModel {
	
	@OneToMany(mappedBy="city")
	private List<Theatre> theatres;
	private String name;

}

