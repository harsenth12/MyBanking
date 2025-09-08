package com.techie.mybank.Controller;

import java.util.Scanner;

import com.techie.mybank.main.BankClient;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Loans;
import com.techie.mybank.service.LoansService;
import com.techie.mybank.service.LoansServiceImpl;

public class LoansController {

		Scanner sc= new Scanner(System.in);
		LoansService ls=new LoansServiceImpl();
		
		// user controller
		
		public void home(Account logac) {
			System.out.println("Please choose the desired Option");
			System.out.println("=================================");
			System.out.println("Please select Option 1 if you want to view Loan Account Details");
			System.out.println("Please select Option 2 if you want to loan amount");
			System.out.println("Please select Option 3 if you want to view loanaccount balance");
			System.out.println("Please select Option 4 if you want to view Loan transactions");
			System.out.println("Please select Option 5 if you want to exit");
			
		sc=new Scanner(System.in);
		int option=sc.nextInt();
		switch(option){
		case 1: 
		System.out.println("You have choosen to view Loan Account");
		uview(logac);
		break;
		case 2:
		System.out.println("You have choosen to view total loan Amount");
		uviewloanAmount(logac);
		break;
		case 3: 
		System.out.println("You have choosen to Loan Balance Amount");
		uviewloanbalance(logac);
		break;
		case 4: 
		System.out.println("You have choosen to close Loan Account");
		uloanTransactions(logac);
		break;
		case 5:
		System.out.println("You have choosen to exit the Loan Section");
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
			System.out.println("You have choosen option to view LoanAccount .");
			System.out.println("===========================================");
			ls.uview(logac);
			
			home(logac); //return user controller
			
		}


		private void uviewloanAmount(Account logac) {
			System.out.println("You have choosen option to view Total Loan Amount");
			System.out.println("===========================================");
			ls.uviewloanAmount(logac);
			
			home(logac); //return user controller
		}


		private void uviewloanbalance(Account logac) {
			System.out.println("You have choosen option to view Balance Loan Amount");
			System.out.println("===========================================");
			ls.uviewloanbalance(logac);
			
			home(logac); //return user controller
		}
		
		private void uloanTransactions(Account logac) {
			System.out.println("You have choosen option to view transactions");
			System.out.println("===========================================");
			ls.uloanTransactions(logac);
			
			home(logac); //return user controller
		}
		
		
		
		// banking controller

		public void mhome() {
			System.out.println("Please choose the desired Option");
			System.out.println("=================================");
			System.out.println("Please select Option 1 if you want to open Loan Account"); 
			System.out.println("Please select Option 2 if you want to view Loan Account Details");
			System.out.println("Please select Option 3 if you want to view Loan details using ViewAccountbyId ");
			System.out.println("Please select Option 4 if you want to update Loan details");
			System.out.println("Please select Option 5 if you want to close Loan details");
			System.out.println("Please select Option 6 if you want to deposit amount by Loan Number");
			System.out.println("Please select Option 7 if you want to view Loan Number transactions");
			System.out.println("Please select Option 8 if you want to exit");

			
		sc=new Scanner(System.in);
		int option=sc.nextInt();
		switch(option){
		case 1:
			System.out.println("You have choosen to open/enroll a Loan Account");
			enrollment();
			break;
		case 2:
			System.out.println("You have choosen to view Loan Account");
			view();
			break;	
		case 3: 
			System.out.println("You have choosen to view Loan Account usin ViewAccountById");
			ViewAccountById();
			break;
		case 4: 
			System.out.println("You have choosen to Update Loan Account ");
			update();
			break;
		case 5: 
			System.out.println("You have choosen to close Loan Account");
			closure();
			break;
		case 6:
			System.out.println("You have choosen to deposit loan amount");
			deposit();
			break;
		case 7:
			System.out.println("You have choosen to view loan Account transaction using Loan Number");
			transaction();
			break;
		case 8:
			System.out.println("You have choosen to exit the Loan Section");
			BankClient obj = new BankClient();
			obj.mcorebanking();
			break;
		default: 
			System.out.println("Please select the correct desired option");
			break;
		}
		}
		

		private void enrollment() {
			System.out.println("You have choosen LoanAccount Opening Process");
			System.out.println("===========================================");
			System.out.println("Please enter Loan Account Holder Details");
			Loans loan=new Loans();
			
			System.out.println("===========================================");
			
			System.out.println("Please enter Account No :");
			long accountno=sc.nextLong();
			loan.setAccountno(accountno);
			
			System.out.println("Please enter Loan No:");
			long loanNumber=sc.nextLong();
			loan.setLoanNumber(loanNumber);
			
			System.out.println("Please enter Loan amount :");
			long loanamount = sc.nextLong();
			loan.setAmount(loanamount);
		
			ls.enrollment(loan);
			
			mhome();  // return banking controller
					
		}

		private void view() {
			System.out.println("You have choosen option to viewAll LoanAccount .");
			System.out.println("===========================================");
			ls.view();
			
			mhome();  // return banking controller
			
		}	
			
		private void ViewAccountById() {
			System.out.println("You have choosen option to view LoanAccount details using Loan Number.");
			System.out.println("===========================================");
			Loans loan=new Loans();
			System.out.println("Please enter Loan Number :");		
			long loan_id=sc.nextLong();
			loan.setLoanid(loan_id);
			ls.ViewAccountById(loan);
			
			mhome();  // return banking controller
			
		}
		
			
		private void update() {
			System.out.println("You have choosen option to Update Loan details");
			System.out.println("===========================================");
			
			System.out.println("Please enter Loan Number to be updated: ");	
			System.out.println("===========================================");
			Loans loan=new Loans();
			long loan_id=sc.nextLong();
			loan.setLoanid(loan_id);	
								
			System.out.println("Please enter Mobile No: ");			
			long mobileNo=sc.nextLong()	;
			loan.setMobileNo(mobileNo);
			
			System.out.println("Please enter Address to be updated: ");		
			String address=sc.next();
			loan.setAddress(address);
			
			ls.update(loan);
			
			mhome();  // return banking controller
				
		}
		
		private void closure() {
			
			System.out.println("You have choosen option to close Loan account");
			System.out.println("===========================================");
			System.out.println("Please enter Loan Number to be deleted : ");	
			System.out.println("===========================================");
			
			Loans loan=new Loans();
			System.out.println("Enter the Loan Number");
			long loan_id=sc.nextLong();
			loan.setLoanid(loan_id);	
			
			ls.closure(loan);
					
			mhome();  // return banking controller
		}
		
		
		private void deposit() {
			
			System.out.println("You have choosen option to deposit loan amount by Loan number");
			System.out.println("==============================================");
			System.out.println("Please enter Loan Number to be deposit the amount");
			System.out.println("==============================================");
			
			Loans loan = new Loans();
			System.out.println("Enter the Loan Number :");
			long loan_number =sc.nextLong();
			loan.setLoanNumber(loan_number);
			
			System.out.println("Enter the Deposit amount");
			long amount = sc.nextLong();
			loan.setAmount(amount);
			ls.deposit(loan);
			
			mhome();  // return banking controller
		}
		
		private void transaction() {
			System.out.println("You have choosen option to transaction loan account by Loan Number");
			System.out.println("==============================================");
			
			Loans loan = new Loans();
			System.out.println("Enter the Loan Number");
			long loan_number = sc.nextLong();
			loan.setLoanNumber(loan_number);
			
			ls.transaction(loan);
			
			mhome();  // return banking controller

		}
}
