package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}
	
	public List<Professor> findAll(){
		return professorRepository.findAll();
	}
	
	public List<Professor> findByNameContaining(String name){
		return professorRepository.findByNameContaining(name);	
	}
	
	public Optional<Professor> findById(Long id) {
		return professorRepository.findById(id);
	}
	
	public Optional<Professor> findByCpf(String cpf){
		return professorRepository.FindByCpf(cpf);
	}
	
	public Optional<Professor> findByDepartmentId(Long departmentId){
		return professorRepository.FindByDepartmentId(departmentId);
	}
	
	public Professor save(Professor professor) {
		professor.setId(null);
		return professorRepository.save(professor);
	}
	
	public Professor update(Professor professor) {
		if(professor.getId() != null && professorRepository.existsById(professor.getId())) {
			return professorRepository.save(professor);
		} else
			return null; // exception aqui
	}
	
	public void deleteById(Long id) {
		if(id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}// exception aqui
	}
	
	 public void deleteAll() {
	        professorRepository.deleteAllInBatch();
	    }

}
