package com.capgemini.bankapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankapp.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = UserNotFoundException.class)
	public String handlheError(HttpServletRequest request, UserNotFoundException exception) {

		request.setAttribute("error", exception);

		return "error";
	}
}