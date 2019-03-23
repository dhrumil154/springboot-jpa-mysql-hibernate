package com.kjit.Diekraft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @PrimaryKeyJoinColumn
	private Long id;

	@NonNull
	@Column
	private String email;

	@NonNull
	@Column
	private String password;

	@NonNull
	@Column
	private String mobileNo;

	@NonNull
	@Column
	private String address;

	@Column
	private String city;

	@Column
	private String zipcode;

	@Column
	private String userName;

	@Column
    private LocalDate dateOfBirth;

	@ManyToOne
	private State state;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "user_authority",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}
	)
	private Set<Authorities> authorities;

    @OneToOne(cascade = CascadeType.ALL)
    private Labour labour;

    public Labour getLabour() {
        return labour;
    }

    public void setLabour(Labour labour) {
        this.labour = labour;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

    public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipcode;
	}

	public void setZipCode(String zipcode) {
		this.zipcode = zipcode;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
