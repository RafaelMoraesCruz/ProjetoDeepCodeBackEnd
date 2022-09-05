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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

@RestController()
@RequestMapping(path = "/Courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	// Read
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

	// Read
	@GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> findById(@PathVariable(name = "course_id") Long id) {
		Course course = courseService.findById(id);
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
	}

	// Create
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Course> save(@RequestBody Course course) {
		try {
			course = courseService.save(course);
			return new ResponseEntity<Course>(course, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id, @RequestBody Course course)
			throws ServiceNotFindException {
		try {
			if (courseService.findById(id) != null) {
				course.setId(id);
				courseService.update(course);
				return new ResponseEntity<Course>(course, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		courseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/{course_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "course_id") Long id) throws ServiceNotFindException {
		try {
			courseService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
