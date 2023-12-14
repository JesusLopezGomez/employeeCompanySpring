package com.jacaranda.employeeCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.service.CompanyService;


@Controller
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping("/listCompanies")
	public String listCompany(Model model) {
		List<Company> listCompanies = companyService.getCompanies();
		model.addAttribute("listCompanies", listCompanies);
		
		return "listCompanies";
	}
	
	@GetMapping("/testLayout")
	public String testLayout() {
		
		return "testLayout";
	}
}
