package com.mono.entriesjdbc.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final String errorCode;
    private final String errorText;

    public CustomException(String errorCode, String errorText) {
        super(errorText);
        this.errorCode = errorCode;
        this.errorText = errorText;
    }

}
