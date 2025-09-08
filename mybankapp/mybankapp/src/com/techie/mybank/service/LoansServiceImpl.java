package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.DAO.AccountDAOImpl;
import com.techie.mybank.DAO.LoanDAO;
import com.techie.mybank.DAO.LoanDAOImpl;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Loans;
import com.techie.mybank.model.Transactions;

public class LoansServiceImpl implements LoansService {
	LoanDAO ld=new LoanDAOImpl();
	
	
	// user controller
	
	@Override
	public void uview(Account logac) {
		Loans result = ld.uview(logac);
		System.out.println("Your Loan Number : " + result.getLoanNumber());
		System.out.println("Your Loan amount : " + result.getAmount());
		System.out.println("Your Loan Balance amount : " + result.getBalanceamount());
		System.out.println("Your Loan Apply Date : " + result.getLoandate());
		System.out.println("Your Name : " + result.getName());
		System.out.println("Your Email : " + result.getEmail());
		System.out.println("Your Mobile Number : " + result.getMobileNo());
		System.out.println("Your Address : " + result.getAddress());
		
	}
	
	@Override
	public void uviewloanAmount(Account logac) {
		Loans result = ld.uviewloanAmount(logac);
		System.out.println("Your Loan Number : " + result.getLoanNumber());
		System.out.println("Your Loan amount : " + result.getAmount());		
	}
	
	@Override
	public void uviewloanbalance(Account logac) {
		Loans result = ld.uviewloanbalance(logac);
		System.out.println("Your Loan Number : " + result.getLoanNumber());
		System.out.println("Your Loan amount : " + result.getBalanceamount());	
		
	}
	
	@Override
	public void uloanTransactions(Account logac) {
		List<Transactions> result = ld.utransaction(logac);
		for(Transactions a : result) {
			System.out.println("Date : " + a.getTransactiondate() + " Loan Number : " + a.getTransactionId() + "amount : " + a.getAmount());
		}	
	}

	
	
	// banking controller

	public Loans enrollment(Loans loan) {
		AccountDAOImpl ad = new AccountDAOImpl();
		long accountno = loan.getAccountno();
		Account result = ad.viewAccountByNo(accountno);
		
		Loans loandetail = new Loans();
		loandetail.setLoanNumber(loan.getLoanNumber());
		loandetail.setAmount(loan.getAmount());
		loandetail.setAccountid(result.getAccountid());
		loandetail.setName(result.getAccountName());
		loandetail.setMobileNo(result.getMobileno());
		loandetail.setEmail(result.getEmail());
		loandetail.setAddress(result.getAddress());
		ld.enrollment(loandetail);		
		return null;
	}

	
	public Loans view() {
		List<Loans> results = ld.view();
		for(Loans result : results) {
			System.out.println("===========================================");
			System.out.println("Your Loan Number : " + result.getLoanNumber());
			System.out.println("Your Loan amount : " + result.getAmount());
			System.out.println("Your Loan Balance amount : " + result.getBalanceamount());
			System.out.println("Your Loan Apply Date : " + result.getLoandate());
			System.out.println("Your Name : " + result.getName());
			System.out.println("Your Email : " + result.getEmail());
			System.out.println("Your Mobile Number : " + result.getMobileNo());
			System.out.println("Your Address : " + result.getAddress());
			System.out.println("===========================================");
		}
		return null;
	}
	
	public Loans update(Loans loan) {
		int result = ld.update(loan);
		if(result > 0) {
			System.out.println("Update Successfully");
		}else {
			System.out.println("Update Unsuccessfully");
		}
		return null;
	}
	
	public Loans ViewAccountById(Loans loan) {
		 Loans rss = ld.ViewAccountById(loan);
				System.out.println(" ");
				System.out.println("Loan Holder Name:"+rss.getName());
				System.out.println("Loan No:"+rss.getLoanNumber());
				System.out.println("Loan Amount :" + rss.getAmount());
				System.out.println("Balance Loan Amount :" + rss.getBalanceamount());
				System.out.println("Email Id:"+rss.getEmail());
				System.out.println("Mobile No:"+rss.getMobileNo());
				System.out.println("Address:"+rss.getAddress());
		return null;
	}

	
	public Loans closure(Loans loan) {
		int result = ld.closure(loan);
		if(result > 0) {
			System.out.println("Deleted successfully");
		}else {
			System.out.println("Deleted unsuccessfully");
		}
		return null;
	}
	
	@Override
	public void deposit(Loans loan) {
		long result = ld.deposit(loan);
		if(result > 0) {
			Loans loans = new Loans();
			loans.setAccountid(result);
			loans.setLoanNumber(loan.getLoanNumber());
			loans.setAmount(loan.getAmount());
			ld.transdeposit(loans);
		}
		
	}
	

	@Override
	public void transaction(Loans loan) {
		List<Transactions> result = ld.transaction(loan);
		for(Transactions a : result) {
			System.out.println("Date : " + a.getTransactiondate() + " Loan Number : " + a.getTransactionId() + "amount : " + a.getAmount());
		}
	}


}
