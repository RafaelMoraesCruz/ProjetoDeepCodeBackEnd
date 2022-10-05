package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;
import com.project.professor.allocation.service.exception.InvalidNameException;
import com.project.professor.allocation.service.exception.NameAlreadyExistsException;

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

	public Department save(Department department) throws NameAlreadyExistsException, InvalidNameException {
		department.setId(null);
		isNameValid(department);
		return departmentRepository.save(department);
	}

	public Department update(Department department) throws EntityNotFoundException, NameAlreadyExistsException, InvalidNameException {
		isNameValid(department);
		if (department.getId() != null && departmentRepository.existsById(department.getId())) {
			return departmentRepository.save(department);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) throws EntityNotFoundException, AllocationExistsException {
		if (id != null && departmentRepository.existsById(id)) {
			if (professorRepository.findByDepartmentId(id).size() > 0) {
				throw new AllocationExistsException("This department have professor allocation");
			} else {
				departmentRepository.deleteById(id);
			}
		} else {
			throw new EntityNotFoundException("Department ID doesnt exists");
		}
	}

	public void deleteAll() throws AllocationExistsException {
		
		List<Department> departments = departmentRepository.findAll();
		
		for (Department department : departments) {
			if(professorRepository.findByDepartmentId(department.getId()).size() > 0){
				throw new AllocationExistsException("Department have professor allocation");
			}
		}
		departmentRepository.deleteAllInBatch();
	}
	
	public boolean isNameValid(Department department) throws NameAlreadyExistsException, InvalidNameException {
		if (department.getName().strip().length() < 2) {
			throw new InvalidNameException("Course name is invalid.");
		}
		for (Department departmentInDepartments : departmentRepository.findAll()) {
			if (department.getName().equalsIgnoreCase(departmentInDepartments.getName())) {
				throw new NameAlreadyExistsException("Course name is already taken.");
			}

		}
		return true;
	}

}
