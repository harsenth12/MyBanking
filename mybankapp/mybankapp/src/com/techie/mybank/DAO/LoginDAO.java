package com.techie.mybank.DAO;

import com.techie.mybank.model.Login;

public interface LoginDAO {
	// User login daos
	public long logins(Login log);
	
	
	// Bank ManageMent daos
	public boolean mlogins(Login mlog);
	public long create(Login login);
	public Login update(Login log);
	public int findbyuserName(Login log);
	public Login activate(Login log);
	public Login deactivate(Login log);
	public Login delete(Login log);

	
	
	
	
	
}
