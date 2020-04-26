package com.javainuse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javainuse.connection.OracleJdbcConnection;
import com.javainuse.model.Employee;
import com.javainuse.model.WorksOn;

/**
 * WorksOnDAOImplementation for assign Project
 * 
 * @author Kanav Singla
 *
 */
public class WorksOnDAOImpl implements WorksOnDAO {

	static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class.getName());

	EmployeeDAOImpl emp = new EmployeeDAOImpl();

	private Connection conn = OracleJdbcConnection.getDatabaseConnection();
	private static final String insertQuery = "insert into works_on(essn,pno,hours) values (?,?,?)";
	private static final String selectQuery = "select * from works_on";

	@Override
	public int assignProject(WorksOn w) {

		if (w == null) {
			logger.error("The works On object is null");
		}
		// 1) An employee may not work on more than two projects managed by his/her
		// department.
		int resultValue = 0;
		float hr = w.getHours();
		if (hr == 0.0) {
			logger.error("The hours returned is zero");
		}
		float hours = getProjectHours(w.getSsn());
		if (hours == 0.0) {
			logger.error("The Project hours returned is zero");
		}
		if (40 - hours >= hr) {
			try {
				resultValue++;
				PreparedStatement statement = conn.prepareStatement(insertQuery);
				statement.setString(1, w.getSsn());
				statement.setLong(2, w.getPno());
				statement.setFloat(3, w.getHours());

				ResultSet rowInserted = statement.executeQuery();
				if (rowInserted == null) {
					logger.error("Insertion Unsuccessfull");
				} else {
					logger.info("Insertion Successfull");
				}
			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}

		return resultValue;
	}

	public float getProjectHours(String ssn) {
		if (ssn == null) {
			logger.info("The ssn entered is null");
		}
		float hours = 0;
		String selectQuery = "select * from works_on where essn =" + ssn;
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(selectQuery);

			while (result.next()) {
				hours += result.getFloat(3);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
		return hours;
	}

	@Override
	public List<WorksOn> listProjects() {
		List<WorksOn> list = new ArrayList<WorksOn>();

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(selectQuery);

			while (result.next()) {
				WorksOn w = new WorksOn();
				w.setSsn(result.getString(1));
				w.setPno(result.getInt(2));
				w.setHours(result.getFloat(3));

				list.add(w);
			}

			for (WorksOn print : list) {
				logger.info(print.toString());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}

		return list;

	}

	@Override
	public List<WorksOn> getDependentBySsn(String ssn) {

		if (ssn == null) {
			logger.error("the ssn entered is null");
		}
		List<WorksOn> list = new ArrayList<>();
		String query = "select * from works_on where essn = " + ssn;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				WorksOn w = new WorksOn();
				w.setSsn(result.getString(1));
				w.setPno(result.getInt(2));
				w.setHours(result.getInt(3));
				list.add(w);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
		return list;

	}

	@Override
	public int checkProject(WorksOn w) {

		if (w == null) {
			logger.error("The object of the works on is null");
		}
		int res = 0;
		String ssn = w.getSsn();
		if (ssn == null) {
			logger.info("The ssn entered is  null");
		}
		Employee employee = emp.getEmployeeBySsn(ssn);
		if (employee == null) {
			logger.info("The employee object return is null");
		}
		String dno = employee.getDno();
		String query = "select count(w.pno) AS total from works_on w,project p where w.essn =" + ssn + "and p.pnumber="
				+ w.getPno() + "and p.dnum=" + dno;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				res = result.getInt("total");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}

		return res;
	}

	@Override
	public void deleteProject(String id) {
		if (id == null) {
			logger.error("The id entered is wrong");
		}
		String query = "delete from works_on where pno=" + id;
		logger.info(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}

	}

}
