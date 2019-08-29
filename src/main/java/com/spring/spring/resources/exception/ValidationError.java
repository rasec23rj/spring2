
package com.spring.spring.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErros() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}
	
	
	
}
