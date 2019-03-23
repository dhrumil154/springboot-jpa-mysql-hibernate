package com.kjit.Diekraft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "Notification")
public class Notification {
	
	@Id
	private String name;
	
	@NonNull
	@Column
	private String mobileNo;

	@ManyToOne
	private User User;
	
	@NonNull
	@Column
	private String Sms_sub;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public User getUser() {
		return User;
	}

	public void setUser(User User) {
		this.User = User;
	}
	
	public String getSms_sub() {
		return Sms_sub;
	}

	public void setSms_sub(String Sms_sub) {
		this.Sms_sub = Sms_sub;
	}
}
