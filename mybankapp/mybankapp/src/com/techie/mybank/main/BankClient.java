package com.techie.mybank.main;

import java.util.Scanner;

import com.techie.mybank.Controller.AccountController;
import com.techie.mybank.Controller.CardsController;
import com.techie.mybank.Controller.LoansController;
import com.techie.mybank.Controller.LoginController;
import com.techie.mybank.model.Account;

public class BankClient {
	
	static boolean x = true;
	public static void main(String[] args) {
		System.out.println("!@#$..........................................$#@!");
		do {
		mainlogin();
		}while(x == true);
	}
	
	public static void mainlogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1.You have choose to User login.");
		System.out.println("2.You have choose to Bank Worker login.");
		System.out.println("3.Find your username");
		System.out.println("4.Exit");
		System.out.println("================================================== ");
		System.out.println("Please choose the desired Option:");
		int choice = scan.nextInt();
	switch(choice) {
	case 1:
		System.out.println("You have choosen to User login.");
		login();
		break;
	case 2:
		System.out.println("You have choosen to Bank Worker login.");
		managerlogin();
		break;
	case 3:
		System.out.println("You have choosen to Find Username.");
		LoginController obj = new LoginController();
		obj.findusername();
		break;
	case 4:
		System.out.println("You have choosen to Exit");
		x = false;
		break;
	default:
		System.out.println("Input invalid");
		mainlogin();
	}
	scan.close();
	}
	
	private static void login() {
		LoginController lc=new LoginController();
		lc.login();
	}
	
	public void ucorebanking(Account logac) {
		ucoreBanking(logac);
	}


	private static void ucoreBanking(Account logac) {
		System.out.println("Welcome to Fire Bank!");
		System.out.println("================================================== ");
		System.out.println("Please choose the desired Option: ");
		System.out.println("================================================== ");

		System.out.println("Select Option 1 to process login: ");
		System.out.println("Select Option 2 if you want to process an account: ");
		System.out.println("Select Option 3 if you want to process creditcards: ");
		System.out.println("Select Option 4 if you want to process loans: ");
		System.out.println("Select Option 5 if you want to Exit in User process Section");
		
		
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();

		switch (option) {
		case 1:
			System.out.println("You have choosen to login . ");
			LoginController loc = new LoginController();
			loc.login();
			break;
		case 2:
			System.out.println("You have choosen Accounting Operations");
			System.out.println("================================================== ");
			AccountController ac = new AccountController();
			ac.home(logac);
			break;
		case 3:
			System.out.println("You have choosen Creditcard Process");
			System.out.println("================================================== ");
			CardsController cc = new CardsController();
			cc.home(logac);
			break;
		case 4:
			System.out.println("You have choosen Loan Process");
			System.out.println("================================================== ");
			LoansController ln = new LoansController();
			ln.home(logac);
			break;
		case 5:
			System.out.println("You have choosen Exit process");
			System.out.println("================================================== ");
			mainlogin();
			break;
		default:
			System.out.println("Correct input choose");
			System.out.println("================================================== ");
			ucoreBanking(logac);
		}
		sc.close();
	}
	
	
	// Bank management login
	
	private static void managerlogin() {
		LoginController lc=new LoginController();
		lc.managerlogin();
	}
	

	public void mcorebanking() {
		mcoreBanking();
	}
	
	private static void mcoreBanking() {
		System.out.println("================================================== ");
		System.out.println("Please choose the desired Option: ");
		System.out.println("================================================== ");

		System.out.println("Select Option 1 to process login: ");
		System.out.println("Select Option 2 if you want to process an account: ");
		System.out.println("Select Option 3 if you want to process creditcards: ");
		System.out.println("Select Option 4 if you want to process loans: ");
		System.out.println("Select Option 5 if you want to Exit Bank Worker Section");
		
		
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("You have choosen to login . ");
			LoginController loc = new LoginController();
			loc.managerlogin();
			break;
		case 2:
			System.out.println("You have choosen Accounting Operations");
			System.out.println("================================================== ");
			AccountController ac = new AccountController();
			ac.mhome();
			break;
		case 3:
			System.out.println("You have choosen Creditcard Process");
			System.out.println("================================================== ");
			CardsController cc = new CardsController();
			cc.mhome();
			break;
		case 4:
			System.out.println("You have choosen Loan Process");
			System.out.println("================================================== ");
			LoansController ln = new LoansController();
			ln.mhome();
			break;
		case 5:
			System.out.println("You have choosen Exit");
			System.out.println("================================================== ");
			mainlogin();
			break;
		default:
			System.out.println("Correct input choose");
			System.out.println("================================================== ");
			mcoreBanking();
		}
		sc.close();
	}
}
