package it.unisannio.ingsw24.gateway.exception;

public class UserNotValidException extends Exception{

    public UserNotValidException(String message){
        super(message);
    }

    public UserNotValidException(){
        super();
    }

}
