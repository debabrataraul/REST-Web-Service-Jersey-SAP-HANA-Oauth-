package com.incture.dao;

import com.incture.entity.EmployeeEntity;
import com.incture.model.Employee;

public interface EmployeeDao {
	
	public  int create(Employee employee);
	public Employee read(int empId);
	public int update (Employee employee);
	public int delete(int empId);
	
	public Employee entityToPojo(EmployeeEntity employeeEntity);
	public EmployeeEntity pojoToentity(Employee employee,EmployeeEntity employeeEntity);

}
