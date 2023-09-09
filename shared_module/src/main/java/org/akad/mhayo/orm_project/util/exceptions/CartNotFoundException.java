package org.akad.mhayo.orm_project.util.exceptions;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(String message){

        super(message);
    }
}