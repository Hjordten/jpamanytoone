package com.example.jpamanytoone.error;

import com.example.jpamanytoone.error.customError.BadRequest;
import com.example.jpamanytoone.error.customError.InternalServerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<CustomErrorResponse> handleMissingInformationException(BadRequest ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<CustomErrorResponse> handleFailedToDeleteException(InternalServerError ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }




    // Add more specific exception handlers for different scenarios
}
