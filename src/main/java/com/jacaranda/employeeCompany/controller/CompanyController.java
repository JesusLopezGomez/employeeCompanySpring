package com.jacaranda.employeeCompany.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.service.CompanyService;


@Controller
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping("/listCompanies")
	public String listCompany(Model model,@RequestParam("pageNumber") Optional<String> pageNumberR, Optional<String> order, @RequestParam("asc") Optional<Boolean> asc) {
		Page<Company> pageCompany = companyService.getCompanies(pageNumberR.orElse("1"), 10,order.orElse("id"), asc.orElse(false) ? "desc" : "");
		Integer totalItems = (int) pageCompany.getTotalElements();
		Integer pageNumber = pageCompany.getNumber();
		Integer totalPages = pageCompany.getTotalPages();
		
		model.addAttribute("totalItems", pageCompany.getContent());
		model.addAttribute("totalElements", totalItems);
		model.addAttribute("currentPage", pageNumber+1);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("asc", asc.orElse(true));						

		
		return "/company/listCompanies";
	}
	
	@GetMapping("/testLayout")
	public String testLayout() {
		return "testLayout";
	}
	
	@GetMapping("/addCompany")
	public String showAddCompany(Model model) {
		Company company = new Company();
		model.addAttribute("company", company);
		
		return "/company/addCompany";
	}
	
	@GetMapping("/saveCompany")
	public String addCompany(Model model,@ModelAttribute("company") Company companySave) {
		Company company = new Company();
		model.addAttribute("company", company);
		
		companyService.addCompany(companySave);
		model.addAttribute("add", "Compañia añadida con éxito");
		return "/company/addCompany";
	}
	
	@GetMapping("/deleteCompany")
	public String showDeleteCompany(Model model, @RequestParam("id") Company company) {
		model.addAttribute("company", company);

		return "/company/deleteCompany";
	}
	
	@GetMapping("/deleteCompanyConfirm")
	public String deleteCompany(Model model, @ModelAttribute("company") Company companyDelete) {
		Company company = new Company();
		model.addAttribute("company", company);
		companyService.deleteCompany(companyDelete);
		model.addAttribute("delete","Compañia eliminada correctamente");

		return "/company/deleteCompany";
	}
	
	@GetMapping("/editCompany")
	public String showEditCompany(Model model, @RequestParam("id") Company company) {
		model.addAttribute("company", company);
		return "/company/editCompany";
	}
	
	@GetMapping("/editCompanyConfirm")
	public String editCompany(Model model, @ModelAttribute("company") Company companyEdit) {
		model.addAttribute("company", companyEdit);
		companyService.editCompany(companyEdit);
		model.addAttribute("edit","Compañia editada correctamente");
		return "/company/editCompany";
	}
}
