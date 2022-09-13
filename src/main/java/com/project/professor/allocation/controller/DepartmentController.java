package com.project.professor.allocation.controller;

import java.util.List;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@ApiOperation(value = "Find all Departments")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Department> departments;

		if (name == null) {
			departments = departmentService.findAll();
		} else {
			departments = departmentService.findByNameContaining(name);
		}

		return new ResponseEntity<>(departments, HttpStatus.OK);
	}

	@ApiOperation(value = "Find a department")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "OK"), 
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
		Department department = departmentService.findById(id);
		if (department == null) {
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Department>(department, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Save a department")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Created"), 
			@ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Department> save(@RequestBody Department department) {
		try {
			department = departmentService.save(department);
			return new ResponseEntity<>(department, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Update a department")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "OK"), 
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,
			@RequestBody Department department) {
		department.setId(id);
		try {
			department = departmentService.update(department);
			if (department == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(department, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Delete a Department")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "No Content"), 
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(path = "/{department_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id){
		try {
			departmentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (AllocationExistsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Delete all Department")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		try {
			departmentService.deleteAll();
		} catch (AllocationExistsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}