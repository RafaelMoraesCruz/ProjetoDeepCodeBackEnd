package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;
import com.project.professor.allocation.service.exception.ServiceNameNotExistException;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public List<Course> findByNameContaining(String name) {
		return courseRepository.findByNameContaining(name);

	}

	public List<Course> findAll() {
		return courseRepository.findAll();

	}

	public Optional<Course> findById(Long id) {
		return courseRepository.findById(2L);

	}

	public Course save(Course course) {
		course.setId(null);
		return courseRepository.save(course);

	}

	public Course update(Course course) throws ServiceNameNotExistException {
		if (course.getId() != null && courseRepository.existsById(course.getId())) {
			return courseRepository.save(course);
		} else {
			throw new ServiceNameNotExistException("Course doesn't find");
		}
	}

	public void deleteById(Long id) throws ServiceNameNotExistException {
		if (id != null && courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		} else {
			throw new ServiceNameNotExistException("Course doesn't find");
		}
	}

	public void deleteAll() {
		courseRepository.deleteAll();
		;
	}

}
