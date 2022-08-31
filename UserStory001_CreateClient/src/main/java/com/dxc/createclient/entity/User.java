package com.dxc.createclient.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "clientNumber", updatable = false, length = 8, nullable = false)
	private String clientNumber;
	@Column(name = "first_name", length = 60, nullable = false)
	private String firstName;
	@Column(name = "last_name", length = 60, nullable = false)
	private String lastName;
	@Column(name = "gender", length = 30, nullable = false)
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date birth;
	@Column(name = "identity_number", length = 15, nullable = false)
	private String identity;
	@Column(name = "marital_status", length = 30, nullable = false)
	private String marital;
	@Column(name = "address", length = 120, nullable = false)
	private String address;
	@Column(name = "country", length = 30, nullable = false)
	private String country;



	public String getClientNumber() {
		return clientNumber;
	}



	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public Date getBirth() {
		return birth;
	}



	public void setBirth(Date birth) {
		this.birth = birth;
	}



	public String getIdentity() {
		return identity;
	}



	public void setIdentity(String identity) {
		this.identity = identity;
	}



	public String getMarital() {
		return marital;
	}



	public void setMarital(String marital) {
		this.marital = marital;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	@Override
	public String toString() {
		return "User [clientNumber=" + clientNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", birth=" + birth + ", identity=" + identity + ", marital=" + marital
				+ ", address=" + address + ", country=" + country + "]";
	}



	public User(String firstName, String lastName, String gender, Date birth, String identity, String marital,
			String address, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birth = birth;
		this.identity = identity;
		this.marital = marital;
		this.address = address;
		this.country = country;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}





}

