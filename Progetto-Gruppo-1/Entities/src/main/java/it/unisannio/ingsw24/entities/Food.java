package it.unisannio.ingsw24.entities;

import java.time.LocalDate;

public abstract class Food {

    private String name;
    private String id;
    protected LocalDate expirationDate;
    private boolean isExpired;
    private boolean isFridge;
    private int quantity;

    public Food(String name, String id, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity) {
        this.name = name;
        this.id = id;
        this.expirationDate = expirationDate;
        this.isExpired = isExpired;
        this.isFridge = isFridge;
        this.quantity = quantity;
    }


    abstract public void setExpirationDate();

    //la dispensa ogni giorno farà un check per verificare se l'alimento è scaduto
    public void setIsExpired(){
        LocalDate today = LocalDate.now();
        isExpired = today.isAfter(expirationDate);
    }

    public String toString(){
        return "Name: " + name + " Id: " + id + " Expiration Date: " + expirationDate + " Is Expired: " + isExpired + " Is Fridge: " + isFridge + " Quantity: " + quantity;
    }
}
