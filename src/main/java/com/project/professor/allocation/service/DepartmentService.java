package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.service.exception.ServiceNameNotExistException;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public List<Department> findByNameContaining(String name) {
		return departmentRepository.findByNameContaining(name);

	}

	public Optional<Department> findById(Long id) {
		return departmentRepository.findById(id);
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department save(Department department) {
		department.setId(null);
		return departmentRepository.save(department);
	}

	public Department update(Department department) throws ServiceNameNotExistException {
		if (department.getId() != null && departmentRepository.existsById(department.getId())) {
			return departmentRepository.save(department);
		} else {
			throw new ServiceNameNotExistException("Department doenst exists");
		}
	}

	public void deleteById(Long id) throws ServiceNameNotExistException {
		if (id != null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		} else {
			throw new ServiceNameNotExistException("Department ID doesnt exists");
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAll();
	}

}
