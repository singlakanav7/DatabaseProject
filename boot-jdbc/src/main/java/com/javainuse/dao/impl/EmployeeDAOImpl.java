package com.javainuse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javainuse.connection.OracleJdbcConnection;
import com.javainuse.model.Employee;
import org.apache.log4j.Logger;

/**
 * Employee DAO Implementation
 * 
 * @author Kanav Singla
 *
 */
public class EmployeeDAOImpl implements EmployeeDAO {

	static Logger logger = Logger.getLogger(EmployeeDAO.class);

	private Connection conn = OracleJdbcConnection.getDatabaseConnection();
	private static final String insertQuery = "insert into employee(fname,minit,lname,ssn,bdate,address,sex,salary,superssn,dno,email) values (?,?,?,?,?,?,?,?,?,?,?)";

	public List<Employee> getEmployees(String ssn) {

		if (ssn == null) {
			logger.error("the ssn entered is null");
		}
		String selectQuery = "select * from employee where superssn =" + ssn;
		List<Employee> list = new ArrayList<Employee>();

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(selectQuery);

			while (result.next()) {
				Employee e = new Employee();
				e.setFirstName(result.getString(1));
				e.setmInt(result.getString(2));
				e.setLastName(result.getString(3));
				e.setSsn(result.getString(4));
				e.setBdate(result.getDate(5));
				e.setAddress(result.getString(6));
				e.setSex(result.getString(7));
				e.setSalary(result.getString(8));
				e.setSuperssn(result.getString(9));
				e.setDno(result.getString(10));
				e.setEmail(result.getString(11));
				list.add(e);
			}

			logger.info("Employee returned to the application successfully");
			logger.info(list);

		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
		}

		return list;
	}

	public void saveEmployee(Employee e) throws Exception {

		if (e == null) {
			logger.error("The employee object is null");
		} else {
			logger.info("Saving emmployee");
		}

		try {
			PreparedStatement statement = conn.prepareStatement(insertQuery);
			statement.setString(1, e.getFirstName());
			statement.setString(2, e.getmInt());
			statement.setString(3, e.getLastName());
			statement.setString(4, e.getSsn());
			statement.setDate(5, e.getBdate());
			statement.setString(6, e.getAddress());
			statement.setString(7, e.getSex());
			statement.setString(8, e.getSalary());
			statement.setString(9, e.getSuperssn());
			statement.setString(10, e.getDno());
			statement.setString(11, e.getEmail());

			ResultSet rowInserted = statement.executeQuery();
			if (rowInserted == null) {
				logger.warn("Insertion Unsuccessfull");
			} else {
				logger.info("Insertion Successfull");
			}
		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
		}

	}

	public Employee checkManager(String ssn) {

		if (ssn == null) {
			logger.error("The ssn enetered is null");
		}
		Employee e = new Employee();
		String query = "select * from employee where superssn = " + ssn;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				e.setFirstName(result.getString(1));
				e.setmInt(result.getString(2));
				e.setLastName(result.getString(3));
				e.setSsn(result.getString(4));
				e.setBdate(result.getDate(5));
				e.setAddress(result.getString(6));
				e.setSex(result.getString(7));
				e.setSalary(result.getString(8));
				e.setSuperssn(result.getString(9));
				e.setDno(result.getString(10));
				e.setEmail(result.getString(11));
			}

		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
		}
		return e;

	}

	public Employee getEmployeeBySsn(String ssn) {

		if (ssn == null) {
			logger.error("The ssn enetered is null");
		}
		Employee e = new Employee();

		String query = "select * from employee where ssn = " + ssn;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				e.setFirstName(result.getString(1));
				e.setmInt(result.getString(2));
				e.setLastName(result.getString(3));
				e.setSsn(result.getString(4));
				e.setBdate(result.getDate(5));
				e.setAddress(result.getString(6));
				e.setSex(result.getString(7));
				e.setSalary(result.getString(8));
				e.setSuperssn(result.getString(9));
				e.setDno(result.getString(10));
				e.setEmail(result.getString(11));
			}
			logger.info("The employee information send through the object of the employee");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
		return e;

	}

	public static void printEmployee(Employee e) {
		if (e == null) {
			logger.error("The Employee Object is null");
		}
		logger.info(e.getFirstName() + "  " + e.getLastName() + "  " + e.getSsn() + " " + e.getBdate() + "  "
				+ e.getAddress() + " " + e.getSex() + "  " + e.getSalary() + "   " + e.getSuperssn() + "  " + e.getDno()
				+ "  " + e.getEmail());

	}

	@Override
	public void deleteEmployee(String ssn) {
		if (ssn == null) {
			logger.error("The ssn enetered is null");
		}
		String query = "delete from employee where ssn = " + ssn;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();
			statement.executeQuery(query);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
	}

	@Override
	public void updateEmployee(Employee e) {
		if (e == null) {
			logger.error("The Employee Object entered is null");
		}
		String query = "update employee " + "set fname ='" + e.getFirstName() + "', minit ='" + e.getmInt()
				+ "', lname ='" + e.getLastName() + "' ,bdate ='" + e.getBdate() + "' ,address ='" + e.getAddress()
				+ "' ,sex ='" + e.getSex() + "' ,salary ='" + e.getSalary() + "' ,superssn ='" + e.getSuperssn()
				+ "' ,dno ='" + e.getDno() + "' ,email ='" + e.getEmail() + "' where ssn =" + e.getSsn();
		try {
			Statement statement = conn.createStatement();
			statement.executeQuery(query);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
	}

}
