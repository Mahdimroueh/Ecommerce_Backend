package com.mroueh.exception;

import com.mroueh.response.ApiResponse;
import com.mroueh.response.AuthResponse;
import com.mroueh.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

     @Autowired
     private ResponseUtil responseUtil;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return responseUtil.badRequest(new ApiResponse(ex.getMessage() ,false));

    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return responseUtil.notFound(errorResponse);
       }
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ApiResponse> handleDuplicateUsernameException(DuplicateUsernameException ex) {

        return responseUtil.badRequest(new ApiResponse(ex.getMessage() , false));
    }
    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<ApiResponse> handleDuplicateUsernameException(InsufficientQuantityException ex) {

        return responseUtil.badRequest(new ApiResponse(ex.getMessage() , false));
    }


    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<AuthResponse> handleUserSessionExpiredException(SessionExpiredException ex) {

        return responseUtil.unauthorized(new AuthResponse(false , null));
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException ex) {

        return responseUtil.badRequest(new ApiResponse(ex.getMessage() , false));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        System.out.println("Internal Server Error occurred: "+  ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return responseUtil.internalServerError(errorResponse);
    }

}