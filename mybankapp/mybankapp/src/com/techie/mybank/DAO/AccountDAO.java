package com.techie.mybank.DAO;

import java.util.List;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;

public interface AccountDAO {

	public List<Account> viewAll(Account account);
	public int update(Account account);
	public	long viewBalance(Account account);
	
	//Banking management
	public long open(Account account);
	public Account viewAccountById(Account account);
	public Account viewAccountByNo(Long accountNo);
	public int mupdate(Account account);
	public Account deposit(Account account);
	public Account debit(Account account);
	public int delete(Account account);
	public void transdeposit(Account account);
	public void transdebit(Account account);
	
	// common
	public List<Transactions> transactions(Account account);
	List<Transactions> utransactions(Account account);
	
	
	
	
}
