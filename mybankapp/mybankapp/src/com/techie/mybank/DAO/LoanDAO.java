package com.techie.mybank.DAO;

import java.util.List;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Loans;
import com.techie.mybank.model.Transactions;

public interface LoanDAO {
	
	// user controller
	
	public Loans uview(Account logac);
	public Loans uviewloanAmount(Account logac);
	public Loans uviewloanbalance(Account logac);


	// banking controller

	public void enrollment(Loans loan);
	public List<Loans> view();
	public Loans ViewAccountById(Loans loan);
	public int update(Loans loan);
	public int closure(Loans loan);
	public long deposit(Loans loan);
	public void transdeposit(Loans loan);
	public List<Transactions> transaction(Loans loan);
	public List<Transactions> utransaction(Account logac);	

}
