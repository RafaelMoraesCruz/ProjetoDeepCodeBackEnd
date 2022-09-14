package com.project.professor.allocation.service;

import java.util.List;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;

	@Test
	public void findAll() {
		professorService.findAll().stream().forEach(System.out::println);
	}

	@Test
	public void findById() {
		Professor prof = professorService.findById(1l);
		System.out.println(prof);
	}

	@Test
	public void findByDepartmentId() {
		List<Professor> prof = professorService.findByDepartmentId(1l);
		prof.stream().forEach(System.out::println);

	}

	@Test
	public void findByCpf() {
		Professor prof = professorService.findByCpf("33344455566");
		System.out.println(prof);
	}

	@Test
	public void save() {
		Professor prof = new Professor();
		prof.setName("mateus");
		prof.setCpf("44455566677");
		prof.setDepartmentId(1l);
		prof.setId(null);
		professorService.saveInternal(prof);
		System.out.println(prof);
	}

	@Test
	public void saveInternal() {
		Professor prof = new Professor();
		prof.setName("rodrigo");
		prof.setCpf("33344455566");
		prof.setDepartmentId(1l);
		professorService.saveInternal(prof);
		System.out.println(prof);
	}

	@Test
	public void update() throws EntityNotFoundException {
		Professor prof = new Professor();
		prof.setName("caio");
		prof.setCpf("44455566677");
		prof.setDepartmentId(1l);
		prof.setId(3l);
		try {
			Professor prof1 = professorService.update(prof);
			System.out.println(prof1);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Test
	public void deleteById() throws EntityNotFoundException {

		try {
			professorService.deleteById(4l);
		} catch (EntityNotFoundException | AllocationExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deleteAll() {
		try {
			professorService.deleteAll();
		} catch (AllocationExistsException e) {
			e.getMessage();
		}
	}

}
