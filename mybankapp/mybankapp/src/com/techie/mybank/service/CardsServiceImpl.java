package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.DAO.AccountDAOImpl;
import com.techie.mybank.DAO.CardDAO;
import com.techie.mybank.DAO.CardDAOImpl;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.CreditCards;
import com.techie.mybank.model.Transactions;

public class CardsServiceImpl implements CardsService{
	
	
	CardDAO cd=new CardDAOImpl();
	
	// User Control
	
	@Override
	public void uview(Account logac) {
		CreditCards result = cd.uview(logac);
		
		if(result != null) {
			System.out.println("Credits Card Holder Name : "+ result.getCardHolderName());
			System.out.println("Credits Card No : "+ result.getCardNo());
			System.out.println("Credits Card Email : "+ result.getEmail());
			System.out.println("Credits Card Mobile No : "+ result.getMobileNo());
			System.out.println("Credits Card Address : "+ result.getAddress());
		}
	}
	
	@Override
	public void udebit(CreditCards logac) {
		CreditCards result = cd.udebit(logac);
		
		if(result != null) {
			System.out.println("You Will Get Amount : " + logac.getDebitamount());
			System.out.println("Your CreditCard Balance : " + result.getCardBalance());
			
			CreditCards trans = new CreditCards();
			trans.setAccountid(logac.getAccountid());
			trans.setCardNo(logac.getCardNo());
			trans.setDebitamount(logac.getDebitamount());
			cd.transdebit(trans);
		}
	}
	
	@Override
	public void uviewBalance(Account logac) {
		long result = cd.uviewBalance(logac);
		
		if(result != 0) {
			System.out.println("Your Card Balance : " + result);
		}
		
	}
	
	@Override
	public void uupdate(CreditCards logac) {
		int result =cd.uupdate(logac);
		if(result > 0) {
			System.out.println("Updated Successfully");
		}else {
			System.out.println("Updated Unsuccessfully");
		}
	}
	
	@Override
	public void uviewAllTransactions(Account logac) {
		List<Transactions> result = cd.viewAllTransactions(logac);
		for(Transactions a : result) {
			System.out.println("Date : " + a.getTransactiondate() + " " + a.getTransactiontype() + "amount : " + a.getAmount());
		}
		
	}
	
	// Bank Management
	
	public CreditCards enrollment(CreditCards cc) {
		AccountDAOImpl account = new AccountDAOImpl();
		long accountno = cc.getAccountNo();
		Account result = account.viewAccountByNo(accountno);
		CreditCards cards = new CreditCards();
		if(result != null) {
			cards.setAccountid(result.getAccountid());
			cards.setCardHolderName(result.getAccountName());
			cards.setEmail(result.getEmail());
			cards.setMobileNo(result.getMobileno());
			cards.setAddress(result.getAddress());
			cards.setCardBalance(cc.getCardBalance());
			cards.setCardNo(cc.getCardNo());
		}
		
		cd.enrollment(cards);
		return null;
	}

	
	public CreditCards view() {
		List<CreditCards> result = cd.view();
		
		for(CreditCards rss : result) { 
		System.out.println("===================================================");
		System.out.println("CardHolder Name.:"+rss.getCardHolderName());
		System.out.println("Card No:"+rss.getCardNo());
		System.out.println("Email Id:"+rss.getEmail());
		System.out.println("Mobile No:"+rss.getMobileNo());
		System.out.println("Address:"+rss.getAddress());
		}
		return null;
	}
	
	
	
	public CreditCards viewByAccountId(Long card_id) {
		CreditCards rss = cd.viewByAccountId(card_id);
		
		System.out.println("CardHolder Name.:"+rss.getCardHolderName());
		System.out.println("Card No:"+rss.getCardNo());
		System.out.println("Email Id:"+rss.getEmail());
		System.out.println("Mobile No:"+rss.getMobileNo());
		System.out.println("Address:"+rss.getAddress());
		return null;
	}
	

	public CreditCards update(CreditCards card_id) {
		int result = cd.update(card_id);
		if(result > 0) {
			System.out.println("Updated Successfully");
		}else {
			System.out.println("Updated Unsuccessfully");
		}
		return null;
	}
	
	
	public CreditCards delete(CreditCards cc) {
		int result = cd.delete(cc);
		
		if(result > 0) {
			System.out.println("Delete Successfully");
		}else {
			System.out.println("Delete Unsuccessfully");
		}
		
		return null;
	}
	
	
	
	@Override
	public long checkBalance(Long creditCard) {
		long result = cd.checkBalance(creditCard);
		
		System.out.println("Balance in CreditCard : " + result);
		return 0;
	}

	@Override
	public void desposit(CreditCards cc) {
		CreditCards result = cd.deposit(cc);
		
		if(result != null) {
			System.out.println("CreditCard Balance amount : " + result.getCardBalance());
			CreditCards trans = new CreditCards();
			trans.setAccountid(result.getAccountid());
			trans.setCardNo(cc.getCardNo());
			trans.setCreditamount(cc.getCreditamount());
			cd.transdeposit(trans);
			
		}
		
	}

	@Override
	public void viewAllTransactions(CreditCards cc) {
		List<Transactions> result = cd.viewAllTransactions(cc);
		for(Transactions a : result) {
			System.out.println("Date : " + a.getTransactiondate() + " " + a.getTransactiontype() + "amount : " + a.getAmount());
		}
	}






}
