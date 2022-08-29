package com.project.professor.allocation.service.exception;

public class ServiceNameNotExistException extends Exception {

	private String serviceNameNotExistExpetion;

	public ServiceNameNotExistException(String msg, Throwable cause) {
		super(msg, cause);
		this.serviceNameNotExistExpetion = msg;

	}

	public ServiceNameNotExistException(String msg) {
		super(msg);
		this.serviceNameNotExistExpetion = msg;

	}

	public String getServiceNameNotExistExpetion() {
		return serviceNameNotExistExpetion;
	}

	public void setServiceNameNotExistExpetion(String serviceNameNotExistExpetion) {
		this.serviceNameNotExistExpetion = serviceNameNotExistExpetion;
	}
}
