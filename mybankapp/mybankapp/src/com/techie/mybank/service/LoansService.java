package com.techie.mybank.service;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Loans;

public interface LoansService {
	
	// user controller
	public void uview(Account logac);
	public void uviewloanAmount(Account logac);
	public void uviewloanbalance(Account logac);


	
	// banking controller

	public Loans enrollment (Loans loan);
	public Loans view();
	public Loans ViewAccountById(Loans loan);
	public Loans update(Loans loan);
	public Loans closure (Loans loan);
	public void deposit(Loans loan);
	public void transaction(Loans loan);
	public void uloanTransactions(Account logac);	
	
	
}
