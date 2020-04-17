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

	void saveEmployee(Employee e);

	Employee getEmployeeBySsn(String ssn);

	Employee checkManager(String ssn);

}
