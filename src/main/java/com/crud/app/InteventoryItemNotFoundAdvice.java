package com.crud.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InteventoryItemNotFoundAdvice {
    @ResponseBody
	@ExceptionHandler(InteventoryItemNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(InteventoryItemNotFoundException ex) {
		return ex.getMessage();
	}
}
