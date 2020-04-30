package com.javainuse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javainuse.connection.OracleJdbcConnection;
import com.javainuse.model.Employee;

/**
 * Employee DAO Implementation
 * 
 * @author Kanav Singla
 *
 */
public class EmployeeDAOImpl implements EmployeeDAO {

	static Logger logger = Logger.getLogger(EmployeeDAO.class);
	Map<String, Employee> map = new HashMap<>();

	private Connection conn = OracleJdbcConnection.getDatabaseConnection();
	private static final String insertQuery = "insert into employee(fname,minit,lname,ssn,bdate,address,sex,salary,superssn,dno,email) values (?,?,?,?,?,?,?,?,?,?,?)";

	public List<Employee> getEmployees(String ssn) {
		ResultSet result = null;
		Statement statement = null;

		if (ssn == null) {
			logger.error("the ssn entered is null");
		}
		String selectQuery = "select * from employee where superssn =" + ssn;
		List<Employee> list = new ArrayList<Employee>();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectQuery);

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
		} finally {
			try {
				result.close();
				statement.close();

			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
		return list;
	}

	public Map<String, Employee> getEmployeesAndLoadinMemory() {
		ResultSet result = null;
		Statement statement = null;
		Map<String, Employee> cache = new HashMap<String, Employee>();
		List<Employee> emp = new ArrayList<>();
		String selectQuery = "select * from employee";

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectQuery);

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
				cache.put(e.getSsn(), e);
			}

		} catch (SQLException e1) {
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				result.close();
				statement.close();

			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
		return cache;
	}

	public void insertIntoLocalMemory(String ssn, Employee e) {

		map.put(ssn, e);

	}

	public void saveEmployee(Employee e) throws Exception {

		boolean val = true;

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

	public ResponseEntity<?> employeeValidations(String ssn) {

		// Checking the null case
		if (ssn.equals(null) || ssn.length() == 0) {
			logger.warn("Ssn is null");
			return new ResponseEntity<>("Ssn is null ", HttpStatus.BAD_REQUEST);
		}

		if (!isDigit(ssn)) {
			logger.warn("Ssn should only be digits");
			return new ResponseEntity<>("Ssn should only be digits", HttpStatus.BAD_REQUEST);
		}

		if (ssn.length() != 9) {
			logger.warn("Ssn length should be 9 digits only");
			return new ResponseEntity<>("Ssn should be only 9 digits ", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	public ResponseEntity<?> employeeInsertValidations(Employee employee) {

		boolean superval = true;

		if (employee.getSsn() == null) {
			logger.info("Employee Information is null");
			return new ResponseEntity<>("Employee Information is null", HttpStatus.BAD_REQUEST);
		}
		if (employee.getFirstName() == null || employee.getFirstName().length() > 15) {
			logger.info("First Name should be less than 15 Characters /First name is null");
			return new ResponseEntity<>("First Name should be less than 15 Characters", HttpStatus.BAD_REQUEST);
		}
		if (!isCharacter(employee.getFirstName())) {
			logger.warn("First Name should only be characters");
			return new ResponseEntity<>("First Name should only be characters", HttpStatus.BAD_REQUEST);
		}
		if (employee.getmInt() == null || employee.getmInt().length() != 1) {
			logger.info("Minit should be not more than one Character/ Minit is null  ");
			return new ResponseEntity<>("Minit should be not more than one Character ", HttpStatus.BAD_REQUEST);
		}
		if (!isCharacter(employee.getmInt())) {
			logger.warn("Mint should only be characters");
			return new ResponseEntity<>("Mint should only be characters", HttpStatus.BAD_REQUEST);
		}

		if (employee.getLastName() == null || employee.getLastName().length() > 15) {
			logger.info("Last Name should be less than 16 Characters/Last Name is null");
			return new ResponseEntity<>("First Entered Exceed Maximum Length", HttpStatus.BAD_REQUEST);
		}
		if (!isCharacter(employee.getLastName())) {
			logger.warn("Last Name should only be characters");
			return new ResponseEntity<>("Last Name should only be characters", HttpStatus.BAD_REQUEST);
		}

		if (employee.getAddress() == null || employee.getAddress().length() > 30) {
			logger.info("Address should be less than 30 Characters/Address is null");
			return new ResponseEntity<>("Address should be less than 30 Characters", HttpStatus.BAD_REQUEST);
		}
		if (employee.getSex() == null || employee.getSex().length() != 1) {
			logger.info("Sex should be only one Character/ Sex is null");
			return new ResponseEntity<>("Sex should be only one Character", HttpStatus.BAD_REQUEST);
		}
		if (!isCharacter(employee.getSex())) {
			logger.warn("Sex should only be characters");
			return new ResponseEntity<>("Sex should only be characters", HttpStatus.BAD_REQUEST);
		}
		if (employee.getSalary() == null || employee.getSalary().length() > 10) {
			logger.info("Salary should be only 10 digits only/ Salary is null");
			return new ResponseEntity<>("Salary should be only 10 digits only", HttpStatus.BAD_REQUEST);
		}
		if (!isDigit(employee.getSalary())) {
			logger.info("Salary should be digits only");
			return new ResponseEntity<>("Salary should be digits only", HttpStatus.BAD_REQUEST);
		}

		if (employee.getSuperssn() == null) {
			logger.info("Superssn is null, you will be assigned as new Manger");
			superval = false;
		}
		if (superval && employee.getSuperssn().length() != 9) {
			logger.info("SuperSsn should not be more than nine Characters");
			return new ResponseEntity<>("SuperSsn should not be more than nine Characters", HttpStatus.BAD_REQUEST);
		}
		if (superval && !isDigit(employee.getSuperssn())) {
			logger.warn("SuperSsn should only be characters");
			return new ResponseEntity<>("Superssn should only be characters", HttpStatus.BAD_REQUEST);
		}
		if (employee.getDno() == null || employee.getDno().length() > 4) {
			logger.info("Dno should cane be only 4 Digits/Dno is null");
			return new ResponseEntity<>("Dno should can be only 4 Digits", HttpStatus.BAD_REQUEST);
		}
		if (!isDigit(employee.getDno())) {
			logger.info("Dno should be digits only");
			return new ResponseEntity<>("Dno should be digits only", HttpStatus.BAD_REQUEST);
		}

		if (employee.getEmail() == null || employee.getEmail().length() > 50) {
			logger.info("Email should can be only 50 Characters/Email is null");
			return new ResponseEntity<>("Email should can be only 50 Characters", HttpStatus.BAD_REQUEST);
		}
		if (!isEmail(employee.getEmail())) {
			logger.info("EMail should be correct format only");
			return new ResponseEntity<>("EMail should be correct format only", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	public ResponseEntity<?> cachingRefresh(Map<String, Employee> cache, Map<String, List<Employee>> managerCache,
			Employee employee, String query) {

		// update

		if (query == "update") {
			if (managerCache.containsKey(employee.getSuperssn())) {
				managerCache.get(employee.getSuperssn()).remove(employee.getSsn());
				managerCache.get(employee.getSuperssn()).add(employee);
			} else {
				List<Employee> list = new ArrayList<>();
				managerCache.put(employee.getSsn(), list);
			}
		}

		// insert caching
		if (query == "insert") {

			if (managerCache.containsKey(employee.getSuperssn())) {
				managerCache.get(employee.getSuperssn()).add(employee);
			} else {
				List<Employee> list = new ArrayList<>();
				managerCache.put(employee.getSsn(), list);
			}
			cache.put(employee.getSsn(), employee);
		}

		if (query == "delete") {
			if (managerCache.containsKey(employee.getSsn())) {
				managerCache.remove(employee.getSsn());
			}
			cache.remove(employee.getSsn());
		}
		return null;
	}

	public Boolean isDigit(String attribute) {

		char[] alpha = attribute.toCharArray();

		for (char a : alpha) {
			if (Character.isLetter(a - '0')) {
				return false;
			}
		}

		return true;
	}

	public boolean isEmail(String attribute) {

		String regex = "^(.+)@(.+)$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(attribute);

		return matcher.matches();
	}

	public Boolean isCharacter(String attribute) {

		char[] alpha = attribute.toCharArray();

		for (char a : alpha) {
			if (Character.isDigit(a)) {
				return false;
			}
		}

		return true;
	}

	public Employee checkManager(String ssn) {
		Statement statement = null;
		ResultSet result = null;
		if (ssn == null) {
			logger.error("The ssn enetered is null");
		}
		Employee e = new Employee();
		String query = "select * from employee where superssn = " + ssn;
		logger.info(query);
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(query);

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
		} finally {
			try {
				result.close();
				statement.close();

			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
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
		String query = "delete employee where ssn =" + ssn;
		String query1 = "delete dependent where essn = " + ssn;
		String query2 = "delete works_on where essn =" + ssn;
		String query3 = "delete department where mgrssn=" + ssn;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();

			statement.executeQuery(query1);

			statement.executeQuery(query2);
			statement.executeQuery(query3);
			statement.executeQuery(query);
			logger.info("Employee Record Deleted");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
	}

	@Override
	public void updateEmployee(Employee e, String ssn) {
		if (e == null) {
			logger.error("The Employee Object entered is null");
		}
		String query = "update employee set fname = ? , minit = ?, lname =? ,bdate =? ,address =? ,sex =? ,salary =? ,superssn =? ,dno =?  ,email =? where ssn = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, e.getFirstName());
			statement.setString(2, e.getmInt());
			statement.setString(3, e.getLastName());
			statement.setDate(4, e.getBdate());
			statement.setString(5, e.getAddress());
			statement.setString(6, e.getSex());
			statement.setInt(7, Integer.parseInt(e.getSalary()));
			statement.setString(8, e.getSuperssn());

			statement.setInt(9, Integer.parseInt(e.getDno()));
			statement.setString(10, e.getEmail());
			statement.setString(11, ssn);
			statement.executeUpdate();
			logger.info("Employee Updated Succesfully");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
	}

}
