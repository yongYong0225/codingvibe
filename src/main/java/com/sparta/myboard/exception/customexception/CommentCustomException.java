package com.sparta.myboard.exception.customexception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCustomException extends RuntimeException{

    private String errorMessage;
    private int statusCode;

    public CommentCustomException(ErrorCode errorCode) {
        this.errorMessage=errorCode.getErrorMessage();
        this.statusCode=errorCode.getStatusCode();
    }
}
