package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.service.exception.ServiceAllocationTimeException;
import com.project.professor.allocation.service.exception.ServiceColissiontException;
import com.project.professor.allocation.service.exception.ServiceNotFindException;

@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public AllocationRepository getAllocationRepository() {
		return allocationRepository;
	}

	public ProfessorService getProfessorService() {
		return professorService;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public List<Allocation> findByProfessorId(Long professorId) throws ServiceAllocationTimeException {
		List<Allocation> allocated = allocationRepository.findByProfessorId(professorId);
		if (!allocated.isEmpty()) {
			return allocated;
		} else {
			throw new ServiceAllocationTimeException("Professor not find! ");
		}
	}

	public List<Allocation> findByCourseId(Long courseId) throws ServiceNotFindException {
		List<Allocation> coursesAllocated = allocationRepository.findByCourseId(courseId);
		if (coursesAllocated != null) {
			return coursesAllocated;
		} else {
			throw new ServiceNotFindException("Course not find");
		}

	}

	public Allocation findById(Long Id) throws ServiceNotFindException {
		Allocation allocation = allocationRepository.findById(Id).orElse(null);
		if (allocation != null) {
			return allocation;
		} else {
			throw new ServiceNotFindException("Allocation doesn't exists");
		}
	}

	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}

	public Allocation save(Allocation allocation)
			throws ServiceAllocationTimeException, ServiceColissiontException, ServiceNotFindException {
		allocation.setId(null);
		return saveInternal(allocation);

	}

	public Allocation update(Allocation allocation)
			throws ServiceNotFindException, ServiceAllocationTimeException, ServiceColissiontException {
		if (allocation.getId() != null && allocationRepository.existsById(allocation.getId())) {
			return saveInternal(allocation);
		} else {
			throw new ServiceNotFindException("Allocation doesn't exist");
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAll();
	}

	public void deleteById(Long id) throws ServiceNotFindException {
		if (id != null && allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		} else {
			throw new ServiceNotFindException("Allocation doesn't exist");
		}
	}

	private Allocation saveInternal(Allocation allocation)
			throws ServiceAllocationTimeException, ServiceColissiontException, ServiceNotFindException {

		isEndHourGreaterThanStartHour(allocation);
		hasCollission(allocation);

		allocation = allocationRepository.save(allocation);

		Professor professor = professorService.findById(allocation.getProfessorId());
		allocation.setProfessor(professor);

		Course course = courseService.findByCourseId(allocation.getCourseId());
		allocation.setCourse(course);

		return allocation;
	}

	boolean isEndHourGreaterThanStartHour(Allocation allocation) throws ServiceAllocationTimeException {
		boolean isEndHourGreaterThanStartHour = true;

		if (allocation.getStart() != null && allocation.getEnd() != null
				&& allocation.getEnd().compareTo(allocation.getStart()) < 0) {
			throw new ServiceAllocationTimeException("End hour must be less start hour");
		}
		return isEndHourGreaterThanStartHour;
	}

	boolean hasCollission(Allocation newAllocation) throws ServiceColissiontException {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasColission(currentAllocation, newAllocation);
			if (hasCollision) {
				throw new ServiceColissiontException("Profesor already allocated at this time");
			}
		}
		return hasCollision;
	}

	private boolean hasColission(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}

}
