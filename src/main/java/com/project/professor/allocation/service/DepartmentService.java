package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ProfessorRepository professorRepository;

	public DepartmentService(DepartmentRepository departmentRepository, ProfessorRepository professorRepository) {
		super();
		this.departmentRepository = departmentRepository;
		this.professorRepository = professorRepository;
	}

	public List<Department> findByNameContaining(String name) {
		return departmentRepository.findByNameContaining(name);

	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department save(Department department) {
		department.setId(null);
		return departmentRepository.save(department);
	}

	public Department update(Department department) throws EntityNotFoundException {
		if (department.getId() != null && departmentRepository.existsById(department.getId())) {
			return departmentRepository.save(department);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) throws EntityNotFoundException, AllocationExistsException {
		if (id != null && departmentRepository.existsById(id)) {
			if (professorRepository.findByDepartmentId(id) != null) {
				throw new AllocationExistsException("This department have professor allocation");
			} else {
				departmentRepository.deleteById(id);
			}
		} else {
			throw new EntityNotFoundException("Department ID doesnt exists");
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

}
