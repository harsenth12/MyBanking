package com.techie.mybank.Controller;

import java.util.Scanner;

import com.techie.mybank.main.BankClient;
import com.techie.mybank.model.Account;
import com.techie.mybank.service.AccountService;
import com.techie.mybank.service.AccountServiceImpl;

public class AccountController {
	Scanner sc= new Scanner(System.in);
	
	AccountService as=new AccountServiceImpl();


public void home(Account logac) {
	System.out.println("Please choose the desired Option");
	System.out.println("=================================");
	System.out.println("Please select Option 1 if you want to view the account details an account");
	System.out.println("Please select Option 2 if you want to update an account");
	System.out.println("Please select Option 3 if you want to view account balance");
	System.out.println("Please select Option 4 if you want to view account transactions");
	System.out.println("Please select Option 5 if you want to Exit in Account Section");

	sc=new Scanner(System.in);
	int option=sc.nextInt();
	
	switch(option) {
	case 1:
	System.out.println("You have selected ViewAll details Account process");
	viewAll(logac);
	break;
	case 2:
	System.out.println("You have selected update Account process");
	update(logac);
	break;
	case 3: 
	System.out.println("You have selected view Account Balance process");
	viewBalance(logac);
	break;
	case 4: 
	System.out.println("You have selected viewAll Transactions process");
	uviewAllTransactions(logac);
	break;
	case 5:
	BankClient.mainlogin();
	break;
	default: 
		System.out.println("Please select the correct desired option");
		home(logac);
		break;
	}
}


private void viewAll(Account account) {
	System.out.println("You have choosen viewAll Account Process");
	System.out.println("=========================================");
	as.viewAll(account);
	
	home(account);  // Return to choice option user
	
}

private void update(Account logac) {
	System.out.println("You have choosen Account updation Process");
	System.out.println("=========================================");
	
	Account account=new Account();

	System.out.println("Enter Mobile No. to update :");	
	Long mobileno=sc.nextLong();
	account.setMobileno(mobileno);
	sc.nextLine();
	System.out.println("Enter Address for updatation :");	
	String address=sc.nextLine();
	account.setAddress(address);
	account.setAccountid(logac.getAccountid());
	System.out.println("Y/N");
	String in = sc.nextLine();
	String choice = in.toLowerCase();
	if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
		as.update(account);
	}else {
		home(logac); //banking process return
	}
	home(logac);
}

private void viewBalance(Account logac) {
	System.out.println("You have choosen Deposit Account process");
	System.out.println("=========================================");
	Account account  = new Account();
	account.setAccountid(logac.getAccountid());;
	as.viewBalance(account);
	home(logac);
	
}

private void uviewAllTransactions(Account account) {
	System.out.println("You have choosen viewAll transactions histroy");
	System.out.println("=========================================");
	as.utransactions(account);
	
	home(account); //banking process return
	
}


// Banking Management

public void mhome() {
	System.out.println("Please choose the desired Option");
	System.out.println("=================================");
	System.out.println("Please select Option 1 if you want to Account open process");
	System.out.println("Please select Option 2 if you want to view Account details using ViewAccountbyid");
	System.out.println("Please select Option 3 if you want to view Account details using accountno");
	System.out.println("Please select Option 4 if you want to update an account");
	System.out.println("Please select Option 5 if you want to deposit amount");
	System.out.println("Please select Option 6 if you want to debit amount");
	System.out.println("Please select Option 7 if you want to view account transactions");
	System.out.println("Please select Option 8 if you want to delete an account");
	System.out.println("Please select Option 5 if you want to Exit in Account Section");
	
	sc=new Scanner(System.in);
	int option=sc.nextInt();
	
	switch(option) {
	case 1:
		System.out.println("You have selected Account Open process");
		open();
		break;
	case 2:
		System.out.println("You have selected ViewAll Account details process");
		viewAccountById();
		break;
	case 3:
		System.out.println("You have selected ViewAll details Account process");
		viewAccountByNo();
		break;
	case 4:
		System.out.println("You have selected Update Account");
		mupdate();
		break;
	case 5:
		System.out.println("You have selected Deposit process");
		deposit();
		break;
	case 6:
		System.out.println("You have selected Debit process");
		debit();
		break;
	case 7:
		System.out.println("You have selected viewAll transactions");
		viewAllTransactions();
		break;
	case 8:
		System.out.println("You have selected delete process");
		delete();
		break;
	case 9:
	BankClient.mainlogin();
	break;
	
	default:
		System.out.println("Correct input select");
		mhome();  //  return to Banking process
	}
}

