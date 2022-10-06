package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Allocation {
	
	@JsonProperty(access = Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "DayOfweek", nullable = false, length = 16)
	private DayOfWeek day;

	@ApiModelProperty(example = "19:00")
	@JsonFormat(pattern = "HH:mm")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Temporal(value = TemporalType.TIME)
	@Column(name = "Start", nullable = false)
	private Date start;
	
	@ApiModelProperty(example = "22:00")
	@JsonFormat(pattern = "HH:mm")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Temporal(value = TemporalType.TIME)
	@Column(name = "End", nullable = false)
	private Date end;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "Course_id", nullable = false)
	private Long courseId;

	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne
	@JoinColumn(name = "Course_id", nullable = false, insertable = false, updatable = false)
	private Course course;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "Professor_id", nullable = false)
	private Long professorId;

	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne
	@JoinColumn(name = "Professor_id", nullable = false, insertable = false, updatable = false)
	private Professor professor;

	public Allocation() {
		super();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + ", courseId=" + courseId
				+ ", course=" + course + ", professorId=" + professorId + ", professor=" + professor + "]";
	}
	
	

}
