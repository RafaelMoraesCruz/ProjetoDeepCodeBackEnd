package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	Optional<Professor> FindByDepartmentId (Long departmentId);
	
	Optional<Professor> FindByCpf (String cpf);
	
	List<Professor> findByNameContaining(String name);

}
