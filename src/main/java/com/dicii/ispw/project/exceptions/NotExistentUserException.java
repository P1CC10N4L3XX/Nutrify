package com.dicii.ispw.project.exceptions;

public class NotExistentUserException extends Exception{
    public NotExistentUserException(String message){
        super(message);
    }
    public NotExistentUserException(){
        super();
    }
}
