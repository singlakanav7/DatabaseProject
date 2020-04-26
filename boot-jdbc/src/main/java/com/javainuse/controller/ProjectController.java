package com.javainuse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javainuse.dao.impl.DependetDAOImpl;
import com.javainuse.dao.impl.EmployeeDAOImpl;
import com.javainuse.dao.impl.WorksOnDAOImpl;
import com.javainuse.model.Employee;

@Controller
@Scope("session")
@RequestMapping(path = "/project")
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(ProjectController.class.getName());

	EmployeeDAOImpl employeeDao = new EmployeeDAOImpl();
	WorksOnDAOImpl worksonDAO = new WorksOnDAOImpl();
	DependetDAOImpl dependentDAO = new DependetDAOImpl();

	@RequestMapping("/")
	public String getIndex() {
		logger.info("Index page is called");
		return "index";
	}

	@GetMapping(value = "/list/{ssn}")
	public Employee getList(@PathVariable("ssn") String ssn) {
		if(ssn == null) {
			logger.error("ssn entered is null");
		}
		Employee emp = employeeDao.getEmployeeBySsn(ssn);
		return emp;
	}

	@RequestMapping("/findLogs")
	public String showLogs() {
		return "application";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest req, Model model) {
		logger.info("home page is called");
		String ssn = (String) req.getSession().getAttribute("managerSSN");
		Employee emp = employeeDao.getEmployeeBySsn(ssn);
		logger.info(ssn);
		model.addAttribute("emp", emp);
		return "home";
	}

	@RequestMapping("/checkUser")
	public String checkUser() {
		return "CheckManager";
	}

	@RequestMapping("/error")
	public String error() {
		return "Error";
	}

	@RequestMapping("/record/home")
	public String getHome() {
		return "home";
	}

	@RequestMapping("/findReport")
	public String showReport() {
		return "FindReport";
	}

	@RequestMapping("/findDependent")
	public String showDependent() {
		return "FindDependent";
	}

	@RequestMapping("/findProject")
	public String showProject() {
		return "FindProject";
	}

	@RequestMapping("/deleteEmployee")
	public String getDelete() {
		return "FindDeleteEmployee";
	}

	@RequestMapping("/deleteProject")
	public String getDeleteProject() {
		return "FindDeleteProject";
	}

	@RequestMapping(value = "/deleteEmp", method = RequestMethod.POST)
	public String delteEmployeeInf(HttpServletRequest req, Model model) {
		String id = req.getParameter("empId");
		logger.info(id);
		employeeDao.deleteEmployee(id);
		String ssn = (String) req.getSession().getAttribute("managerSSN");
		Employee emp = employeeDao.getEmployeeBySsn(ssn);
		logger.info(ssn);
		model.addAttribute("emp", emp);
		return "home";
	}

	@RequestMapping(value = "/deletePro", method = RequestMethod.POST)
	public String delteProjectInfo(HttpServletRequest req, Model model) {
		String id = req.getParameter("empId");
		logger.info(id);
		worksonDAO.deleteProject(id);
		String ssn = (String) req.getSession().getAttribute("managerSSN");
		Employee emp = employeeDao.getEmployeeBySsn(ssn);
		logger.info(ssn);
		model.addAttribute("emp", emp);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String getEmployee(HttpServletRequest request, Model model) {
		logger.info("Home Page Entered");
		String id = request.getParameter("empId");
		logger.info(id);
		Employee emp1 = employeeDao.checkManager(id);
		if (emp1.getSsn() == null) {
			logger.error("ssn is null");
			return "Error";
		}
		Employee emp = employeeDao.getEmployeeBySsn(id);
		HttpSession session = request.getSession();
		session.setAttribute("managerSSN", emp.getSsn());
		logger.info((String) session.getAttribute("managerSSN"));
		model.addAttribute("emp", emp);
		return "home";
	}

	/*
	 * @RequestMapping("/home/{ssn}") public String
	 * getEmployeeInfo(HttpServletRequest request, Model
	 * model,@PathVariable(value="empId")int ssn) { String id =
	 * request.getParameter("empId"); System.out.println(id); Employee emp =
	 * employeeDao.getEmployeeBySsn(id);
	 * 
	 * model.addAttribute("emp", emp); return "home"; }
	 * 
	 * @RequestMapping("/home/{id}") public ModelAndView showEdit(@PathVariable(name
	 * = "id") String id) { ModelAndView mv = new ModelAndView("home"); Employee emp
	 * = employeeDao.getEmployeeById(id); mv.addObject("emp",emp); return mv; }
	 */
	@RequestMapping("/newUser")
	public String showNewForm() {
		return "NewEmployee";
	}

	@PostMapping(path = "/newEmp", consumes = "application/json", produces = "application/json")
	public void insertEmployee(@RequestBody Employee em) {
		Employee newEmp = new Employee();
		newEmp.setFirstName(em.getFirstName());
		newEmp.setmInt(em.getmInt());
		newEmp.setLastName(em.getLastName());
		newEmp.setSsn(em.getSsn());
		newEmp.setBdate(em.getBdate());
		newEmp.setAddress(em.getAddress());
		newEmp.setSex(em.getSex());
		newEmp.setSalary(em.getSalary());
		newEmp.setSuperssn(em.getSuperssn());
		newEmp.setDno(em.getDno());
		newEmp.setEmail(em.getEmail());
		try {
			employeeDao.saveEmployee(newEmp);
		} catch (Exception e) {
			
		}
		logger.info("Insert Completed Using rest api request");
	}
	/*
	 * @RequestMapping(value = "/new", method = RequestMethod.POST) public String
	 * getInsert(HttpServletRequest req, Model model) { Employee newEmp = new
	 * Employee(); newEmp.setFirstName(req.getParameter("fname"));
	 * newEmp.setmInt(req.getParameter("mid"));
	 * newEmp.setLastName(req.getParameter("lname"));
	 * newEmp.setSsn(req.getParameter("ssn"));
	 * newEmp.setBdate(req.getParameter("bdate"));
	 * newEmp.setAddress(req.getParameter("address"));
	 * newEmp.setSex(req.getParameter("sex"));
	 * newEmp.setSalary(req.getParameter("sal"));
	 * newEmp.setSuperssn(req.getParameter("superssn"));
	 * newEmp.setDno(req.getParameter("dno"));
	 * newEmp.setEmail(req.getParameter("email")); employeeDao.saveEmployee(newEmp);
	 * Employee listEmp = employeeDao.getEmployeeBySsn(newEmp.getSsn());
	 * model.addAttribute("listEmp", listEmp);
	 * 
	 * return "EmployeeRecord"; }
	 * 
	 * @RequestMapping("/list") public String showList(Model model,
	 * HttpServletRequest req) { String managerSSN = (String)
	 * req.getSession().getAttribute("managerSSN"); System.out.println(managerSSN);
	 * model.addAttribute("listEmp", employeeDao.getEmployees(managerSSN)); return
	 * "EmployeeList"; }
	 * 
	 * @RequestMapping("/scan") public String showListScan(Model model,
	 * HttpServletRequest req) {
	 * 
	 * return "ScanDatabase"; }
	 * 
	 * @RequestMapping("/project") public String getProject() { return "Project"; }
	 * 
	 * @RequestMapping("/FindProject") public String findProject() { return
	 * "FindProject"; }
	 * 
	 * @RequestMapping(value = "/assign", method = RequestMethod.POST) // 1) An
	 * employee may not work on more than two projects managed by his/her //
	 * department. public String assignProjectList(HttpServletRequest req, Model
	 * model) { WorksOn w = new WorksOn(); w.setSsn(req.getParameter("ssn"));
	 * w.setPno(Integer.parseInt(req.getParameter("pno")));
	 * w.setHours(Float.parseFloat(req.getParameter("hours"))); int res1 =
	 * worksonDAO.checkProject(w); if (res1 > 2) { return "ProjectError"; } int res
	 * = worksonDAO.assignProject(w); if (res == 0) { return "HourError"; } String
	 * ssn = (String) req.getSession().getAttribute("managerSSN"); Employee emp =
	 * employeeDao.getEmployeeBySsn(ssn); System.out.println(ssn);
	 * model.addAttribute("emp", emp); return "redirect:/project/home"; }
	 * 
	 * @RequestMapping(value = "assignProject/assign", method = RequestMethod.POST)
	 * // 1) An employee may not work on more than two projects managed by his/her
	 * // department. public String assignProject(HttpServletRequest req, Model
	 * model) { WorksOn w = new WorksOn(); w.setSsn(req.getParameter("ssn"));
	 * w.setPno(Integer.parseInt(req.getParameter("pno")));
	 * w.setHours(Float.parseFloat(req.getParameter("hours"))); int res =
	 * worksonDAO.assignProject(w); int res1 = worksonDAO.checkProject(w); if (res
	 * == 0) { return "HourError"; } if (res1 > 2) { return "ProjectError"; } String
	 * ssn = (String) req.getSession().getAttribute("managerSSN"); Employee emp =
	 * employeeDao.getEmployeeBySsn(ssn); System.out.println(ssn);
	 * model.addAttribute("emp", emp); return "redirect:/project/home"; }
	 * 
	 * @RequestMapping(value = "/report", method = RequestMethod.POST) public String
	 * getReport(HttpServletRequest req, Model model) { String ssn =
	 * req.getParameter("empId"); Employee listEmp =
	 * employeeDao.getEmployeeBySsn(ssn); List<Dependent> listDependent =
	 * dependentDAO.getDependentBySsn(ssn); List<WorksOn> listProject =
	 * worksonDAO.getDependentBySsn(ssn); model.addAttribute("listEmp", listEmp);
	 * model.addAttribute("listDependent", listDependent);
	 * model.addAttribute("listProject", listProject);
	 * 
	 * return "Record"; }
	 * 
	 * @RequestMapping("/listProject") public String showProjectList(Model model,
	 * HttpServletRequest req) { String ssn = req.getParameter("empId");
	 * System.out.println(ssn); model.addAttribute("listProject",
	 * worksonDAO.getDependentBySsn((ssn))); return "ProjectList"; }
	 * 
	 * @RequestMapping(value = "/addDependent", method = RequestMethod.POST) public
	 * String assignDependentList(HttpServletRequest req, Model model) { Dependent d
	 * = new Dependent(); d.setSsn(req.getParameter("ssn"));
	 * d.setdName(req.getParameter("dno")); d.setSex(req.getParameter("sex"));
	 * d.setBdate(req.getParameter("bdate"));
	 * d.setRelationship(req.getParameter("relationship"));
	 * dependentDAO.assignDependent(d); String ssn = (String)
	 * req.getSession().getAttribute("managerSSN"); Employee emp =
	 * employeeDao.getEmployeeBySsn(ssn); System.out.println(ssn);
	 * model.addAttribute("emp", emp);
	 * 
	 * return "Project"; }
	 * 
	 * @RequestMapping(value = "/record/addDependent", method = RequestMethod.POST)
	 * public String assignDependent(HttpServletRequest req, Model model) {
	 * Dependent d = new Dependent(); d.setSsn(req.getParameter("ssn"));
	 * d.setdName(req.getParameter("dno")); d.setSex(req.getParameter("sex"));
	 * d.setBdate(req.getParameter("bdate"));
	 * d.setRelationship(req.getParameter("relationship"));
	 * dependentDAO.assignDependent(d); String ssn = (String)
	 * req.getSession().getAttribute("managerSSN"); Employee emp =
	 * employeeDao.getEmployeeBySsn(ssn); System.out.println(ssn);
	 * model.addAttribute("emp", emp);
	 * 
	 * return "redirect:/project/home"; }
	 * 
	 * @RequestMapping("/listDependent") public String showDependentList(Model
	 * model, HttpServletRequest req) { String ssn = req.getParameter("empId");
	 * System.out.println(ssn); model.addAttribute("listDependent",
	 * dependentDAO.getDependentBySsn((ssn))); return "DependentList"; }
	 * 
	 * @RequestMapping("/record/{ssn}") public String showRecord(@PathVariable(name
	 * = "ssn") String ssn, Model model) { System.out.println(ssn);
	 * model.addAttribute("listEmp", employeeDao.getEmployeeBySsn(ssn)); return
	 * "EmployeeRecord"; }
	 * 
	 * @RequestMapping("/assignProject/{ssn}") public String
	 * showProject(@PathVariable(name = "ssn") String ssn, Model model) { return
	 * "Project"; }
	 * 
	 * @RequestMapping(value = "/checkDependent", method = RequestMethod.POST)
	 * public String showDependentDisp(Model model, HttpServletRequest req) { String
	 * checkbox = req.getParameter("no"); if (checkbox != null) { return "Project";
	 * } return "Dependent"; }
	 * 
	 * @RequestMapping(value = "/record/checkDependent", method =
	 * RequestMethod.POST) public String showDependent(Model model,
	 * HttpServletRequest req) { String checkbox = req.getParameter("no"); if
	 * (checkbox != null) { return "redirect:/project/home"; } return "Dependent"; }
	 * 
	 * /*
	 * 
	 * @RequestMapping("/list") public String getList(Model model) { List<Employee>
	 * listEmp = employeeDao.list(); model.addAttribute("listEmp",listEmp); return
	 * "EmployeeList"; }**
	 * 
	 * @RequestMapping("/project") public String getProject() { return "Project"; }*
	 * 
	 * @RequestMapping("/newUser") public String showNewForm() { return
	 * "NewEmployee"; }**
	 * 
	 * @RequestMapping(value = "/save", method = RequestMethod.POST) public String
	 * save(@ModelAttribute("emp") Employee emp) { // employeeDao.save(emp);
	 * 
	 * return "index"; } /*
	 * 
	 * @RequestMapping("/home/{id}") public ModelAndView showEdit(@PathVariable(name
	 * = "id") String id) { ModelAndView mv = new ModelAndView("home"); Employee emp
	 * = employeeDao.getEmployeeById(id); mv.addObject("emp",emp); return mv; }
	 * 
	 * @RequestMapping(value = "/home", method= RequestMethod.POST) public String
	 * getEmployee(HttpServletRequest request,Model model) { String empId =
	 * request.getParameter("empId"); System.out.println(empId); Employee emp =
	 * employeeDao.getEmployeeById(empId);
	 * 
	 * model.addAttribute("ssn",empId); return "home"; }
	 * 
	 * /*
	 * 
	 * @RequestMapping(value = "/new", method= RequestMethod.POST) public String
	 * getInsert(HttpServletRequest req) { Employee newEmp = new Employee();
	 * System.out.println(Integer.parseInt(req.getParameter("id")));
	 * System.out.println(req.getParameter("name"));
	 * 
	 * newEmp.setEmpId(req.getParameter("id"));
	 * newEmp.setEmpName(req.getParameter("name"));
	 * employeeDao.insertEmployee(newEmp);
	 * 
	 * return "home"; }
	 * 
	 * /*
	 * 
	 * @RequestMapping("/check/{ssn}") public String checkUser(@PathVariable("id")
	 * int ssn) { Employee emp = employeeDao.getEmployeeById(ssn); if(emp == null) {
	 * return "error"; } return "home"; }
	 * 
	 * @RequestMapping("/home") public String home() { return "home"; }
	 */

}
