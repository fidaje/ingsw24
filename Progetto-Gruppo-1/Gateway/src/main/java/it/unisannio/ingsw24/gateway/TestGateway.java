package it.unisannio.ingsw24.gateway;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;

public class TestGateway {

    public static void main(String[] args){

        GatewayLogic l = new GatewayLogicImplementation();

        Pantry p = l.getPantry(1);

        System.out.println("Pantry non c'è " );

        //UnPackedFood u = l.getUnPackedFood("mela", true, 22);

        //System.out.println("ID: " + u.getID());

    }
}
