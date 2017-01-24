package com.incture.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.incture.dao.imp.EmployeeDaoImp;
import com.incture.model.Employee;
import com.incture.model.Status;

@Path("/Employees")
public class EmployeeService {

	private EmployeeDaoImp employeeService;

	// C R U D operation for Employees

	// CREATE Employee Details
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Status addEmpDetails(Employee employee) {

		employeeService = new EmployeeDaoImp();
		System.out.println("Inside Service Class");

		int empId=employeeService.create(employee);
		
		Status status=new Status();
		if(empId!=0){
			status.setStatusCode(200);
			status.setStatus("Created");
			status.setEmpId(empId);
		}
		return status;
	}

	// READ Employee Details
	@GET
	@Path("/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmpDetails(@PathParam("empId") int empId) {

		employeeService = new EmployeeDaoImp();
		System.out.println("Inside Service Class");
		Employee emp=null;
		try {
			//jsonInString = mapper.writeValueAsString(employeeService.read(empId));
			emp=employeeService.read(empId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}

	// UPDATE Employee Details
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Status updateEmpDetails(Employee emp) {

		employeeService = new EmployeeDaoImp();
		System.out.println("Inside Service Class");
		Status status=new Status();
		int empId=emp.getEmpId();
		try {
			empId = employeeService.update(emp);
			if(empId!=0){
				status.setStatusCode(200);
				status.setStatus("Updated");
				status.setEmpId(empId);
			}else{
				status.setStatusCode(200);
				status.setStatus("Employee "+empId + " details not present in data base");
				status.setEmpId(empId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	// @DELETE Employee Details
	@DELETE
	@Path("/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Status deleteEmpDetails(@PathParam("empId") int empId) {

		employeeService = new EmployeeDaoImp();
		System.out.println("Inside Service Class");
		Status status=new Status();
		try {
			empId = employeeService.delete(empId);
			if(empId!=0){
				status.setStatusCode(200);
				status.setStatus("Deleted");
				status.setEmpId(empId);
			}else{
				status.setStatusCode(200);
				status.setStatus("Employee "+empId + " details not present in data base");
				status.setEmpId(empId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	// Read All Employee Data
	@GET
	@Path("/readall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmpDetails() {

		EmployeeDaoImp employeeService = new EmployeeDaoImp();
		System.out.println("Inside Service Class");
		List<Employee> empList = null;

		try {
			empList=employeeService.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empList;
	}
}
