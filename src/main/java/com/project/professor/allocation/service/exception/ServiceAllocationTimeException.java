package com.project.professor.allocation.service.exception;

public class ServiceAllocationTimeException extends Exception {
	
	private String serviceAllocationTimeException;
	
	public ServiceAllocationTimeException(String serviceAllocationTimeException) {
		super("End time is must be less start time");
		this.serviceAllocationTimeException = serviceAllocationTimeException;

	}

	public String getServiceNameNotExistExpetion() {
		return serviceAllocationTimeException;
	}

	public void setServiceNameNotExistExpetion(String serviceAllocationTimeException) {
		this.serviceAllocationTimeException = serviceAllocationTimeException;
	}
}
