package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.DAO.AccountDAO;
import com.techie.mybank.DAO.AccountDAOImpl;
import com.techie.mybank.DAO.LoginDAO;
import com.techie.mybank.DAO.LoginDAOImpl;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Login;
import com.techie.mybank.model.Transactions;

public class AccountServiceImpl implements AccountService {

	AccountDAO ad=new AccountDAOImpl();
	LoginDAO logDao = new LoginDAOImpl();
	
	
	public List<Account> viewAll(Account account) {
		List<Account> view = ad.viewAll(account);
		for(Account list : view) {
			System.out.println("Account Number : " + list.getAccountNo());
			System.out.println("Account Holder Name : " + list.getAccountName());
			System.out.println("Account Holder email : " + list.getEmail());
			System.out.println("Account Holder phone Number : " + list.getMobileno());
			System.out.println("Account Holder Address : " + list.getAddress());
		}
		return view;
	}


	@Override
	public Account update(Account account) {
		int result = ad.update(account);
		
		if(result  > 0) {
			System.out.println("Updated Successfully");
		}else {
			System.out.println("Updated Unsuccessfully");
		}
		return null;
	}
	
	@Override
	public long viewBalance(Account account) {
		long result = ad.viewBalance(account);
		System.out.println("Your Account Balance :" + result);
		return 0;
	}
	
	@Override
	public List<Transactions> utransactions(Account account) {
		List<Transactions> result = ad.utransactions(account);
		for(Transactions a : result) {
			System.out.println("Date : " + a.getTransactiondate() + " " + a.getTransactiontype() + "amount : " + a.getAmount());
		}
		return null;
	}
	
	//Banking management
	
	@Override
	public Account open(Account account) {
		long  result = ad.open(account);
		if(result > 0) {
			System.out.println("Account No.:"+ account.getAccountNo());
			System.out.println("Account Name:"+ account.getAccountName());
			System.out.println("Email Id.:"+ account.getEmail());
			System.out.println("AccountHolder Mobile No.:"+ account.getMobileno());
			System.out.println("Address:"+ account.getAddress());
			System.out.println("Account Balance:"+account.getAccountBalance());
			
			Login login = new Login();
			login.setUserName(account.getEmail());
			login.setPassword(account.getAccountName());
			login.setActive(true);
			login.setDelete(false);
			login.setAccountId(result);
			logDao.create(login);
			
			Account trans = new Account();
			trans.setAccountNo(account.getAccountNo());
			trans.setCreditAmount(account.getCreditAmount());
			trans.setAccountid(result);
			ad.transdeposit(trans);
		}
		
		return null ;
		
	}
	
	@Override
	public Account viewAccountById(Account account) {
		
		Account result  = ad.viewAccountById(account);
		
		System.out.println("Account No.:" + result.getAccountNo());
		System.out.println("Account Name:" + result.getAccountName());
		System.out.println("Email Id.:" + result.getEmail());
		System.out.println("AccountHolder Mobile No.:" + result.getMobileno());
		System.out.println("Address:" + result.getAddress());
		System.out.println("Account Balance:" + result.getAccountBalance());
		return null;
	}
	
	@Override
	public void viewAccountByNo(Long accountNo) {
		Account result = ad.viewAccountByNo(accountNo);
		System.out.println("Account No.:"+result.getAccountNo());
		System.out.println("Account Name:"+ result.getAccountName());
		System.out.println("Email Id.:"+ result.getEmail());
		System.out.println("AccountHolder Mobile No.:"+ result.getMobileno());
		System.out.println("Address:"+ result.getAddress());
		System.out.println("Account Balance:"+result.getAccountBalance());
		
	}
	
	@Override
	public void mupdate(Account account) {
		int result = ad.mupdate(account);
		
		if(result > 0) {
			System.out.println("Update Successfully");
		}
		
	}
	
	@Override
	public Account deposit(Account account) {
		Account result = ad.deposit(account);
		ad.transdeposit(account);
		System.out.println("Account Balance:" + result.getAccountBalance());
		return null;
	}

	@Override
	public Account debit(Account account) {
		Account result = ad.debit(account);
		ad.transdebit(account);
		System.out.println("Account Balance:" + result.getAccountBalance());
		System.out.println("You get debit Amount" + account.getDebitAmount());
		return null;
	}
	
	@Override
	public Account delete(Account account) {
		int result = ad.delete(account);
		if(result > 0) {
			System.out.println("Account is delete Successfully");
		}
		return null;
	}
	

	@Override
	public List<Transactions> transactions(Account account) {
		List<Transactions> result = ad.transactions(account);
		for(Transactions a : result) {
			System.out.println("Date : " + a.getTransactiondate() + " " + a.getTransactiontype() + "amount : " + a.getAmount());
		}
		return null;
	}
	
}