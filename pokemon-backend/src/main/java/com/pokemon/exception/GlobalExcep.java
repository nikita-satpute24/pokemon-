package com.pokemon.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExcep
{
	 @ExceptionHandler(PokemonNotFoundException.class)
	    public ResponseEntity<Object> handlePokemonNotFound(
	            PokemonNotFoundException e, WebRequest req) {

	        Map<String, Object> errorBody = new LinkedHashMap<>();
	        errorBody.put("timestamp", LocalDateTime.now());
	        errorBody.put("status", HttpStatus.NOT_FOUND.value());
	        errorBody.put("error", "Not Found");
	        errorBody.put("message", e.getMessage());
	        errorBody.put("path", req.getDescription(false).replace("uri=", ""));

	        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExcep(Exception e, WebRequest req)
	{
		Map<String, Object> errorBody = new LinkedHashMap<String,Object>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorBody.put("error", "Server Error");
        errorBody.put("message", e.getMessage());
        errorBody.put("path", req.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
