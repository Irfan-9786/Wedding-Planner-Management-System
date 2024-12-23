package com.quest2travels.wpms.exceptions;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundExceptionHandler(ResourceNotFoundException r) {
		String message = r.getMessage();
		return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Object> runtimeExceptionHandler(DateTimeParseException dateTimeParseException) {
		String message = "Date format should be \"yyyy-mm-dd\" only.\nAll other formats like \"yyyy/mm/dd\",\"dd/mm/yyyy\",\"dd-mm-yyyy\" are not valid.";
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> respMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			respMap.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(respMap, HttpStatus.BAD_REQUEST);
	}

}
