package com.javainuse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javainuse.connection.OracleJdbcConnection;
import com.javainuse.model.Dependent;

/**
 * Dependent DAO Implementation
 * 
 * @author Kanav Singla
 *
 */
public class DependetDAOImpl implements DependentDAO {

	private Connection conn = OracleJdbcConnection.getDatabaseConnection();
	private static final String insertQuery = "insert into dependent(essn,dependent_name,sex,bdate,relationship) values (?,?,?,?,?)";
	private static final String selectQuery = "select * from dependent";

	@Override
	public void assignDependent(Dependent d) {
		try {
			PreparedStatement statement = conn.prepareStatement(insertQuery);
			statement.setString(1, d.getSsn());
			statement.setString(2, d.getdName());
			statement.setString(3, d.getSex());
			statement.setString(4, d.getBdate());
			statement.setString(5, d.getRelationship());

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

	@Override
	public List<Dependent> listDependents() {
		List<Dependent> list = new ArrayList<Dependent>();

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(selectQuery);

			while (result.next()) {
				Dependent d = new Dependent();
				d.setSsn(result.getString(1));

				d.setdName(result.getString(2));

				d.setSex(result.getString(3));

				d.setBdate(result.getString(4));
				d.setRelationship(result.getString(5));

				list.add(d);
			}

			for (Dependent print : list) {
				System.out.println(print.toString());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Dependent> getDependentBySsn(String ssn) {
		List<Dependent> list = new ArrayList<>();

		String query = "select * from dependent where essn = " + ssn;
		System.out.println(query);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				Dependent d = new Dependent();
				d.setSsn(result.getString(1));
				d.setdName(result.getString(2));
				d.setSex(result.getString(3));
				d.setBdate(result.getString(4));
				d.setRelationship(result.getString(4));
				list.add(d);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;

	}

}
