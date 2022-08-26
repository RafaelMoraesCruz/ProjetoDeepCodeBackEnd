package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.service.exception.ServiceDepartmentException;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
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

	public Department upadate(Department department) throws ServiceDepartmentException {
		if (department.getId() != null && departmentRepository.existsById(department.getId())) {
			return departmentRepository.save(department);
		} else {
			throw new ServiceDepartmentException("Department doenst exists");
		}
	}

	public void deletById(Long id) throws ServiceDepartmentException {
		if (id != null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		} else {
			throw new ServiceDepartmentException("Department ID doesnt exists");
		}
	}

	public List<Department> findByNameContaining(String name) {
		return departmentRepository.findByNameContaining(name);

	}

}
