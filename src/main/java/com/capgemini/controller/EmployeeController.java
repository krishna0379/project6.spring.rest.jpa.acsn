package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Department;
import com.capgemini.entities.Employee;
import com.capgemini.exception.EmployeeNotFoundException;
import com.capgemini.repository.DepartmentRepository;
import com.capgemini.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository er;
	
	@Autowired
	private DepartmentRepository dr;

	@PostMapping("/")
	public String create(@RequestBody Employee emp) {

		er.save(emp);
		return "Employee added..>>";
	}

	@GetMapping("/{id}")
	public Employee findById(@PathVariable int id) {
		return er.findById(id).get();
	}
	
	@GetMapping("/email/{emailId}")
	public Employee findByEmailll(@PathVariable String emailId) {
		return er.findByEmail(emailId);
	}
	
	@GetMapping("/mobile/{mobileNo}")
	public List<Employee> findByMobile(@PathVariable String mobileNO){
		return er.findByMobile(mobileNO);
	}
	
	@GetMapping("/all")
	List<Employee> findAllEmployee(){
		return er.customFindAllEmployees();
	}
	
	@PostMapping("/custom/authenticate")
	Employee customFindByEmailAndPassword(@RequestBody Employee employee) {
		return er.customFindEmailAndPassword(employee.getEmail(), employee.getPassword());
	}
	
	@PostMapping("/authenticate")
	public Employee findByEmailAndPasswordd(@RequestBody Employee employee) throws EmployeeNotFoundException{
		Employee emp = er.findByEmailAndPassword(employee.getEmail(), employee.getPassword());
		if(emp==null) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		else return emp;
		
	}
	
	@PutMapping("/{employeeId}/department/{departmentId}")
	public String asssignDeparment(@PathVariable int employeeId, @PathVariable int departmentId ) {
	
		Employee employee = er.findById(employeeId).get();
		Department department =  dr.findById(departmentId).get();
		
		// associate them / link them
		employee.setDept(department);
		
		// update method
		er.save(employee);
		
		return "Departmeent Linked";
	}
}
