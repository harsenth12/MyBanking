package com.techie.mybank.service;

import com.techie.mybank.model.Login;

public interface LoginService {
	
	// user control
	public long logins(Login log);
	
	// Banking management
	public boolean mlogins(Login mlog);
	public Login update(Login log);
	public Login findbyuserName(Login log);
	public Login activate(Login log);
	public Login deactivate(Login log);
	public Login delete(Login log);
}
