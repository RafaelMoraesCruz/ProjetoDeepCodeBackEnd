package com.project.professor.allocation.controller;

import java.util.List;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/professors")
public class ProfessorController {
	
	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Professor>> findAll(){
		List<Professor> professors = professorService.findAll();
		return new ResponseEntity<>(professors, HttpStatus.OK);
		
	}
	
	@GetMapping("/{professor_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) throws ServiceNotFindException{
		Professor professor = professorService.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(professor,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor,HttpStatus.OK);
		}
	}
	
	

}
