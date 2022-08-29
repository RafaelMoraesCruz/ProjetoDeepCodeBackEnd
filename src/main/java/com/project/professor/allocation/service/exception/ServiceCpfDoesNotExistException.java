package com.project.professor.allocation.service.exception;

public class ServiceCpfDoesNotExistException extends Exception {

	private String serviceCpfDoesNotExistException;

	public ServiceCpfDoesNotExistException(String serviceNameNotExistException) {
		super("Doesn't exist");
		this.serviceCpfDoesNotExistException = serviceNameNotExistException;

	}

	public String getServiceCpfDoesNotExistExcpetion() {
		return serviceCpfDoesNotExistException;
	}

	public void setServiceCpfDoesNotExistException(String serviceCpfDoesNotExistException) {
		this.serviceCpfDoesNotExistException = serviceCpfDoesNotExistException;
	}
}
