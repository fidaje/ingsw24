package it.unisannio.ingsw24.entities;

public class User {

    int id;
    String email;
    String password;

    public User(int id,String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId(){
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }


    public void setEmail(String nEmail){
        this.email = nEmail;
    }

    public void setPassword(String nPass){
        this.password = nPass;
    }

    public String toString(){
        return "ID: " + this.id + " Email: " + this.email;
    }
}
