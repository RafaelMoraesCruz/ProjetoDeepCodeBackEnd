package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

import net.bytebuddy.implementation.bytecode.Throw;

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

	public Course save(Course course) {
		course.setId(null);
		return courseRepository.save(course);

	}

	public Course update(Course course) throws EntityNotFoundException {
		if (course.getId() != null && courseRepository.existsById(course.getId())) {
			return courseRepository.save(course);
		} else {
			throw new EntityNotFoundException("Course doesn't find");
		}
	}

	public void deleteById(Long id) throws EntityNotFoundException {
		if (id != null && courseRepository.existsById(id)&& allocationRepository.findByCourseId(id) != null) {
			courseRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Course doesn't find");
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}

}
