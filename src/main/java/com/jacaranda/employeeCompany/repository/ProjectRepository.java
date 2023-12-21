package com.jacaranda.employeeCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.employeeCompany.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
