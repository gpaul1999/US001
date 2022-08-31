package com.dxc.createclient.model;

import java.util.Date;


public class UserInfo {
	private String clientNumber;
	private String firstName;
	private String lastName;
	private String gender;
	private String birth;
	private String identity;
	private String marital;
	private String address;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
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
	public UserInfo(String clientNumber, String firstName, String lastName, String gender, String birth,
			String identity, String marital, String address, String country) {
		super();
		this.clientNumber = clientNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birth = birth;
		this.identity = identity;
		this.marital = marital;
		this.address = address;
		this.country = country;
	}
	@Override
	public String toString() {
		return "UserInfo [clientNumber=" + clientNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", birth=" + birth + ", identity=" + identity + ", marital=" + marital
				+ ", address=" + address + ", country=" + country + "]";
	}
	public UserInfo() {
	}
	
	
	
}
