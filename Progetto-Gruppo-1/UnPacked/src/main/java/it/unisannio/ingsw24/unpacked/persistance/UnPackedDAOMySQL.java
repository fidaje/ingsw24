package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.Category;
import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;

public class UnPackedDAOMySQL implements UnPackedDAO{

    private static String host = System.getenv("MYSQL_ADDRESS");
    private static String port = System.getenv("MYSQL_PORT");
    private Connection connection;


    private static final String GET_UNPACKEDFOOD = "SELECT * FROM " + TABLE + " WHERE " + ELEMENT_NAME + " = ?";
    private static final String GET_ALL_UNPACKEDFOOD_FOR_NAME = "SELECT " + ELEMENT_NAME + " FROM " + TABLE;

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
    public String createUnPackedFood(UnPackedFood UF) {
        return null;
    }

    @Override
    public Map<String, UnPackedFood> getAllUnPackedFood() {
        return null;
    }

    @Override
    public List<String> getAllUnPackedFoodNames(){

        List<String> names = new ArrayList<>();

        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_ALL_UNPACKEDFOOD_FOR_NAME);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(UnPackedDAO.ELEMENT_NAME));
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
                resultSet.getInt(ELEMENT_AVERAGE_EXPIRY_DATE));
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
        return null;

    }

    @Override
    public UnPackedFood updateUnPackedFood(int averageExpiryDate) {
        return null;
    }

    @Override
    public boolean deleteUnPackedFood(Long frameNumber) {
        return false;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }


}
