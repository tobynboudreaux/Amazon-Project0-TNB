package com.revature.amazon.service;

import com.revature.amazon.db.SetupDB;

public class SetupService {
	
	public SetupService() {

    }

    public String setupData() {
        SetupDB setupDB = new SetupDB();
        return setupDB.setupData();
    }
    
}