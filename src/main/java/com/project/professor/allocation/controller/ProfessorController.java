package com.project.professor.allocation.controller;

import java.util.List;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;
import com.project.professor.allocation.service.exception.AllocationExistsException;
import com.project.professor.allocation.service.exception.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping(path = "/professors")
public class ProfessorController {

	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@ApiOperation(value = "Find all professors")
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name)
			throws EntityNotFoundException {
		List<Professor> professors;
		if (name == null) {
			professors = professorService.findAll();
		} else {
			professors = professorService.findByNameContaining(name);
		}
		return new ResponseEntity<>(professors, HttpStatus.OK);

	}

	@ApiOperation(value = "Find a professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
	@GetMapping(path = "/{professor_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id)
			throws EntityNotFoundException {
		Professor professor = professorService.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(professor, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Find professors by department id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
	@GetMapping(path = "deptid/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Professor>> findByDepartmentId(@PathVariable(name = "department_id") Long departmentId)
			throws EntityNotFoundException {
		List<Professor> professor = professorService.findByDepartmentId(departmentId);
		if (professor.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Professor>>(professor, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Find a professor by cpf")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
	@GetMapping(value = "/cpf", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> findByCpf(@RequestParam String cpf) throws EntityNotFoundException {
		Professor professor = professorService.findByCpf(cpf);
		if (professor != null) {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.NOT_FOUND);
		}
	}

	 @ApiOperation(value = "Save a professor")
	    @ApiResponses({
	            @ApiResponse(code = 201, message = "Created"),
	            @ApiResponse(code = 400, message = "Bad Request")
	    })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Professor> save(@RequestBody Professor professor) {
		try {
			professor = professorService.save(professor);
			if(professor != null) {
				return new ResponseEntity<Professor>(professor, HttpStatus.CREATED);	
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	 @ApiOperation(value = "Update a professor")
	    @ApiResponses({
	            @ApiResponse(code = 200, message = "OK"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 404, message = "Not Found")
	    })
	@PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id,
			@RequestBody Professor professor) {
		professor.setId(id);
		try {
			professor = professorService.update(professor);
			if (professor == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(professor, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Delete a professor")
	    @ApiResponses({
	            @ApiResponse(code = 204, message = "No Content"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 404, message = "Not Found")
	    })
	@DeleteMapping(path = "/{professor_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id){
		
		try {
			professorService.deleteById(id);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch(AllocationExistsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all professors")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		
		try {
			professorService.deleteAll();
		} catch (AllocationExistsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
