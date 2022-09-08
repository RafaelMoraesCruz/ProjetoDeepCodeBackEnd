package com.project.professor.allocation.service;

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
import com.project.professor.allocation.service.exception.AllocationTimeException;
import com.project.professor.allocation.service.exception.ColissiontException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationService allocationService;

	@Test
	public void deleteById() {
		try {
			allocationService.deleteById(1L);
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void findAll() {
		List<Allocation> allocationFind = allocationService.findAll();
		allocationFind.stream().forEach(System.out::println);
	}

	@Test
	public void findyById() {
		Allocation findId;

		findId = allocationService.findById(7l);
		System.out.println(findId);

	}

	@Test
	public void findByProfessorID() {
		Long professorId = 1L;

		List<Allocation> allocations;

		allocations = allocationService.findByProfessorId(professorId);
		allocations.stream().forEach(System.out::println);

	}

	@Test
	public void findByCursoID() {
		Long cursoID = 3L;

		List<Allocation> allocations;

		allocations = allocationService.findByCourseId(cursoID);
		allocations.stream().forEach(System.out::println);

	}

	@Test
	public void create() throws ParseException {

		try {

			Allocation allocation = new Allocation();
			allocation.setId(null);
			allocation.setDay(DayOfWeek.FRIDAY);
			allocation.setStart(sdf.parse("15:00-0300"));
			allocation.setEnd(sdf.parse("17:00-0300"));
			allocation.setProfessorId(3L);
			allocation.setCourseId(1L);
			allocation = allocationService.save(allocation);

			System.out.println(allocation);

		} catch (AllocationTimeException e) {
			System.out.println(e.getMessage());
		} catch (ColissiontException e) {
			System.out.println(e.getMessage());
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void upadate() throws ParseException {

		try {
			Allocation allocation = new Allocation();
			allocation.setId(1L);
			allocation.setDay(DayOfWeek.THURSDAY);
			allocation.setStart(sdf.parse("15:00-0300"));
			allocation.setEnd(sdf.parse("18:00-0300"));
			allocation.setProfessorId(3L);
			allocation.setCourseId(1L);
			allocation = allocationService.save(allocation);

			System.out.println(allocation);
		} catch (AllocationTimeException e) {
			System.out.println(e.getMessage());
		} catch (ColissiontException e) {
			System.out.println(e.getMessage());
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
