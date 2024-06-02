package it.unisannio.ingsw24.gateway;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;

import java.util.Map;

public class TestGateway {

    public static void main(String[] args){

        GatewayLogic l = new GatewayLogicImplementation();

        Map<String, UnPackedMySQL> m = ((GatewayLogicImplementation) l).getAllUnPackedFood();
        int i = 0;

        for(String s : m.keySet()) {
            System.out.println(m.get(s));
            System.out.println(m.get(s).getName());
            i++;
            if (i == 10) break;
        }

        System.out.println(m.keySet().size());

        //UnPackedFood u = l.getUnPackedFood("mela", true, 22);

        //System.out.println("ID: " + u.getID());

    }
}
