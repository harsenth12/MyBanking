package com.techie.mybank.model;

import java.sql.Date;

public class Loans {

	private String name;
	private String address;
	private long mobileNo;
	private long loanNumber;
	private String email;
	private long loanid;
	private Date loandate;
	private String username;
	private long amount;
	private long balanceamount;
	private long accountid;
	private long accountno;
	
	
	public Date getLoandate() {
		return loandate;
	}
	public void setLoandate(Date loandate) {
		this.loandate = loandate;
	}
	public long getLoanid() {
		return loanid;
	}
	public void setLoanid(long loanid) {
		this.loanid = loanid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(long loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getBalanceamount() {
		return balanceamount;
	}
	public void setBalanceamount(long balanceamount) {
		this.balanceamount = balanceamount;
	}
	public long getAccountid() {
		return accountid;
	}
	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}
	public long getAccountno() {
		return accountno;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	
	


}
