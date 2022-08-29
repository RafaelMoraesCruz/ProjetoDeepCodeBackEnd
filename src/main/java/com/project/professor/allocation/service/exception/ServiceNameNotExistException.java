package com.project.professor.allocation.service.exception;

public class ServiceNameNotExistException extends Exception {
	
	private String serviceNameNotExistExpetion;
	
//	public ServiceNameNotExistException(String msg, Throwable cause) {
//		super(msg, cause);
//
//	}

	public ServiceNameNotExistException(String msg) {
		super(msg);

	}

	public String getServiceNameNotExistExpetion() {
		return serviceNameNotExistExpetion;
	}

	public void setServiceNameNotExistExpetion(String serviceNameNotExistExpetion) {
		this.serviceNameNotExistExpetion = serviceNameNotExistExpetion;
	}
}
