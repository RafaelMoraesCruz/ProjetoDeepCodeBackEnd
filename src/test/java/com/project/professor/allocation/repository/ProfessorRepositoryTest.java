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

import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	@Autowired
	ProfessorRepository professorRepository;
	
//	read
	@Test
	public void findAll() {
		List<Professor> listProfessor = professorRepository.findAll();;

		listProfessor.stream().forEach(System.out::println);
		}
		
//	read
	@Test
	public void findById() {
		Optional<Professor> listProfessor = professorRepository.findById(1l);
		listProfessor.stream().forEach(System.out::println);
	}
	
//	create
	@Test
	public void create() {
		Professor prof = new Professor();
		prof.setName("rodrigo");prof.setCpf("22233344455");prof.setDepartmentId(2l);
		professorRepository.save(prof);
	}
	
//	update
	@Test
	public void update() {
		Professor prof = new Professor();
		prof.setName("caio");
		prof.setCpf("22233344455");
		prof.setDepartmentId(2l);
		prof.setId(2l);
		professorRepository.save(prof);
	}
	
//	delete
	@Test
	public void delete() {
		professorRepository.deleteById(1l);
	}
	
//	delete
	@Test
	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	}

