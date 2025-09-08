package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.techie.mybank.model.Login;
import com.techie.mybank.utils.BankDatabase;

public class LoginDAOImpl implements LoginDAO{
	
	BankDatabase bd=new BankDatabase();
	
	
	@Override
	public long logins(Login log) {
		Connection comm=bd.getConnection();
		long result = 0;

		try {
			String sql = "select * from login where user_name = ? and is_active = ? and is_delete = ? ";
			PreparedStatement ps = comm.prepareStatement(sql);
			ps.setString(1, log.getUserName());
			ps.setBoolean(2, log.getisActive());
			ps.setBoolean(3, log.getisDelete());
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				if(res.getString("user_name").equals(log.getUserName()) && res.getString("password").equals(log.getPassword())) {
					result = res.getLong("accountid");
				}
			}
			comm.close();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public boolean mlogins(Login mlog) {
		Connection comm=bd.getConnection();
		boolean result = false;

		try {
			String sql = "select * from managerlogin where username=?";
			PreparedStatement stmt=comm.prepareStatement(sql);
			stmt.setString(1, mlog.getUserName());
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				if(res.getString("username").equals(mlog.getUserName()) && res.getString("password").equals(mlog.getPassword())) {
					result = true;
				}
				
			}
			comm.close();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	@Override
	public long create(Login login) {
		Connection comm=bd.getConnection();
		long out = 0;
		try {
		String sql = "INSERT INTO login(user_name, password, is_active, is_delete, accountid) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt = comm.prepareStatement(sql);
		stmt.setString(1, login.getUserName());
		stmt.setString(2, login.getPassword());
		stmt.setBoolean(3, login.getisActive());
		stmt.setBoolean(4, login.getisDelete());
		stmt.setLong(5, login.getAccountId());

		out = stmt.executeUpdate();

		comm.close();	
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		return out;
	}
	
	
	@Override
	public int findbyuserName(Login log) {
	
		Connection comm=bd.getConnection();
		int result = 0;
		try {
			Statement stmt=comm.createStatement();	
			String sql = "select * from login where user_name="+log.getUserName();				
			result = stmt.executeUpdate(sql);
			comm.close();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public Login update(Login log) {		
		return null;
	}

	

	@Override
	public Login activate(Login log) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login deactivate(Login log) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login delete(Login log) {
		// TODO Auto-generated method stub
		return null;
	}

}
