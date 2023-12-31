package com.blog1.exception;

import com.blog1.paylod.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> Exception(
           Exception exception,
            WebRequest webRequest


            ){
         ErrorDetail errorDetail = new ErrorDetail(new Date(),exception.getMessage(),webRequest.getDescription(true));
         return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
