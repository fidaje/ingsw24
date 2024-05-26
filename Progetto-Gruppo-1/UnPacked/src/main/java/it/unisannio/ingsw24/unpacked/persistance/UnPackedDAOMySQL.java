package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.Category;
import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.Map;
import java.sql.*;

public class UnPackedDAOMySQL implements UnPackedDAO{

    private static String host = System.getenv("MYSQL_ADDRESS");
    private static String port = System.getenv("MYSQL_PORT");
    private Connection connection;

    private static final String GET_FRUIT = "SELECT * FROM " + TABLE_FRUIT + " WHERE " + ELEMENT_NAME + " = ?";
    private static final String GET_TABLE_NAME = "SELECT DISTINCT"+ " table_name" +
    " FROM information_schema.columns" + " WHERE table_schema = 'PROVAWM'";

    public UnPackedDAOMySQL(){
        if (host == null) {
            host = "172.17.0.4";
        }
        if (port == null) {
            port = "3306";
        }
        String URI = "jdbc:mysql://" + host + ":" + port + "/" + DATABASE_NAME;

        try {
            this.connection = DriverManager.getConnection(URI, "root", "mysql");
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


    public static UnPackedFood unPackedFoodFromResultSet(ResultSet resultSet) throws SQLException{
        return new UnPackedFood(resultSet.getString(ELEMENT_NAME),
                resultSet.getString(ELEMENT_UNIQUE_ID),
                null,
                false,
                false,
                1,
                Category.OTHER,
                resultSet.getInt(ELEMENT_Average_Expiry_Date));
    }
    @Override
    public UnPackedFood getUnPackedFood(String name) {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_FRUIT);
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
