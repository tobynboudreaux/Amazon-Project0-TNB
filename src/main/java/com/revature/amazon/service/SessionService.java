package com.revature.amazon.service;

import com.revature.amazon.db.UserDB;

public class SessionService {

	private String email;
    private String password;
    
    public SessionService() {
        
    }
	
	public SessionService(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean validateUser() {
		return new UserDB().validEmail(getEmail(), getPassword());
	}
	
	@Override
	public String toString() {
		return "SessionsService [email=" + email + ", password=" + password + "]";
	}

	
}
