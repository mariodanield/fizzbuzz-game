package com.fizzbuzz.apirest.exception;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fizzbuzz.apirest.models.entity.ErrorResponse;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
   @ExceptionHandler(Exception.class)
   public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
       List<String> details = new ArrayList<>();
       details.add(ex.getLocalizedMessage());
       ErrorResponse error = new ErrorResponse("Server Error", details);
       return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(CustomNullPointerException.class)
   public final ResponseEntity<ErrorResponse> customNullPointerException(CustomNullPointerException ex, WebRequest request) {
       List<String> details = new ArrayList<>();
       details.add(ex.getLocalizedMessage());
       ErrorResponse error = new ErrorResponse("Null pointer exception.", details);
       return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}