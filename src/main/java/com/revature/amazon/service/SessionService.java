package com.revature.amazon.service;
import com.revature.amazon.model.Role;
import com.revature.amazon.model.User;

import com.revature.amazon.db.UserDB;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.HTTP;

public class SessionService {
	private String email;
	private String password;
	private Role role;
	private int id;

	public SessionService() {

	}

	public SessionService(String email, String password) {
		this.email = email;
		this.password = password;

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



	// public void setRole(Role role) {
	// 	this.role = role;

	// }

	// public int getId() {
	// 	return this.id;

	// }

	// public void setId(int id) {
	// 	this.id = id;

	// }

	public User validateUser(JSONObject jsonObject) {
		String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
		return new UserDB().validEmail(email, password);

	}
	
	@Override
	public String toString() {
		return "SessionsService [email=" + email + ", password=" + password + "]";
	
	}
}
