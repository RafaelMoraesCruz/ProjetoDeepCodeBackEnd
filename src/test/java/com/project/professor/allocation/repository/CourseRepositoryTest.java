package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Test
	public void findAll() {
		List<Course> listCourse = courseRepository.findAll();
		System.out.println(listCourse);
	}

	@Test
	public void findyById() {
		Optional<Course> findId = courseRepository.findById(1l);
		System.out.println(findId.orElse(null));

	}

	@Test
	public void delete() {
		courseRepository.deleteById(1l);

	}

	@Test
	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}

	@Test
	public void create() {
		Course course = new Course();
		course.setName("Java");
		courseRepository.save(course);

	}

	@Test
	public void update() {
		Course course = new Course();
		course.setName("c++");
		course.setId(3l);
		courseRepository.save(course);

	}
}