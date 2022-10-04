package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.AllocationTimeException;
import com.project.professor.allocation.service.exception.ColissiontException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping(path = "/allocations")
public class AllocationController {

	private AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@ApiOperation(value = "Find all allocations")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {

		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@ApiOperation(value = "Find an allocation by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {

		Allocation allocation = allocationService.findById(id);
		return new ResponseEntity<Allocation>(allocation, HttpStatus.OK);
	}

	@ApiOperation(value = "Find allocations by professor")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long professorId) {

		List<Allocation> allocatioProfessor = allocationService.findByProfessorId(professorId);
		return new ResponseEntity<>(allocatioProfessor, HttpStatus.OK);
	}

	@ApiOperation(value = "Find allocations by course")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long courseId) {

		List<Allocation> allocatioCourse = allocationService.findByCourseId(courseId);
		return new ResponseEntity<>(allocatioCourse, HttpStatus.OK);
	}

	@ApiOperation(value = "Save allocation")
	@ApiResponses({ @ApiResponse(code = 201, message = "OK"), @ApiResponse(code = 400, message = "Bad request") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Allocation> save(@RequestBody Allocation allocation) {
		try {
			allocation = allocationService.save(allocation);
			return new ResponseEntity<Allocation>(allocation, HttpStatus.CREATED);
		} catch (AllocationTimeException | ColissiontException | EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Update an allocation")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> upadate(@PathVariable(name = "allocation_id") Long id,
			@RequestBody Allocation allocation) {
		allocation.setId(id);
		try {
			allocation = allocationService.update(allocation);
			if (allocation == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Allocation>(allocation, HttpStatus.OK);
			}
		} catch (AllocationTimeException | ColissiontException | EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Delete allocation by ID")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "No content"),
			@ApiResponse(code = 400, message = "Bad request") })
	@DeleteMapping(path = "/{allocation_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
		try {
			allocationService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}

	@ApiOperation(value = "Delete all allocation")
	@ApiResponses({ @ApiResponse(code = 204, message = "No content") })
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteAll() {

		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
