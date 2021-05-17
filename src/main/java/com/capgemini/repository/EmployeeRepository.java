package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// deriving the methods based on the data members 
	Employee findByEmail(String email);
	List<Employee> findByMobile(String mobile);
	Employee findByEmailAndPassword(String email, String password);
	
	//native SQL query
	@Query(value = "SELECT * from employee", nativeQuery = true)
	List<Employee> customFindAllEmployees();
	
	@Query(value = "SELECT * from employee WHERE email=?1 AND password=?2", nativeQuery = true)
	Employee customFindEmailAndPassword(String email, String password);
	
	
}
