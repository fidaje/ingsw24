package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.Category;
import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class UnPackedDAOMySQL implements UnPackedDAO{

    private static String host = System.getenv("MYSQL_ADDRESS");
    private static String port = System.getenv("MYSQL_PORT");
    private Connection connection;


    private static final String GET_UNPACKEDFOOD = "SELECT * FROM " + TABLE + " WHERE " + ELEMENT_NAME + " = ?";
    private static final String GET_ALL_UNPACKEDFOOD_FOR_NAME = "SELECT " + ELEMENT_NAME + " FROM " + TABLE;
    private static final String POST_UNPACKEDFOOD = "INSERT INTO " + TABLE + "(" + ELEMENT_UNIQUE_ID + ", " + ELEMENT_NAME + ", " + ELEMENT_AVERAGE_EXPIRY_DATE + ", " + ELEMENT_CATEGORY + ")" 
                                + " VALUES ( ?, ?, ?, ?)";
    private static final String DELETE_UNPACKEDFOOD = "DELETE FROM UNPACKEDFOOD WHERE ID = ?";

    public UnPackedDAOMySQL(){
        if (host == null) {
            host = "172.17.0.4";
        }
        if (port == null) {
            port = "3306";
        }
        String URI = "jdbc:mysql://" + host + ":" + port + "/" + DATABASE_NAME;

        try {
            this.connection = DriverManager.getConnection(URI, "root", "cusas-mysql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean dropDB() {
        return false;
    }


    @Override
    public boolean createUnPackedFood(String ID, String name, int average_exp_date, String category) {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(POST_UNPACKEDFOOD);
            preparedStmt.setString(1, ID);
            preparedStmt.setString(2, name);
            preparedStmt.setInt(3, average_exp_date);
            preparedStmt.setString(4, category);
            
            int affectedRaw = preparedStmt.executeUpdate();

            if (affectedRaw == 1){
                return true;
            }
            else    
                return false;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, UnPackedFood> getAllUnPackedFood() {
        
        Map<String, UnPackedFood> unpackedfoods = new HashMap<>();
        List<String> names = this.getAllUnPackedFoodNames();
        for( String name : names){
            UnPackedFood upf = this.getUnPackedFood(name);
            unpackedfoods.put(upf.getID(), upf);
        }

        return unpackedfoods;
    }

    @Override
    public List<String> getAllUnPackedFoodNames(){

        List<String> names = new ArrayList<>();

        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_ALL_UNPACKEDFOOD_FOR_NAME);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                names.add(rs.getString(UnPackedDAO.ELEMENT_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;

    }



    public static UnPackedFood unPackedFoodFromResultSet(ResultSet resultSet) throws SQLException{

        String cat = resultSet.getString(ELEMENT_CATEGORY);

        return new UnPackedFood(resultSet.getString(ELEMENT_NAME),
                resultSet.getString(ELEMENT_UNIQUE_ID),
                false,
                false,
                1,
                Category.valueOf(cat.toUpperCase()),
                resultSet.getString(ELEMENT_AVERAGE_EXPIRY_DATE));
    }

    @Override
    public UnPackedFood getUnPackedFood(String name) {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_UNPACKEDFOOD);
            preparedStmt.setString(1, name);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                UnPackedFood uf = unPackedFoodFromResultSet(rs);
                return uf;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // FACTORY

    }

    @Override
    public UnPackedFood updateUnPackedFood(int averageExpiryDate) {
        return null;
    }

    @Override
    public boolean deleteUnPackedFood(String id) {
        return false;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }


}
