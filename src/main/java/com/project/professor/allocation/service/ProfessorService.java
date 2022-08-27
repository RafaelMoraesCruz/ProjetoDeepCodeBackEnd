package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;
import com.project.professor.allocation.service.exception.ServiceNameNotExistException;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
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

	public Professor update(Professor professor) throws ServiceNameNotExistException {
		if (professor.getId() != null && professorRepository.existsById(professor.getId())) {
			return saveInternal(professor);
		} else
			throw new ServiceNameNotExistException("Professor doesn't exist");
	}

	public void deleteById(Long id) throws ServiceNameNotExistException {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}else {
			throw new ServiceNameNotExistException("Professor doesn't exist");
		}
	}

	public void deleteAll() {
		professorRepository.deleteAll();
	}

	public Professor saveInternal(Professor professor) {

		Long dptId = professor.getDepartmentId();
		Department dpt = departmentService.findById(dptId).orElse(null); // ajeitar department service optional!

		Professor prof = professorRepository.save(professor);
		prof.setDepartment(dpt);

		return prof;
	}

}
