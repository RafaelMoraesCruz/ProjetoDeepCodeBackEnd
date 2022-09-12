package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	List<Department> findByNameContaining(String name);
}
