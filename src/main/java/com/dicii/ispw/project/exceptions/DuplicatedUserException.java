package com.dicii.ispw.project.exceptions;

public class DuplicatedUserException extends Exception{
    public DuplicatedUserException(String message){
        super(message);
    }
    public DuplicatedUserException(){
        super();
    }
}
