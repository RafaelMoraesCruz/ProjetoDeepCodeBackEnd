package com.project.professor.allocation.service;

import java.util.List;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

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
	public void findById() throws ServiceNotFindException {
		try {
			Professor prof = professorService.findById(1l);
		System.out.println(prof);
		} catch (ServiceNotFindException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void findByDepartmentId() throws ServiceNotFindException {
		try {
			List<Professor> prof = professorService.findByDepartmentId(1l);
			prof.stream().forEach(System.out::println);
		} catch (ServiceNotFindException e) {
			e.getMessage();
		}

	}
	
	@Test
	public void findByCpf() throws ServiceNotFindException {
		try {
			Professor prof = professorService.findByCpf("33344455566");
			System.out.println(prof);
		} catch (ServiceNotFindException e) {
			e.getMessage();
		}
	}
	@Test
	public void save() {
		Professor prof = new Professor();
		prof.setName("mateus");prof.setCpf("44455566677");prof.setDepartmentId(1l);prof.setId(null);
		professorService.saveInternal(prof);
		System.out.println(prof);
	}
	
	
	
	@Test
	public void saveInternal() {
		Professor prof = new Professor();
		prof.setName("rodrigo");prof.setCpf("33344455566");prof.setDepartmentId(1l);
		professorService.saveInternal(prof);
		System.out.println(prof);
	}
	
	@Test
	public void update() throws ServiceNotFindException {
		Professor prof = new Professor();
		prof.setName("caio");prof.setCpf("44455566677");prof.setDepartmentId(1l);prof.setId(3l);
		try {
			Professor prof1 = professorService.update(prof);
			System.out.println(prof1);
		} catch (ServiceNotFindException e) {
			e.getMessage();
		}

		
	}
	
	@Test
	public void deleteById() throws ServiceNotFindException {
		
		try {
			professorService.deleteById(4l);
		} catch (ServiceNotFindException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void deleteAll() {
		professorService.deleteAll();
	}
	
	
	

}
