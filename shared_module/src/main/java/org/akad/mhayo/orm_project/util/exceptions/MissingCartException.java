package org.akad.mhayo.orm_project.util.exceptions;

public class MissingCartException extends RuntimeException{

    public MissingCartException(String message){
        super(message);
    }
}