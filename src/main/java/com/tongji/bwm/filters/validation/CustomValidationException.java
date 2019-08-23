package com.tongji.bwm.filters.validation;

import com.tongji.bwm.filters.CustomException;
import org.springframework.validation.FieldError;

import java.util.List;

public class CustomValidationException extends CustomException {



    public CustomValidationException(String customMessage, List<FieldError> customFieldErrors) {
        this.customMessage = customMessage;
        String customError = "";
        if(customFieldErrors!=null){
            for(FieldError fieldError:customFieldErrors){
                customError += fieldError.getDefaultMessage()+"\r\n";
            }
        }
        this.customError = customError;
    }
}
