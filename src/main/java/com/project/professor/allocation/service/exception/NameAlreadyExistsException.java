package com.project.professor.allocation.service.exception;

public class NameAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 5L;

	public NameAlreadyExistsException(String msg) {
		super(msg);
	}

}
