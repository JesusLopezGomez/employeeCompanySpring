package com.jacaranda.employeeCompany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.service.CompanyService;
import com.jacaranda.employeeCompany.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/listEmployees")
	public String listEmployees(Model model,@RequestParam("pageNumber") Optional<String> pageNumberR, @RequestParam("order") Optional<String> order, @RequestParam("asc") Optional<Boolean> asc) {				
		Page<Employee> pageEmployee = employeeService.getEmployees(pageNumberR.orElse("1"), 10,order.orElse("id"),asc.orElse(false) ? "desc" : "");
		
		Integer totalItems = (int) pageEmployee.getTotalElements();
		Integer pageNumber = pageEmployee.getNumber();
		Integer totalPages = pageEmployee.getTotalPages();
		
		model.addAttribute("totalItems", pageEmployee.getContent());
		model.addAttribute("totalElements", totalItems);
		model.addAttribute("currentPage", pageNumber+1);
		model.addAttribute("totalPages",totalPages);
		
		if(asc.orElse(false)) {
			model.addAttribute("asc", false);						
		}else {
			model.addAttribute("asc", true);						
		}
		
		return "/employee/listEmployees";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		List<Company> companies = companyService.getCompanies();
		model.addAttribute("companies", companies);

		return "/employee/addEmployee";
	}
	
	@GetMapping("/saveEmployee")
	public String saveEmployee(Model model,@ModelAttribute("employee") Employee employeeSave) {
		employeeService.save(employeeSave);
		model.addAttribute("add", "Empleado añadido con éxito");
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "/employee/addEmployee";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(Model model,@RequestParam("id") Employee employee) {
		model.addAttribute("employee", employee);
		List<Company> companies = companyService.getCompanies();
		model.addAttribute("companies", companies);
		
		return "/employee/editEmployee";
	}
	
	@GetMapping("editEmployeeConfirm")
	public String editEmployeeConfirm(Model model,@ModelAttribute("employee") Employee employeeEdit) {
		model.addAttribute("employee", employeeEdit);
		employeeService.edit(employeeEdit);
		model.addAttribute("edit","Empleado editado correctamente");
		List<Company> companies = companyService.getCompanies();
		model.addAttribute("companies", companies);
		
		return "/employee/editEmployee";
	}
	
}
