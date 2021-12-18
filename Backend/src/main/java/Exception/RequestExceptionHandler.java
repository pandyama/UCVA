package com.project.Exception;

import com.project.Helper.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class,
            HttpRequestMethodNotSupportedException.class,
    Exception.class})
    public ResponseEntity<Object> handleRequestException(Exception e){
        if(e instanceof HttpRequestMethodNotSupportedException){
            ApiException apiException = new ApiException("Sorry, this request is not allowed here", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(apiException, HttpStatusCode.METHOD_NOT_ALLOWED);
        }
        else if( e instanceof RequestException){
            ApiException apiException = new ApiException("Sorry Failed to process", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(apiException, HttpStatusCode.BAD_REQUEST);
        }
        else{
            ApiException apiException = new ApiException("Could not process request", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(apiException, HttpStatusCode.BAD_REQUEST);
        }
    }
}
