package com.dicii.ispw.project.exceptions;

public class DuplicatedNotificationException extends Exception{
    public DuplicatedNotificationException(){
        super();
    }
    public DuplicatedNotificationException(String message){
        super(message);
    }
}
