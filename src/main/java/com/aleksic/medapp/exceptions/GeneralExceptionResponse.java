package com.aleksic.medapp.exceptions;

import lombok.Getter;
import lombok.Setter;

public class GeneralExceptionResponse {
    @Getter
    @Setter
    private String errorMessage;

    public GeneralExceptionResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
