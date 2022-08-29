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
import com.project.professor.allocation.service.exception.ServiceCpfDoesNotExistException;

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
		Optional<Professor> professor = professorRepository.findById(4l);
		System.out.println(professor.orElse(null));
	}
	
	@Test
	public void findByCpf() throws ServiceCpfDoesNotExistException {
		String cpf = "1112223";
		Professor prof = professorRepository.findByCpf(cpf).orElse(null);
		if (prof != null) {
			System.out.println(prof);
		} else {
			throw new ServiceCpfDoesNotExistException("Cpf não cadastrado");
		}
	}
	
//	create
	@Test
	public void create() {
		Professor prof = new Professor();
		prof.setName("rodrigo");prof.setCpf("22233344455");prof.setDepartmentId(1L);
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
	public void deleteById() {
		professorRepository.deleteById(1l);
	}
	
//	delete
	@Test
	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	}