private void open() {
	
Scanner sc = new Scanner(System.in);
System.out.println("You have choosen Account Opening Process");
System.out.println("=========================================");
System.out.println("Please enter Account Holder Details");

Account account=new Account();

System.out.println("Please enter Account Holder Name");
String accountName=sc.nextLine();
account.setAccountName(accountName);

System.out.println("Please enter Account Holder AccountNo.");
Long accountNo=sc.nextLong();
account.setAccountNo(accountNo);

System.out.println("Please enter Account Holder MobileNo.");
Long mobileno=sc.nextLong();
account.setMobileno(mobileno);
sc.nextLine();
System.out.println("Please enter Account Holder Email");
String email=sc.nextLine();
account.setEmail(email);

System.out.println("Please enter Account Holder Address");
String address=sc.nextLine();
account.setAddress(address);

System.out.println("Please enter Account Holder Account balance");
Long accountBalance=sc.nextLong();
account.setAccountBalance(accountBalance);

System.out.println("Please enter Account Holder Credit Amount");
Long creditAmount=sc.nextLong();
account.setCreditAmount(creditAmount);

System.out.println("Please enter Account Holder Debit Amount");
Long debitAmount=sc.nextLong();
account.setDebitAmount(debitAmount);

as.open(account);

mhome();  // banking process return

sc.close();
}

private void viewAccountById() {
	System.out.println("You have choosen viewAccountById Process");
	System.out.println("=========================================");
	Account account  = new Account();
	sc.nextLine();
	System.out.println("Enter the UserName: ");
	String username =sc.nextLine();
	account.setEmail(username);
	
	as.viewAccountById(account);
	
	mhome(); //banking process return

}

private void viewAccountByNo() {
	System.out.println("You have choosen viewAccountbyNo Process");
	System.out.println("=========================================");
	System.out.println("Enter the AccountNo : ");
	Long accountNo=sc.nextLong();

	as.viewAccountByNo(accountNo);
	
	mhome(); //banking process return
}
private void mupdate() {
	System.out.println("You have choosen Account updation Process");
	System.out.println("=========================================");
	
	Account account=new Account();
	
	System.out.println("Choose the update Account number :");
	long choose = sc.nextLong();
	account.setAccountNo(choose);
	System.out.println("Enter Mobile No. to update :");	
	Long mobileno=sc.nextLong();
	account.setMobileno(mobileno);
	sc.nextLine();
	System.out.println("Enter Address for updatation :");	
	String address=sc.nextLine();
	account.setAddress(address);
	
	as.mupdate(account);
	
	mhome(); // banking process return
}

private void deposit() {
	System.out.println("You have choosen Deposit Account process");
	System.out.println("=========================================");
	Account account  = new Account();
	System.out.println("Enter the Account No:");
	long accountno = sc.nextLong();
	account.setAccountNo(accountno);
	System.out.println("Enter deposit Amount");
	long depamount = sc.nextLong();
	account.setCreditAmount(depamount);
	
	as.deposit(account);
	
	mhome(); //banking process return
}


private void debit() {
	System.out.println("You have choosen Debit Account process");
	System.out.println("=========================================");
	Account account  = new Account();
	System.out.println("Enter the Account No:");
	long accountno = sc.nextLong();
	account.setAccountNo(accountno);
	System.out.println("Enter debit Amount");
	long debamount = sc.nextLong();
	account.setDebitAmount(debamount);
	
	as.debit(account);
	
	mhome(); //banking process return
}

private void delete() {
	System.out.println("You have choosen Account Delete Process");
	System.out.println("=========================================");
	Account account  = new Account();
	sc.nextLine();
	System.out.println("Enter the account NO : ");
	long accountno = sc.nextLong();
	account.setAccountNo(accountno);
	sc.nextLine();
	System.out.println("Y/N");
	String in = sc.nextLine();
	String choice = in.toLowerCase();
	if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
		as.delete(account);
	}else {
		mhome(); //banking process return
	}
}


//  this common method

private void viewAllTransactions() {
	System.out.println("You have choosen viewAll transactions histroy");
	System.out.println("=========================================");
	Account account  = new Account();
	sc.nextLine();
	System.out.println("Enter the account NO : ");
	long accountno = sc.nextLong();
	account.setAccountNo(accountno);
	
	as.transactions(account);
	
	mhome(); //banking process return
	
}

}
