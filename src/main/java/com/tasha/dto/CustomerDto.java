package com.tasha.dto;

import java.sql.Date;

/**
 * @author Shahrukh
 * @since May 2022
 */
public class CustomerDto {
	private int custId;
	private String custName, custEmail, custPassword;
	private double regAmount;
	private Date regDate;
	private String custRole;

	public CustomerDto() {
		
	}

	/**
	 * @param custId
	 * @param custName
	 * @param custEmail
	 * @param custPassword
	 * @param regAmount
	 * @param regDate
	 * @param custRole
	 */
	public CustomerDto(int custId, String custName, String custEmail, String custPassword, double regAmount,
			Date regDate, String custRole) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPassword = custPassword;
		this.regAmount = regAmount;
		this.regDate = regDate;
		this.custRole = custRole;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getCustRole() {
		return custRole;
	}

	public void setCustRole(String custRole) {
		this.custRole = custRole;
	}

	@Override
	public String toString() {
		return "CustomerDto [custId=" + custId + ", custName=" + custName + ", custEmail=" + custEmail
				+ ", custPassword=" + custPassword + ", regAmount=" + regAmount + ", regDate=" + regDate + ", custRole="
				+ custRole + "]";
	}
}
