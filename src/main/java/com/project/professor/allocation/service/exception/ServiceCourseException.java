package com.project.professor.allocation.service.exception;

public class ServiceCourseException extends Exception {
	
	private String serviceCourseExpetion;
	
	public ServiceCourseException(String serviceNameNotExistExpetion) {
		super("Department doen't exist");
		this.serviceCourseExpetion = serviceNameNotExistExpetion;

	}

	public String getServiceNameNotExistExpetion() {
		return serviceCourseExpetion;
	}

	public void setServiceNameNotExistExpetion(String serviceNameNotExistExpetion) {
		this.serviceCourseExpetion = serviceNameNotExistExpetion;
	}


}
