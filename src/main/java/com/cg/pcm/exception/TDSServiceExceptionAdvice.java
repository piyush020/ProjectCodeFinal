package com.cg.pcm.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



//Exception advice class

@ControllerAdvice
public class TDSServiceExceptionAdvice {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = ProjectException.class)
	protected ExceptionBody handleConflicts(Exception e, HttpServletRequest request) {
		String message = e.getMessage();
		String details = "uri=" + request.getServletPath();
		return new ExceptionBody(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), message, details);
	}
}
