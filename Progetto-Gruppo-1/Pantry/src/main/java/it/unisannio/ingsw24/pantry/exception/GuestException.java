package it.unisannio.ingsw24.pantry.exception;

public class GuestException extends Exception{

    public GuestException(String message){
        super(message);
    }

    public GuestException(String message, Throwable cause){
        super(message, cause);
    }

}
