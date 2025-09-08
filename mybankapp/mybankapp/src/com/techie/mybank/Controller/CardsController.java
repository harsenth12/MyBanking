package com.techie.mybank.Controller;

import java.util.Scanner;

import com.techie.mybank.main.BankClient;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.CreditCards;
import com.techie.mybank.service.CardsService;
import com.techie.mybank.service.CardsServiceImpl;

public class CardsController {

	
		Scanner sc= new Scanner(System.in);
		CardsService cs=new CardsServiceImpl();
		
		//User Control 
		
		public void home(Account logac) {
			System.out.println("Please choose the desired Option");
			System.out.println("=================================");
			System.out.println("Please select Option 1 if you want to view an Creditcard accountdetails");
			System.out.println("Please select Option 2 if you want to debit amount");
			System.out.println("Please select Option 3 if you want to view account balance");
			System.out.println("Please select Option 4 if you want to view Transaction");
			System.out.println("Please select Option 5 if you want to Update CreditCard Details");
			System.out.println("Please select Option 6 if you want to Exit");
			
		sc=new Scanner(System.in);
		int selectoption=sc.nextInt();
		
		switch(selectoption){
			case 1: System.out.println("You have selected View Creditcard Details");
			uview(logac);
			break;
			case 2: System.out.println("You have selected Debit Amount in Creditcard");
			udebit(logac);
			break;
			case 3: System.out.println("You have selected view Account Balance in Creditcard");
			uviewBalance(logac);
			break;
			case 4: System.out.println("You have selected viewAll Transactions in Creditcard");
			uviewAllTransactions(logac);
			break;
			case 5: System.out.println("You have selected Update CreditCard Details");
			uupdate(logac);
			break;
			case 6:System.out.println("You have selected Exit in CreditCard Section");
			BankClient obj = new BankClient();
			obj.ucorebanking(logac);
			break;
			
			default: 
				System.out.println("Please select the correct desired option");
				home(logac);
				break;
				}

		}
		
		private void uview(Account logac) {
			cs.uview(logac);
			
			home(logac);
		}
		
		private void udebit(Account logac) {
			System.out.println("Enter the debit Amount : ");
			long debitamount = sc.nextLong();
			CreditCards debit = new CreditCards();
			debit.setAccountid(logac.getAccountid());
			debit.setDebitamount(debitamount);
			cs.udebit(debit);
			
			home(logac);
		}
		
		private void uviewBalance(Account logac) {
			cs.uviewBalance(logac);
			
			home(logac);
		}
		
		private void uupdate(Account logac) {
			CreditCards details = new CreditCards();
			System.out.println("Enter your name :");
			String name = sc.nextLine();
			details.setCardHolderName(name);
			
			sc.nextLine();
			
			System.out.println("Enter your update mobile No : ");
			long mobileno = sc.nextLong();
			details.setMobileNo(mobileno);
			
			sc.nextLine();
			
			System.out.println("Enter your update email : ");
			String email = sc.nextLine();
			details.setEmail(email);
			
			System.out.println("Enter your update address : ");
			String address = sc.nextLine();
			details.setAddress(address);
			
			long accountid = logac.getAccountid();
			details.setAccountid(accountid);
			
			cs.uupdate(details);
			
			home(logac);
		}
		
		private void uviewAllTransactions(Account logac) {
			cs.uviewAllTransactions(logac);
			
			home(logac);
		}
		
		
		// Banking management

