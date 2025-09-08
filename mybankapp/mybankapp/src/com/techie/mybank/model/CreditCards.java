package com.techie.mybank.model;

public class CreditCards {

	private String cardHolderName;
	private long cardNo;
	private String email;
	private String address;
	private long mobileNo;
	private long cardid;
	private long accountNo;
	private long accountid;
	private long creditamount;
	private long debitamount;
	private long cardBalance;
	
		
	public long getCardid() {
		return cardid;
	}
	public void setCardid(long cardid) {
		this.cardid = cardid;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public long getAccountid() {
		return accountid;
	}
	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}
	public long getCreditamount() {
		return creditamount;
	}
	public void setCreditamount(long creditamount) {
		this.creditamount = creditamount;
	}
	public long getDebitamount() {
		return debitamount;
	}
	public void setDebitamount(long debitamount) {
		this.debitamount = debitamount;
	}
	public long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(long cardBalance) {
		this.cardBalance = cardBalance;
	}
	

}
