package ru.chelogaev.dm.measurementrestapp.exceptions;

public class EntityValidateException extends RuntimeException{
    public EntityValidateException(String message) {
        super(message);
    }
}
