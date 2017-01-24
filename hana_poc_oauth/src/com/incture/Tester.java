package com.incture;

import com.incture.dao.imp.EmployeeDaoImp;

public class Tester {

	public static void main(String[] args) {

		/*Employee employee = new Employee(101, "Debabrata", "Raul", "debabrata.raul@gmail.com", "9035006260",
				"Consultant - Technology", "IT", "Electronics City");*/
		
		EmployeeDaoImp employeeService=new EmployeeDaoImp();
		
		System.out.println(employeeService.read(101));

	}

}
