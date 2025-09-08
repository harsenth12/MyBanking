package com.techie.mybank.Controller;

import java.util.Scanner;

import com.techie.mybank.model.Login;
import com.techie.mybank.service.LoginService;
import com.techie.mybank.service.LoginServiceImpl;

public class LoginController {
	Scanner sc=new Scanner(System.in);
	LoginService los=new LoginServiceImpl();
			
	public void login() {
		System.out.println("Welcome to MyBank!");
		System.out.println("Please login with valid credentials!");
		System.out.println("Please enter UserName:");
		Login log=new Login();
		String userName=sc.next();
		log.setUserName(userName);
	
		System.out.println("Please enter password:");
		String password=sc.next();
		log.setPassword(password);
		
		boolean active = true;
		log.setActive(active);
		boolean delete = false;
		log.setDelete(delete);
		los.logins(log);
	}
	
	
	public void managerlogin() {
		System.out.println("Please login with valid credentials!");
		System.out.println("Please enter UserName:");
		Login mlog=new Login();
		String userName=sc.next();
		mlog.setUserName(userName);
	
		System.out.println("Please enter password:");
		String password=sc.next();
		mlog.setPassword(password);		
		los.mlogins(mlog);
	}
	
	public void findusername() {
		System.out.println("Find your username");
		System.out.println("Please enter UserName:");
		Login umlog=new Login();
		String userName=sc.next();
		umlog.setUserName(userName);
		
		los.findbyuserName(umlog);
		
	}

}
