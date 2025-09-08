package com.techie.mybank.DAO;

import java.util.List;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.CreditCards;
import com.techie.mybank.model.Transactions;

public interface CardDAO {
	
	// User Control
	public CreditCards uview(Account logac);
	public CreditCards udebit(CreditCards logac);
	public long uviewBalance(Account logac);
	public int uupdate(CreditCards logac);

	
	// Bank Management
	public List<CreditCards> view();
	public CreditCards viewByAccountId(Long card_id);
	public int update(CreditCards card_id);
	public int delete(CreditCards cc);
	public CreditCards deposit(CreditCards cc);
	public CreditCards enrollment(CreditCards cc);
	public long checkBalance(Long creditCard);
	public void transdebit(CreditCards debit);
	public void transdeposit(CreditCards cc);
	public List<Transactions> viewAllTransactions(CreditCards cc);
	public List<Transactions> viewAllTransactions(Account logac);
	
}
