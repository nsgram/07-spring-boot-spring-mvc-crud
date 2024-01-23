package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}




	/*	// load employee data

	private List<Employee> theEmployees;
	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
*/
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		List<Employee> employees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind the data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
	//get employee from service
	Employee theEmployee = employeeService.findById(theId);
		
	// set employee in the model to prepopulate
	theModel.addAttribute("employee", theEmployee);
		
	return "employees/employee-form";
		
	}
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
		
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees/list";
		
	}
	
}