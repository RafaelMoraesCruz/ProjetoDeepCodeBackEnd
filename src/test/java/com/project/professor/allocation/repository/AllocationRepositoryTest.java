package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationRepository allocationRepository;

	@Test
	public void findAll() {
		List<Allocation> allocationFind = allocationRepository.findAll();
		allocationFind.stream().forEach(System.out::println);
	}

	@Test
	public void findyById() {
		Optional<Allocation> findId = allocationRepository.findById(1l);
		System.out.println(findId.orElse(null));
	}

	@Test
	public void findByProfessorID() {
		Long professorId = 1L;

		List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
		allocations.stream().forEach(System.out::println);
	}

	@Test
	public void findByCursoID() {
		Long cursoID = 1L;

		List<Allocation> allocations = allocationRepository.findByCourseId(cursoID);
		allocations.stream().forEach(System.out::println);
	}

	@Test
	public void create() throws ParseException {

		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDay(DayOfWeek.SUNDAY);
		allocation.setStart(sdf.parse("08:00-0300"));
		allocation.setEnd(sdf.parse("18:00-0300"));
		allocation.setProfessorId(3L);
		allocation.setCourseId(1L);

		allocation = allocationRepository.save(allocation);

		System.out.println(allocation);
	}

	@Test
	public void upadate() throws ParseException {

		Allocation allocation = new Allocation();
		allocation.setId(1L);
		allocation.setDay(DayOfWeek.SUNDAY);
		allocation.setStart(sdf.parse("08:00-0300"));
		allocation.setEnd(sdf.parse("18:00-0300"));
		allocation.setProfessorId(1L);
		allocation.setCourseId(1L);

		allocation = allocationRepository.save(allocation);

		System.out.println(allocation);
	}

}
