package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public void edit(Employee employee) {
		save(employee);
	}

	public Page<Employee> getEmployees(String pageNum, int pageSize,String order) {
		Pageable pageable = null;
		
		try {
			pageable = PageRequest.of(Integer.valueOf(pageNum) -1, pageSize,Sort.by(order).ascending());
		} catch (Exception e) {
			pageable = PageRequest.of(0, pageSize,Sort.by(order).ascending());
		}
		return employeeRepository.findAll(pageable);
	}
}
