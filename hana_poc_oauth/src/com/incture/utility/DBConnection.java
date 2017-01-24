package com.incture.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {
	public static EntityManagerFactory getConnection() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("application");
		return emf;
	}
	
}