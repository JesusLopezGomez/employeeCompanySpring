package com.jacaranda.employeeCompany.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.employeeCompany.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByFirstName(String nombre,Pageable pageable); 
	
}
