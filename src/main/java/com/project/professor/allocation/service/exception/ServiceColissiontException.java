package com.project.professor.allocation.service.exception;

public class ServiceColissiontException extends Exception {
	
	private String serviceColissiontException;
	
	public ServiceColissiontException(String serviceColissiontException) {
		super("Colission has detected");
		this.serviceColissiontException = serviceColissiontException;

	}

	public String getServiceNameNotExistExpetion() {
		return serviceColissiontException;
	}

	public void setServiceNameNotExistExpetion(String serviceColissiontException) {
		this.serviceColissiontException = serviceColissiontException;
	}
}
