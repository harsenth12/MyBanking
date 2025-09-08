package com.techie.mybank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BankDatabase {
public Connection getConnection() {

	try {
		Class.forName("com.mysql.jdbc.Driver");
		 Connection comm=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybanking","root","admin");
		 		 return comm;
		
		
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
	
	
}






			static BankDatabase bd=new BankDatabase();
//System.out.println(bd.getConnection());
		
	
	public String  createtableloans(){
		Connection comm=bd.getConnection();
	try {
		Statement stmt=comm.createStatement();
		String sql="CREATE table loan_acc"+"(loan_id Integer PRIMARY KEY not Null AUTO_INCREMENT ,"+
		"loanNumber BIGINT(10),"+"name VARCHAR(45),"+"email VARCHAR(45),"+"mobileNo BIGINT(10),"+"address VARCHAR(45),loandate Varchar(10))";
	
		stmt.executeUpdate(sql);
		comm.close();			
	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
	}
}

