package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.service.exception.ServiceNameNotExistException;

@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;

	public AllocationService(AllocationRepository allocationRepository) {
		super();
		this.allocationRepository = allocationRepository;
	}

	public List<Allocation> findByProfessorId(Long professorId){
		return allocationRepository.findByProfessorId(professorId);
	}
	
	public List<Allocation> findByCourseId(Long courseId){
		return allocationRepository.findByCourseId(courseId);
	}
	
	public Allocation findById(Long Id) {
		return allocationRepository.findById(Id).orElse(null);
	}
	
	public List<Allocation> findAll(){
		return allocationRepository.findAll();
	}

    public Allocation create (Allocation allocation) {
    	allocation.setId(null);
    	return allocationRepository.save(allocation);
    	
    }
    
    public Allocation update (Allocation allocation) throws ServiceNameNotExistException {
    	if (allocation.getId() != null && allocationRepository.existsById(allocation.getId())){
    	return allocationRepository.save(allocation);
    	}else { 
    		throw new ServiceNameNotExistException("Allocation doesn't exist");
    	}}
    public void deleteAll () {
     allocationRepository.deleteAll();
    }
    
	public void deleteById(Long id) throws ServiceNameNotExistException {
    	if (id != null && allocationRepository.existsById(id)) {
    		allocationRepository.deleteById(id);
		} else {
			throw new ServiceNameNotExistException("Allocation doesn't exist");
		}
    }
    	






}













