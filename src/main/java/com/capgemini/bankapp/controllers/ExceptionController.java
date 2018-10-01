package com.capgemini.bankapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankapp.exceptions.ChangePasswordFailedException;
import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = UserNotFoundException.class)
	public String handlheError(HttpServletRequest request, UserNotFoundException exception) {

		request.setAttribute("error", exception);

		return "error";
	}

	

	@ExceptionHandler(value = LowBalanceException.class)
	public String handleError(HttpServletRequest request, LowBalanceException exception) {

		request.setAttribute("lowBalance", exception);

		return "lowBalance";
	}
	@ExceptionHandler(value = ChangePasswordFailedException.class)
	public String handleErrorPassword(HttpServletRequest request, ChangePasswordFailedException exception) {

		request.setAttribute("passwordFailed", exception);

		return "passwordFailed";
	}
	/*
	 * @ExceptionHandler(value = LowBalanceException.class) public String
	 * handlheErrorf(HttpServletRequest request, LowBalanceException exception) {
	 * 
	 * request.setAttribute("error2", exception);
	 * 
	 * return "error2"; }
	 */
}