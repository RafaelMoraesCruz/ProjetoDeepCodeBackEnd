package com.project.professor.allocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