		public void mhome() {
			System.out.println("Please choose the desired Option");
			System.out.println("=================================");
			System.out.println("Please select Option 1 if you want to open CreditCard");
			System.out.println("Please select Option 2 if you want to viewAll CreditCards details");
			System.out.println("Please select Option 3 if you want to ViewAccountby Creditcard details");
			System.out.println("Please select Option 4 if you want to update Creditcard details");
			System.out.println("Please select Option 5 if you want to deposit amount");
			System.out.println("Please select Option 6 if you want to view Creditcard Balance");
			System.out.println("Please select Option 7 if you want to view account transactions");
			System.out.println("Please select Option 8 if you want to delete Creditcard details");
			System.out.println("Please select Option 9 if you want to Exit");

			
			sc=new Scanner(System.in);
			int selectoption=sc.nextInt();
			
			switch(selectoption){	
				case 1: System.out.println("You have selected Creditcard Opening Process");
				enrollment();
				break;
				case 2: System.out.println("You have selected ViewAll Creditcard Details");
				view();
				break;
				case 3: System.out.println("You have selected ViewByAccountId");
				viewByAccountId();
				break;
				case 4: System.out.println("You have selected update Creditcard details");
				update();
				break;
				case 5: System.out.println("You have selected Deposit Amount in Creditcard");
				deposit();
				break;
				case 6: System.out.println("You have selected view Balance in Creditcard");
				viewBalance();
				break;
				case 7: System.out.println("You have selected viewAll Transactions in Creditcard");
				viewAllTransactions();
				break;
				case 8: System.out.println("You have selected Delete CreditCard Details");
				delete();
				break;
				case 9: System.out.println("You have selected Exit in CreditCard section");
				BankClient obj = new BankClient();
				obj.mcorebanking();
				break;
				
				default:
				System.out.println("Please selected correct option");
				mhome();  // return banking management
			}
		}
		
		
		private void enrollment() {
			System.out.println("You have choosen CreditCard Opening Process");
			System.out.println("===========================================");
			System.out.println("Please enter Card Holder Details");
			
			CreditCards cc=new CreditCards();
			
			
			System.out.println("Please enter Account number : ");
			long accountno=sc.nextLong();
			cc.setAccountNo(accountno);
			
			System.out.println("Please enter Card No:");
			Long cardNo=sc.nextLong();
			cc.setCardNo(cardNo);
			
			System.out.println("Please enter Card Balance :");
			Long cardBalance=sc.nextLong();
			cc.setCardBalance(cardBalance);
			
			cs.enrollment(cc);	
			
			mhome();  // return banking management
		}

		private void view() {
			System.out.println("You have choosen option to view CreditCard details");
			System.out.println("===========================================");
			cs.view();	
	
			mhome();  // return banking management

		}
				
		private void viewByAccountId() {
			System.out.println("You have choosen option to view CreditCard details");
			System.out.println("===========================================");
			System.out.println("Please enter Card no: ");
			CreditCards cc=new CreditCards();
			Long card_id=sc.nextLong();
			cc.setCardid(card_id);
			
			cs.viewByAccountId(card_id);	
			
			mhome();  // return banking management

		}
			
		private void update() {
			System.out.println("You have choosen option to Update CreditCard details");
			System.out.println("===========================================");
			System.out.println("Please enter Card No: ");	
			CreditCards cc=new CreditCards();
			Long card_id=sc.nextLong();
			cc.setCardid(card_id);	
	
			System.out.println("Enter your name :");
			String name = sc.nextLine();
			cc.setCardHolderName(name);
			
			sc.nextLine();
			
			System.out.println("Enter your update mobile No : ");
			long mobileno = sc.nextLong();
			cc.setMobileNo(mobileno);
			
			sc.nextLine();
			
			System.out.println("Enter your update email : ");
			String email = sc.nextLine();
			cc.setEmail(email);
			
			System.out.println("Enter your update address : ");
			String address = sc.nextLine();
			cc.setAddress(address);
			
			cs.update(cc);
			
			mhome();  // return banking management

		}
				
		private void viewBalance() {
			System.out.println("You have choosen option to viewBalance CreditCard details");
			System.out.println("============================================");
			System.out.println("Please enter Card no : ");
			Long cardno = sc.nextLong();
			cs.checkBalance(cardno);
			
			mhome();  // return banking management

		}

		private void deposit() {
			System.out.println("You have choosen option to deposit amount in Creditcard");
			System.out.println("============================================");
			CreditCards cc = new CreditCards();
			System.out.println("Please enter Card no : ");
			Long cardno = sc.nextLong();
			cc.setCardNo(cardno);
			
			System.out.println("Please enter the amount : ");
			Long amount = sc.nextLong();
			cc.setCreditamount(amount);
			
			cs.desposit(cc);
			
			mhome();  // return banking management

		}
		
		private void viewAllTransactions() {
			System.out.println("You have choosen option to Transactions in Creditcard");
			System.out.println("============================================");
			CreditCards cc = new CreditCards();
			System.out.println("Please enter Card no : ");
			Long cardno = sc.nextLong();
			cc.setCardNo(cardno);
			
			cs.viewAllTransactions(cc);
			
			mhome();  // return banking management
		}
		
		private void delete() {
			System.out.println("You have choosen option to delete CreditCard details");
			System.out.println("===========================================");
			System.out.println("Please enter Card No : ");	
			CreditCards cc=new CreditCards();
			Long card_id=sc.nextLong();
			cc.setCardid(card_id);
			cs.delete(cc);
			
			mhome();  // return banking management

		}


}