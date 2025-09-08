package com.techie.mybank.service;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.CreditCards;

public interface CardsService {

	// User Control
	public void uview(Account logac);
	public void udebit(CreditCards logac);
	public void uviewBalance(Account logac);
	public void uupdate(CreditCards details);
	
	// Bank Management
	public CreditCards enrollment(CreditCards creditCard);
	public long checkBalance(Long creditCard);
	public CreditCards view();
	public CreditCards viewByAccountId(Long card_id);
	public CreditCards update(CreditCards cc);
	public CreditCards delete(CreditCards cc);
	public void desposit(CreditCards cc);
	public void viewAllTransactions(CreditCards cc);
	public void uviewAllTransactions(Account logac);
	
}

