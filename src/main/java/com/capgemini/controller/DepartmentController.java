package com.capgemini.controller;

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
import com.capgemini.repository.DepartmentRepository;
import com.capgemini.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/department/")
public class DepartmentController {

	@Autowired
	private DepartmentRepository dr;
	
	@Autowired
	private EmployeeRepository er;

	@PostMapping("/")
	public String create(@RequestBody Department dp) {
		dr.save(dp);
		return "Department added..>>>";
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable int id) {
		return dr.findById(id).get();
		
	}
	
	@PutMapping("/{departmentId}/employee/{employeeId}")
	public String assignDepartmeentToEmployee(@PathVariable int departmentId, @PathVariable int employeeId) {
		
		Department department =  dr.findById(departmentId).get();
		Employee employee =  er.findById(employeeId).get();
		
		department.getEmployees().add(employee);
		
		// updated.
		dr.save(department);
		return "Successs";
	}

}
