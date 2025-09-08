package com.techie.mybank.service;
import java.util.List;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;
public interface AccountService {
	
	public List<Account> viewAll(Account account);
	public Account update(Account account);
	public	long viewBalance(Account account);
	
	// Banking management
	public Account open(Account account);
	public Account viewAccountById(Account account);
	public void viewAccountByNo(Long accountNo);
	public void mupdate(Account account);
	public Account deposit(Account account);
	public Account debit(Account account);
	public List<Transactions> transactions(Account account);
	public Account delete(Account account);
	List<Transactions> utransactions(Account account);
	
	
	
	
	}


