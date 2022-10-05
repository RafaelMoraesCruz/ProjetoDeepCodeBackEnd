package com.project.professor.allocation.service;

import java.util.List;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;
import com.project.professor.allocation.service.exception.InvalidNameException;
import com.project.professor.allocation.service.exception.NameAlreadyExistsException;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final AllocationRepository allocationRepository;

	public CourseService(CourseRepository courseRepository, AllocationRepository allocationRepository) {
		super();
		this.courseRepository = courseRepository;
		this.allocationRepository = allocationRepository;
	}

	public List<Course> findByNameContaining(String name) {
		return courseRepository.findByNameContaining(name);

	}

	public List<Course> findAll(String name) {

		return courseRepository.findAll();

	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);

	}

	public Course save(Course course) throws NameAlreadyExistsException, InvalidNameException {
		course.setId(null);
		isNameValid(course);
		return courseRepository.save(course);
	}

	public Course update(Course course) throws EntityNotFoundException, NameAlreadyExistsException, InvalidNameException {
		isNameValid(course);
		if (course.getId() != null && courseRepository.existsById(course.getId())) {
			return courseRepository.save(course);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) throws EntityNotFoundException, AllocationExistsException {
		if (id != null && courseRepository.existsById(id)) {
			if (allocationRepository.findByCourseId(id).size() > 0) {
				throw new AllocationExistsException("This Course have allocation");
			} else {
				courseRepository.deleteById(id);
			}
		} else {
			throw new EntityNotFoundException("Course doesn't find");
		}
	}

	public void deleteAll() throws AllocationExistsException {

		List<Course> courses = courseRepository.findAll();

		for (Course course : courses) {
			if (allocationRepository.findByCourseId(course.getId()) != null) {
				throw new AllocationExistsException("Course have allocation");
			}
		}
		courseRepository.deleteAllInBatch();
	}

	public boolean isNameValid(Course course) throws NameAlreadyExistsException, InvalidNameException {
		if (course.getName().strip().length() < 2) {
			throw new InvalidNameException("Course name is invalid.");
		}
		for (Course courseInCursos : courseRepository.findAll()) {
			if (course.getName().equalsIgnoreCase(courseInCursos.getName())) {
				throw new NameAlreadyExistsException("Course name is already taken.");
			}

		}
		return true;
	}
}
