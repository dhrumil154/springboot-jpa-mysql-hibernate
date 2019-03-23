package com.kjit.Diekraft.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	@Entity
	@Table(name = "Appoinment")
	public class Appointment {
		
		@Id
		@GeneratedValue
		@Column
		private Long id;
		
		@ManyToOne
		private User user;
		
		@Column
		private ZonedDateTime time;
		
		@Column
		private ZonedDateTime date;
		
		public Long getId(){ return id;}
		public void setId(Long id) { this.id=id; }
		
		public User getUser() {return user;}
		public void setUser(User user){this.user=user ;}
		
		public ZonedDateTime getTime() {return time;}
		public void setTime(ZonedDateTime time){this.time=time;}
		
		public ZonedDateTime getDate(){return date;}
		public void setDate(ZonedDateTime date){this.date=date;}
		
		
	}
