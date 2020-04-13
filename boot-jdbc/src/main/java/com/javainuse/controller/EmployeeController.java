package com.javainuse.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javainuse.dao.impl.EmployeeDaoImpl;
import com.javainuse.model.Employee;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeDaoImpl employeeDao;
	
	
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/checkUser")
	public String checkUser() {
		return "CheckManager";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "Error";
	}
	
	@RequestMapping("/list")
	public String getList(Model model) {
		List<Employee> listEmp = employeeDao.list();
		model.addAttribute("listEmp",listEmp);
		return "EmployeeList";
	}
	
	@RequestMapping("/project")
	public String getProject(){
		return "Project";
	}
	
	@RequestMapping("/newUser")
	public String showNewForm() {
		return "NewEmployee";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") Employee emp) {
	    employeeDao.save(emp);
	      
	    return "index";
	}
	/*
	@RequestMapping("/home/{id}")
	public ModelAndView showEdit(@PathVariable(name = "id") String id) {
		ModelAndView mv = new ModelAndView("home");
		Employee emp = employeeDao.getEmployeeById(id);
		mv.addObject("emp",emp);
		return mv;
	}
	/*
	@RequestMapping(value = "/home", method= RequestMethod.POST)
	public String getEmployee(HttpServletRequest request,Model model) {
		String empId = request.getParameter("empId");
		System.out.println(empId);
		Employee emp = employeeDao.getEmployeeById(empId);
		
		model.addAttribute("ssn",empId);
		return "home";
	}
	*/
	/*
	@RequestMapping(value = "/new", method= RequestMethod.POST)
	public String getInsert(HttpServletRequest req) {
		Employee newEmp = new Employee();
		System.out.println(Integer.parseInt(req.getParameter("id")));
		System.out.println(req.getParameter("name"));
	
		newEmp.setEmpId(req.getParameter("id"));
		newEmp.setEmpName(req.getParameter("name"));
		employeeDao.insertEmployee(newEmp);
		
		return "home";
	}

	/*
	@RequestMapping("/check/{ssn}")
	public String checkUser(@PathVariable("id") int ssn) {
		Employee emp = employeeDao.getEmployeeById(ssn);
		if(emp == null) {
			return "error";
		}
		return "home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	*/
	
}
