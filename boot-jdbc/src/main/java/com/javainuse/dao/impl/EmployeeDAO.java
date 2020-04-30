package com.javainuse.dao.impl;

import java.util.List;

import com.javainuse.model.Employee;

/**
 * Employee DAO Interface
 * 
 * @author Kanav Singla
 *
 */

public interface EmployeeDAO {

	List<Employee> getEmployees(String ssn);

	void saveEmployee(Employee e) throws Exception;

	Employee getEmployeeBySsn(String ssn);

	Employee checkManager(String ssn);

	void deleteEmployee(String ssn);
	
	void updateEmployee(Employee e,String ssn);

}
