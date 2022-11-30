package com.project.professor.allocation.service;

import java.util.List;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.ProfessorRepository;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	private final AllocationRepository allocationRepository;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService,
			AllocationRepository allocationRepository) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
		this.allocationRepository = allocationRepository;
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public List<Professor> findByNameContaining(String name) {
		return professorRepository.findByNameContaining(name);
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}

	public Professor findByCpf(String cpf) {
		return professorRepository.findByCpf(cpf).orElse(null);
	}

	public List<Professor> findByDepartmentId(Long departmentId) {
		return professorRepository.findByDepartmentId(departmentId);

	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		if (professor.getId() != null && professorRepository.existsById(professor.getId())
				&& professorValidationName(professor)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) throws EntityNotFoundException, AllocationExistsException {
		if (id != null && professorRepository.existsById(id)) {
			if (allocationRepository.findByProfessorId(id).size() > 0) {
				throw new AllocationExistsException("This professor have allocation");

			} else {
				professorRepository.deleteById(id);
			}
		} else {
			throw new EntityNotFoundException("Professor ID doesnt exist");
		}
	}

	public void deleteAll() throws AllocationExistsException {

		List<Professor> professors = professorRepository.findAll();

		for (Professor professor : professors) {
			if (allocationRepository.findByProfessorId(professor.getId()).size() > 0) {
				throw new AllocationExistsException("Professor allocation");
			}
		}
		professorRepository.deleteAllInBatch();
	}

	public boolean professorValidationName(Professor professor) {
		Professor duplicatedProfessor = null;
		for (Professor ProfessorFromList: professorRepository.findAll()) {
			if(professor.getId() != ProfessorFromList.getId() && ProfessorFromList.getName().equalsIgnoreCase(professor.getName())) {
				duplicatedProfessor = ProfessorFromList;
			}
		}
		if (professor.getName().isEmpty() || professor.getName().length() < 3 
				|| duplicatedProfessor != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean professorValidationCPF(Professor professor) {
			if (professor.getCpf().isEmpty() || professor.getCpf().length() != 11
					|| ((findByCpf(professor.getCpf()) != null) && findByCpf(professor.getCpf()).getId() != professor.getId())) {
				return false;
			} else {
				return true;
			}
	}

	public Professor saveInternal(Professor professor) {
		if (professorValidationName(professor) && professorValidationCPF(professor)) {
			Long dptId = professor.getDepartmentId();
			Department dpt = departmentService.findById(dptId);

			Professor prof = professorRepository.save(professor);
			prof.setDepartment(dpt);

			return prof;
		} else {
			return null;
		}
	}

}
