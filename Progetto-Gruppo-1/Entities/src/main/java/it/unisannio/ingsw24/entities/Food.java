package it.unisannio.ingsw24.entities;

import java.time.LocalDate;

public abstract class Food {

    private String name;
    protected LocalDate expirationDate;
    private boolean isExpired;
    private boolean isFridge;
    private int quantity;



    public Food(String name, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.isExpired = isExpired;
        this.isFridge = isFridge;
        this.quantity = quantity;
    }
    
    protected Food(String name, boolean isExpired, boolean isFridge, int quantity) {
        this.name = name;
        this.isExpired = isExpired;
        this.isFridge = isFridge;
        this.quantity = quantity;
    }

    abstract public void setExpirationDate(String date);

    //la dispensa ogni giorno farà un check per verificare se l'alimento è scaduto
    public void setIsExpired(){
        LocalDate today = LocalDate.now();
        this.isExpired = today.isAfter(this.expirationDate);
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }


    public LocalDate getExpirationDate(){
        return this.expirationDate;
    }
    
    public boolean getIsExpired(){
        return this.isExpired;
    }

    public boolean getIsFridge(){
        return this.isFridge;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String toString(){
        return "Name: " + name + " Expiration Date: " + expirationDate + " Is Expired: " + isExpired + " Is Fridge: " + isFridge + " Quantity: " + quantity;
    }
}
