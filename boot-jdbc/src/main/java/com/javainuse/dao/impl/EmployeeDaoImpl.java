package com.javainuse.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javainuse.model.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	@Override
	public void insertEmployee(Employee emp) {
		String sql = "INSERT INTO employee (empId, empName) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql,emp.getEmpId(), emp.getEmpName());
		System.out.println("Success Insert");
	}
	
	@Override
	public void insertEmployees(final List<Employee> employees) {
		String sql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getEmpId());
				ps.setString(2, employee.getEmpName());
			}
			
			public int getBatchSize() {
				return employees.size();
			}
		});
	}

	@Override
	public List<Employee> getAllEmployees(){
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Employee> result = new ArrayList<Employee>();
		for(Map<String, Object> row:rows){
			Employee emp = new Employee();
			//emp.setEmpId(row.get("empId"));
			emp.setEmpName((String)row.get("empName"));
			result.add(emp);
		}
		
		return result;
	}

	public Employee getEmployeeById(String id) {
		String sql = "select * from employee where superssn = ?";
		Object[] args = {id};
		Employee emp = jdbcTemplate.queryForObject(sql, args,BeanPropertyRowMapper.newInstance(Employee.class));
		return emp;
	} 
	*/
	public List<Employee> list() {
		String sql = "select * from employee";
		
		List<Employee> listEmp = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Employee.class));
		
		return listEmp;
	}
	
	public void save(Employee emp) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
	    insertActor.withTableName("employee").usingColumns("fname","minit","lname","ssn","bdate","address","sex","salary","superssn","dno","email");
	    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(emp);
	    insertActor.execute(param);    
	}
	/*
	@Override
	public Employee getEmployeeById(String id) {
		String sql = "SELECT * FROM employee WHERE superssn = '?'";
		return (Employee)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Employee>(){
			@Override
			public Employee mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Employee emp = new Employee();
				emp.setSex(rs.getString("superssn"));
				return emp;
			}
		});
	}
	*/
}
