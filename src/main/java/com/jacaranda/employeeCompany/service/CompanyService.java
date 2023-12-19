package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> getCompanies(){
		return companyRepository.findAll();
	}
	
	public void addCompany(Company company) {
		companyRepository.save(company);
	}
	
	public void deleteCompany(Company company) {
		companyRepository.delete(company);
	}
	
	public void editCompany(Company company) {
		addCompany(company);
	}
	
	public Page<Company> getCompanies(String pageNum, int pageSize) {
		Pageable pageable = null;
		
		try {
			pageable = PageRequest.of(Integer.valueOf(pageNum) -1, pageSize);
		} catch (Exception e) {
			pageable = PageRequest.of(0, pageSize);
		}
		return companyRepository.findAll(pageable);
	}
}
