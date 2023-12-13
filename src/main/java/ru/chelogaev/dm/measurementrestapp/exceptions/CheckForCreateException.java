package ru.chelogaev.dm.measurementrestapp.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CheckForCreateException {
    public static void returnErrorsToClient(BindingResult bindingResult) throws EntityValidateException{
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMsg.append(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new EntityValidateException(errorMsg.toString());
        }
    }
}
