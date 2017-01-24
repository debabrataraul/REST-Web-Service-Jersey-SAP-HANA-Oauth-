package com.incture.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.incture.dao.EmployeeDao;
import com.incture.entity.EmployeeEntity;
import com.incture.model.Employee;
import com.incture.utility.DBConnection;

public class EmployeeDaoImp implements EmployeeDao {

	public int create(Employee employee) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction et = null;
		EmployeeEntity emp = null;
		try {
			emf = DBConnection.getConnection();
			em = emf.createEntityManager();
			et = em.getTransaction();

			emp = new EmployeeEntity();

			pojoToentity(employee, emp);

			System.out.println(emp);

			et.begin();
			em.persist(emp);
			et.commit();

		} catch (Exception e) {
			System.out.println("Unable to insert");
		}
		if (emp.getEmpId()!=0)
			return emp.getEmpId();
		else
			return 0;
	}

	public int update(Employee employee) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction et = null;
		int empId = employee.getEmpId();
		try {
			emf = DBConnection.getConnection();
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			EmployeeEntity emp = em.find(EmployeeEntity.class, empId);
			if (emp != null) {
				emp = pojoToentity(employee, emp);
			} else {
				empId = 0;
			}
			emp = pojoToentity(employee, emp);
			// before update
			System.out.println(emp);
			et.commit();
			// after update
			System.out.println(emp);

		} catch (Exception e) {
			// TODO: handle exception
		}
		em.close();
		emf.close();
		return empId;
	}

	public Employee read(int empId) {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		EmployeeEntity employeeEntity = null;
		try {
			emf = DBConnection.getConnection();
			em = emf.createEntityManager();
			employeeEntity = em.find(EmployeeEntity.class, empId);

			if (employeeEntity == null)
				return null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return entityToPojo(employeeEntity);

	}

	public int delete(int empId) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			emf = DBConnection.getConnection();
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			EmployeeEntity emp = em.find(EmployeeEntity.class, empId);
			if (emp != null) {
				em.remove(emp);
			} else {
				empId = 0;
			}
			et.commit();
			em.close();
			emf.close();
		} catch (Exception e) {
			System.out.println("Error");
		}

		return empId;
	}

	public List<Employee> readAll() {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		List<EmployeeEntity> empEList = null;
		List<Employee> empList = null;

		try {
			emf = DBConnection.getConnection();
			em = emf.createEntityManager();


			Query query = em.createNativeQuery(
					"SELECT e.empId, e.firstName, e.lastName, e.email, e.mobile, "
					+ "e.designation, e.department, e.address from employee e",
					EmployeeEntity.class);

			empEList = (List<EmployeeEntity>) query.getResultList();

			empList = new ArrayList<>();
			for (EmployeeEntity empE : empEList) {

				empList.add(entityToPojo(empE));
			}

		} catch (Exception e) {
			System.out.println("error");
		}
		return empList;

	}

	public Employee entityToPojo(EmployeeEntity employeeEntity) {
		Employee employee = new Employee();

		employee.setEmpId(employeeEntity.getEmpId());
		employee.setFirstName(employeeEntity.getFirstName());
		employee.setLastName(employeeEntity.getLastName());
		employee.setEmail(employeeEntity.getEmail());
		employee.setMobile(employeeEntity.getMobile());
		employee.setDesignation(employeeEntity.getDesignation());
		employee.setDepartment(employeeEntity.getDepartment());
		employee.setAddress(employeeEntity.getAddress());

		return employee;
	}

	public EmployeeEntity pojoToentity(Employee employee, EmployeeEntity employeeEntity) {

		// employeeEntity.setEmpId(employee.getEmpId());
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
		employeeEntity.setEmail(employee.getEmail());
		employeeEntity.setMobile(employee.getMobile());
		employeeEntity.setDesignation(employee.getDesignation());
		employeeEntity.setDepartment(employee.getDepartment());
		employeeEntity.setAddress(employee.getAddress());

		return employeeEntity;
	}

}
