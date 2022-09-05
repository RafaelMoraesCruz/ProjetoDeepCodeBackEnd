package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

public class CourseController {
	
	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Course>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Course> courses;
		if (name == null) {
			courses = courseService.findAll(name);
		} else {
			courses = courseService.findByNameContaining(name);
		}
		return new ResponseEntity<>(courses, HttpStatus.OK);

	}

}
