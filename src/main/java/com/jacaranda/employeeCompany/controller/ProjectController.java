package com.jacaranda.employeeCompany.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeCompany.model.Project;
import com.jacaranda.employeeCompany.service.ProjectService;

@Controller
public class ProjectController {
	@Autowired
	ProjectService projectService;
	@GetMapping("/listProjects")
	public String listCompany(Model model,@RequestParam("pageNumber") Optional<String> pageNumberR, Optional<String> order, @RequestParam("asc") Optional<Boolean> asc) {
		Page<Project> pageProject = projectService.getProjects(pageNumberR.orElse("1"), 10,order.orElse("id"), asc.orElse(false) ? "desc" : "");
		Integer totalItems = (int) pageProject.getTotalElements();
		Integer pageNumber = pageProject.getNumber();
		Integer totalPages = pageProject.getTotalPages();
		
		model.addAttribute("totalItems", pageProject.getContent());
		model.addAttribute("totalElements", totalItems);
		model.addAttribute("currentPage", pageNumber+1);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("asc", asc.orElse(true));						

		
		return "/project/listProjects";
	}
}
