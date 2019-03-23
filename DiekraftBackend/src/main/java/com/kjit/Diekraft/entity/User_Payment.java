package com.kjit.Diekraft.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table

public class User_Payment {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Column
	private BigDecimal amount;

	@Column
	private ZonedDateTime time;
	
	public Long getId() { return id;}
	
	public void setId(Long id) { this.id = id;}
	
	public User getUser() {return user;}
	
	public void setUser(User user) { this.user = user;}
	
	public BigDecimal amount(){return amount;}
	public void setAmount(BigDecimal amount){this.amount=amount;}
	
	public ZonedDateTime getDate(){return time;}
	
	public void setDate(ZonedDateTime date){this.time=time;}
	
	
	
	}

