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

import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	// read
	@Test
	public void findAll() {
		List<Department> listDepartment = departmentRepository.findAll();
		System.out.println(listDepartment);

	}

	// read
	@Test
	public void findById() {
		Optional<Department> department = departmentRepository.findById(2l);
		System.out.println(department.orElse(null));
	}

	// create
	@Test
	public void createDepartment() {

		Department dpt = new Department();
		dpt.setName("Departamento de Fisica");

		departmentRepository.save(dpt);

	}

	// update
	@Test
	public void updateDepartment() {

		Department dpt = new Department();
		dpt.setName("Department of TI");
		dpt.setId(5l);

		departmentRepository.save(dpt);

	}

	// delete by id
	@Test
	public void deleteDepartment() {
		departmentRepository.deleteById(1l);
	}

	// delete All
	@Test
	public void deleteDepartmentAll() {
		departmentRepository.deleteAllInBatch();
	}
}
