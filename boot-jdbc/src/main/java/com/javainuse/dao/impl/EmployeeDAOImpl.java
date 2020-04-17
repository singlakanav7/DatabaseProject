package com.javainuse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javainuse.connection.OracleJdbcConnection;
import com.javainuse.dao.impl.EmployeeDAO;
import com.javainuse.model.Employee;

/**
 * Employee DAO Implementation
 * 
 * @author Kanav Singla
 *
 */
public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection conn = OracleJdbcConnection.getDatabaseConnection();
	private static final String insertQuery = "insert into employee(fname,minit,lname,ssn,bdate,address,sex,salary,superssn,dno,email) values (?,?,?,?,?,?,?,?,?,?,?)";

	public List<Employee> getEmployees(String ssn) {
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
				e.setBdate(result.getString(5));
				e.setAddress(result.getString(6));
				e.setSex(result.getString(7));
				e.setSalary(result.getString(8));
				e.setSuperssn(result.getString(9));
				e.setDno(result.getString(10));
				e.setEmail(result.getString(11));
				list.add(e);
			}

			for (Employee print : list) {
				System.out.println(print.toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return list;
	}

	public void saveEmployee(Employee e) {

		try {
			PreparedStatement statement = conn.prepareStatement(insertQuery);
			statement.setString(1, e.getFirstName());
			statement.setString(2, e.getmInt());
			statement.setString(3, e.getLastName());
			statement.setString(4, e.getSsn());
			statement.setString(5, e.getBdate());
			statement.setString(6, e.getAddress());
			statement.setString(7, e.getSex());
			statement.setString(8, e.getSalary());
			statement.setString(9, e.getSuperssn());
			statement.setString(10, e.getDno());
			statement.setString(11, e.getEmail());

			ResultSet rowInserted = statement.executeQuery();
			if (rowInserted == null) {
				System.out.println("Insertion Unsuccessfull");
			} else {
				System.out.println("Insertion Successfull");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public Employee checkManager(String ssn) {
		Employee e = new Employee();
		String query = "select * from employee where superssn = " + ssn;
		System.out.println(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				e.setFirstName(result.getString(1));
				e.setmInt(result.getString(2));
				e.setLastName(result.getString(3));
				e.setSsn(result.getString(4));
				e.setBdate(result.getString(5));
				e.setAddress(result.getString(6));
				e.setSex(result.getString(7));
				e.setSalary(result.getString(8));
				e.setSuperssn(result.getString(9));
				e.setDno(result.getString(10));
				e.setEmail(result.getString(11));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;

	}

	public Employee getEmployeeBySsn(String ssn) {
		Employee e = new Employee();
		String query = "select * from employee where ssn = " + ssn;
		System.out.println(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				e.setFirstName(result.getString(1));
				e.setmInt(result.getString(2));
				e.setLastName(result.getString(3));
				e.setSsn(result.getString(4));
				e.setBdate(result.getString(5));
				e.setAddress(result.getString(6));
				e.setSex(result.getString(7));
				e.setSalary(result.getString(8));
				e.setSuperssn(result.getString(9));
				e.setDno(result.getString(10));
				e.setEmail(result.getString(11));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;

	}

	public static void printEmployee(Employee e) {
		System.out.println(e.getFirstName() + "  " + e.getLastName() + "  " + e.getSsn() + " " + e.getBdate() + "  "
				+ e.getAddress() + " " + e.getSex() + "  " + e.getSalary() + "   " + e.getSuperssn() + "  " + e.getDno()
				+ "  " + e.getEmail());

	}

}
