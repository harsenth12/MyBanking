package com.techie.mybank.service;

import com.techie.mybank.Controller.LoginController;
import com.techie.mybank.DAO.LoginDAO;
import com.techie.mybank.DAO.LoginDAOImpl;
import com.techie.mybank.main.BankClient;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Login;

public class LoginServiceImpl implements LoginService {

	LoginDAO lod=new LoginDAOImpl();


	@Override
	public long logins(Login log) {
		long result = lod.logins(log);
		if(result > 0) {
			System.out.println("Login Successfully");
			BankClient bc = new BankClient();
			Account logac =new Account();
			logac.setAccountid(result);
			bc.ucorebanking(logac);

		}else {
			System.out.println("Login Unsuccessfully");
			LoginController obj = new LoginController();
			obj.login();
		}
		return result;
	}



	@Override
	public boolean mlogins(Login mlog) {
		boolean result = lod.mlogins(mlog);
		if(result == true) {
			System.out.println("Login Successfully");
			BankClient bc = new BankClient();
			Account logac =new Account();
			logac.setEmail(mlog.getUserName());
			bc.mcorebanking();

		}else {
			System.out.println("Login Unsuccessfully");
			LoginController obj = new LoginController();
			obj.managerlogin();
		}
		return false;
	}
	
	
	@Override
	public Login findbyuserName(Login log) {
		int result = lod.findbyuserName(log);
		if(result > 0) {
			System.out.println("UserName is valid");
		}else {
			System.out.println("UserName is invalid");
		}
		return null;
	}
	
	@Override
	public Login update(Login log) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Login delete(Login log) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Login deactivate(Login log) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Login activate(Login log) {
		// TODO Auto-generated method stub
		return null;
	}
}
