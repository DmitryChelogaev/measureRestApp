package ru.chelogaev.dm.measurementrestapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.chelogaev.dm.measurementrestapp.models.ErrorResponseModel;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptions {
    @ExceptionHandler
    private ResponseEntity<ErrorResponseModel> handleNotCreatedException(EntityValidateException e) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(
                e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponseModel> handleNotFoundException(EntityNotFoundException e) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(
                e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(errorResponseModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponseModel> illegalArgumentException(IllegalArgumentException e) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(
                e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }
}
