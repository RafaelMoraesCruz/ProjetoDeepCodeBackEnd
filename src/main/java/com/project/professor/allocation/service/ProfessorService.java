package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

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

	public List<Professor> findByNameContaining(String name) throws ServiceNotFindException {
		List<Professor> listProf = professorRepository.findByNameContaining(name);
		if (listProf.size() != 0) {
			return professorRepository.findByNameContaining(name);
		} else {
			throw new ServiceNotFindException("Professor doesn't exist");
		}
	}

	public Professor findById(Long id) throws ServiceNotFindException {
		Professor prof = professorRepository.findById(id).orElse(null);
		if (prof != null) {
			return prof;
		} else {
			throw new ServiceNotFindException("Professor doesn't exist");
		}
	}

	public Professor findByCpf(String cpf) throws ServiceNotFindException {
		Professor prof = professorRepository.findByCpf(cpf).orElse(null);
		if (prof != null) {
			return prof;
		} else {
			throw new ServiceNotFindException("cpf Does not exists in table professor");
		}
	}

	public List<Professor> findByDepartmentId(Long departmentId) throws ServiceNotFindException {
		List<Professor> listProf = professorRepository.findByDepartmentId(departmentId);
		if (listProf.size() != 0) {
			return professorRepository.findByDepartmentId(departmentId);
		} else {
			throw new ServiceNotFindException("Department doesn't have professors in it");
		}
	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) throws ServiceNotFindException {
		if (professor.getId() != null && professorRepository.existsById(professor.getId())) {
			return saveInternal(professor);
		} else
			throw new ServiceNotFindException("Professor doesn't exist");
	}

	public void deleteById(Long id) throws ServiceNotFindException {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		} else {
			throw new ServiceNotFindException("Professor doesn't exist");
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	public Professor saveInternal(Professor professor) {

		Long dptId = professor.getDepartmentId();
		Department dpt = departmentService.findById(dptId);

		Professor prof = professorRepository.save(professor);
		prof.setDepartment(dpt);

		return prof;
	}

}
