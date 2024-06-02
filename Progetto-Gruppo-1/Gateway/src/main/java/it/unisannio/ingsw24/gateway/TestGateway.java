package it.unisannio.ingsw24.gateway;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.OpenFood;
import it.unisannio.ingsw24.entities.OpenFoodPantry;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;

public class TestGateway {

    public static void main(String[] args){

        GatewayLogic l = new GatewayLogicImplementation();

        Food f = l.getOpenFoodPantry("3017624010701", "2024-12-12" , false, 4);

        System.out.println(f);

        //UnPackedFood u = l.getUnPackedFood("mela", true, 22);

        //System.out.println("ID: " + u.getID());

    }
}
