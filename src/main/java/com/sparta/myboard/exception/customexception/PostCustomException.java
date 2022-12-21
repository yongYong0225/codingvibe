package com.sparta.myboard.exception.customexception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostCustomException extends RuntimeException{

    private String errorMessage;
    private int statusCode;

    public PostCustomException(ErrorCode errorCode) {
        this.errorMessage=errorCode.getErrorMessage();
        this.statusCode=errorCode.getStatusCode();
    }
}
