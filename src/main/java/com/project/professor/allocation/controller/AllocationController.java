package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;
import com.project.professor.allocation.service.exception.ServiceAllocationTimeException;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

@RestController()
@RequestMapping(path = "/allocations")
public class AllocationController {

	private AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {

		List<Allocation> allocations = allocationService.findAll();

		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {

		try {
			Allocation allocation = allocationService.findById(id);
			return new ResponseEntity<Allocation>(allocation, HttpStatus.OK);
		} catch (ServiceNotFindException e) {
			return new ResponseEntity<Allocation>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long professorId) {
		try {
			List<Allocation> allocatioProfessor = allocationService.findByProfessorId(professorId);
			return new ResponseEntity<>(allocatioProfessor, HttpStatus.OK);
		} catch (ServiceAllocationTimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long professorId) {
		try {
			List<Allocation> allocatioProfessor = allocationService.findByProfessorId(professorId);
			return new ResponseEntity<>(allocatioProfessor, HttpStatus.OK);
		} catch (ServiceAllocationTimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
