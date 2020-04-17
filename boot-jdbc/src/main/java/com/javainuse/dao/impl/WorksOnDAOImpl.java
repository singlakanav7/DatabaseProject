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
import com.javainuse.model.WorksOn;

/**
 * WorksOnDAOImplementation for assign Project
 * 
 * @author Kanav Singla
 *
 */
public class WorksOnDAOImpl implements WorksOnDAO {

	EmployeeDAOImpl emp = new EmployeeDAOImpl();

	private Connection conn = OracleJdbcConnection.getDatabaseConnection();
	private static final String insertQuery = "insert into works_on(essn,pno,hours) values (?,?,?)";
	private static final String selectQuery = "select * from works_on";

	@Override
	public int assignProject(WorksOn w) {
		// 1) An employee may not work on more than two projects managed by his/her
		// department.
		int resultValue = 0;
		float hr = w.getHours();
		float hours = getProjectHours(w.getSsn());
		if (40 - hours >= hr) {
			try {
				resultValue++;
				PreparedStatement statement = conn.prepareStatement(insertQuery);
				statement.setString(1, w.getSsn());
				statement.setLong(2, w.getPno());
				statement.setFloat(3, w.getHours());

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

		return resultValue;
	}

	public float getProjectHours(String ssn) {
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
			e1.printStackTrace();
		}
		return hours;
	}

	@Override
	public List<WorksOn> listProjects() {
		List<WorksOn> list = new ArrayList();

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
				System.out.println(print.toString());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;

	}

	@Override
	public List<WorksOn> getDependentBySsn(String ssn) {
		List<WorksOn> list = new ArrayList<>();
		String query = "select * from works_on where essn = " + ssn;
		System.out.println(query);
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
			e1.printStackTrace();
		}
		return list;

	}

	@Override
	public int checkProject(WorksOn w) {
		int res = 0;
		String ssn = w.getSsn();
		Employee employee = emp.getEmployeeBySsn(ssn);
		String dno = employee.getDno();
		String query = "select count(w.pno) AS total from works_on w,project p where w.essn =" + ssn + "and p.pnumber="
				+ w.getPno() + "and p.dnum=" + dno;
		System.out.println(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				res = result.getInt("total");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return res;
	}

}
