package com.jacaranda.employeeCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Project;
import com.jacaranda.employeeCompany.repository.ProjectRepository;


@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	public Page<Project> getProjects(String pageNum, int pageSize,String order,String ad) {
		Pageable pageable = null;
		
		if(ad.equals("desc")) {
			try {
				pageable = PageRequest.of(Integer.valueOf(pageNum) -1, pageSize,Sort.by(order).descending());
			} catch (Exception e) {
				pageable = PageRequest.of(0, pageSize,Sort.by(order).descending());
			}			
		}else {
			try {
				pageable = PageRequest.of(Integer.valueOf(pageNum) -1, pageSize,Sort.by(order).ascending());
			} catch (Exception e) {
				pageable = PageRequest.of(0, pageSize,Sort.by(order).ascending());
			}
		}
		return projectRepository.findAll(pageable);
	}
}
