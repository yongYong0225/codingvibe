package com.sparta.myboard.exception;

import com.sparta.myboard.entity.User;
import com.sparta.myboard.exception.customexception.CommentCustomException;
import com.sparta.myboard.exception.customexception.ErrorCode;
import com.sparta.myboard.exception.customexception.PostCustomException;
import com.sparta.myboard.exception.customexception.UserCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserCustomException.class})
    public ResponseEntity<Object> userCustomException(UserCustomException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setErrorMessage(ex.getErrorMessage());
        restApiException.setStatusCode(ex.getStatusCode());
        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PostCustomException.class})
    public ResponseEntity<Object> postCustomException(PostCustomException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setErrorMessage(ex.getErrorMessage());
        restApiException.setStatusCode(ex.getStatusCode());
        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CommentCustomException.class})
    public ResponseEntity<Object> commentCustomException(CommentCustomException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setErrorMessage(ex.getErrorMessage());
        restApiException.setStatusCode(ex.getStatusCode());
        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleApiRequestException(MethodArgumentNotValidException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restApiException.setErrorMessage(ex.getFieldError().getDefaultMessage());
        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }
}
