package com.dicii.ispw.project.exceptions;

public class NotExistentNotification extends Exception{
    public NotExistentNotification(){
        super();
    }
    public NotExistentNotification(String message){
        super(message);
    }
}
