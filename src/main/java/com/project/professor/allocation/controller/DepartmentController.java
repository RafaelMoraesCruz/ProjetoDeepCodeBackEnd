package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Department> departments;

		if (name == null) {
			departments = departmentService.findAll();
		} else {
			departments = departmentService.findByNameContaining(name);
		}

		return new ResponseEntity<>(departments, HttpStatus.OK);
	}

	@GetMapping(path = "/{department_id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
		Department department = departmentService.findById(id);
		if (department == null) {
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Department>(department, HttpStatus.OK);
		}
	}
}