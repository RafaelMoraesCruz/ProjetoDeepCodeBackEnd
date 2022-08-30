package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

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

	public Department update(Department department) throws ServiceNotFindException {
		if (department.getId() != null && departmentRepository.existsById(department.getId())) {
			return departmentRepository.save(department);
		} else {
			throw new ServiceNotFindException("Department doenst exists");
		}
	}

	public void deleteById(Long id) throws ServiceNotFindException {
		if (id != null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		} else {
			throw new ServiceNotFindException("Department ID doesnt exists");
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

}
