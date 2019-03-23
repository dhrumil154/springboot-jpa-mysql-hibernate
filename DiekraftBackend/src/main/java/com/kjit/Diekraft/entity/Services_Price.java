package com.kjit.Diekraft.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Services_Price")
public class Services_Price {
	
	@Id
	private String name;

	@Column
	private BigDecimal Price;
	
	public String getName() {
		return name;
	}	

	public void setName(String name) {
		this.name = name;
	}
	
		public String getPrice() {
			return name;
		}	

		public void setPrice(String name) {
			this.name = name;

		
	}

}
