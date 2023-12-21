package com.jacaranda.employeeCompany.model;

import java.sql.Date;
import java.util.Objects;

public class CompanyProjectId {
	//El tipo es como la base de datos pero el nombre de la variable es el nombre de la clase
	private Integer company;
	private Integer project;
	private Date begin;
	
	public CompanyProjectId(Integer company, Integer project, Date begin) {
		super();
		this.company = company;
		this.project = project;
		this.begin = begin;
	}
	//Aqui si hay que crear los contructores obligatoriamente
	public CompanyProjectId() {
		super();
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
		this.project = project;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	@Override
	public int hashCode() {
		return Objects.hash(begin, company, project);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyProjectId other = (CompanyProjectId) obj;
		return Objects.equals(begin, other.begin) && Objects.equals(company, other.company)
				&& Objects.equals(project, other.project);
	}
	
	
}
