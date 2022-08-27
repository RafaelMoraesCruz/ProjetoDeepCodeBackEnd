package com.project.professor.allocation.service.exception;

public class ServiceDepartmentException extends Exception {
	
	private String serviceDepartmentExpetion;
	
	public ServiceDepartmentException(String serviceNameNotExistExpetion) {
		super("Department doesn't exist");
		this.serviceDepartmentExpetion = serviceNameNotExistExpetion;

	}

	public String getServiceNameNotExistExpetion() {
		return serviceDepartmentExpetion;
	}

	public void setServiceNameNotExistExpetion(String serviceNameNotExistExpetion) {
		this.serviceDepartmentExpetion = serviceNameNotExistExpetion;
	}

}
