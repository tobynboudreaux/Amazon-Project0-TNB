package com.revature.amazon.service;
import com.revature.amazon.model.Role;
import com.revature.amazon.model.User;

import com.revature.amazon.db.UserDB;

public class SessionService {
	private String email;
	private String password;
	private Role role;

	public SessionService() {

	}
	
	public SessionService(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;

	}

	public String getEmail() {
		return this.email;

	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getPassword() {
		return this.password;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getRole() {
		if (this.role == null) {
			return null;
			
		} else {
			return this.role.getRole();

		}
	}

	public void setRole(Role role) {
		this.role = role;

	}

	public User validateUser() {
		return new UserDB().validEmail(getEmail(), getPassword(), getRole());

	}
	
	@Override
	public String toString() {
		return "SessionsService [email=" + email + ", password=" + password + ", role=" + role + "]";
	
	}
}
