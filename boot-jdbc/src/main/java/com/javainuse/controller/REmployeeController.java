package com.javainuse.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javainuse.dao.EmployeeDao;
import com.javainuse.model.Employee;

@RestController
@RequestMapping(path = "/employees")
public class REmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	/*
	@GetMapping(path="/all", produces= "application/json")
	public List<Employee> getEmplyees() {
		return employeeDao.getAllEmployees();
	}
	/*
	@PostMapping("/new")
    public ResponseEntity<Object> addEmployee(@PathVariable("id")int id,@PathVariable("name")String name) 
    {
		Employee current = null;
		
		current.setEmpId(id);
		current.setEmpName(name);

        employeeDao.insertEmployee(current);
         
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(id)
                                    .toUri();
         
        return ResponseEntity.created(location).build();
    }
	
	@GetMapping("/check/{id}")
	public String checkUser(@PathVariable("id") int id) {
		Employee emp = employeeDao.getEmployeeById(id);
		if(emp == null) {
			return "error";
		}
		return "home";
	}
	*/
}
