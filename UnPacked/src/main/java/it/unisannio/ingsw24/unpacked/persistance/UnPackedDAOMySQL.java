package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.Category;
import it.unisannio.ingsw24.entities.UnPackedMySQL;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * This class is responsible for the communication between the application and the database.
 * It implements the UnPackedDAO interface.
 */
public class UnPackedDAOMySQL implements UnPackedDAO{

    private static String host = System.getenv("MYSQL_ADDRESS");
    private static String port = System.getenv("MYSQL_PORT");
    private Connection connection;


    private static final String GET_UNPACKED = "SELECT * FROM " + TABLE + " WHERE " + ELEMENT_NAME + " = ?";
    private static final String GET_ALL_UNPACKED_FOR_NAME = "SELECT " + ELEMENT_NAME + " FROM " + TABLE;
    private static final String POST_UNPACKED = "INSERT INTO " + TABLE + "(" + ELEMENT_UNIQUE_ID + ", " + ELEMENT_NAME + ", " + ELEMENT_AVERAGE_EXPIRY_DAYS + ", " + ELEMENT_CATEGORY + ")"
                                + " VALUES ( ?, ?, ?, ?)";
    private static final String DELETE_UNPACKED = "DELETE FROM " + TABLE + " WHERE " + ELEMENT_UNIQUE_ID + " = ?";
    private static final String UPDATE_AVERAGE_EXPIRE_DAYS_UNPACKED = "UPDATE " + TABLE + " SET " + ELEMENT_AVERAGE_EXPIRY_DAYS + " = ?" + " WHERE " + ELEMENT_UNIQUE_ID + " = ?";
    private static final String GET_NEXT_ID = "SELECT MAX(" + ELEMENT_UNIQUE_ID + ") as maximum FROM " + TABLE;

    /**
     * This constructor creates a connection to the database.
     */
    public UnPackedDAOMySQL(){
        if (host == null) {
            host = "172.31.6.1";
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

    /**
     * This method creates an UnPackedMySQL object from a ResultSet.
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static UnPackedMySQL UnPackedMySQLFromResultSet(ResultSet resultSet) throws SQLException{

        //String cat = resultSet.getString(ELEMENT_CATEGORY);

        return new UnPackedMySQL(resultSet.getInt(ELEMENT_UNIQUE_ID),
                resultSet.getString(ELEMENT_NAME),
                resultSet.getInt(ELEMENT_AVERAGE_EXPIRY_DAYS),
                Category.valueOf(resultSet.getString(ELEMENT_CATEGORY).toUpperCase()));
    }

    @Override
    public int getNextId() {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_NEXT_ID);
            ResultSet resultSet = preparedStmt.executeQuery();
            if (resultSet.next()) {
                int max = resultSet.getInt("maximum") + 1;
                return max;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public UnPackedMySQL getUnPackedMySQL(String name) {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_UNPACKED);
            preparedStmt.setString(1, name);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                UnPackedMySQL f = UnPackedMySQLFromResultSet(rs);
                return f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // FACTORY
    }

    @Override
    public int createUnPackedMySQL(String name, int averageExpiryDays, String category) {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(POST_UNPACKED);
            int id = this.getNextId();
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, name);
            preparedStmt.setInt(3, averageExpiryDays);
            preparedStmt.setString(4, category);
            
            int affectedRows = preparedStmt.executeUpdate();

            return id;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean deleteUnPackedMySQL(int ID) {
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(DELETE_UNPACKED);
            preparedStmt.setInt(1, ID);
            int affectedRows = preparedStmt.executeUpdate();

            return affectedRows == 1;
           
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public List<String> getAllUnPackedMySQLNames(){

        List<String> names = new ArrayList<>();

        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(GET_ALL_UNPACKED_FOR_NAME);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                names.add(rs.getString(UnPackedDAO.ELEMENT_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    @Override
    public boolean updateUnPackedMySQL(int ID, int averageExpiryDays) {
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_AVERAGE_EXPIRE_DAYS_UNPACKED);
            preparedStatement.setInt(2, ID);
            preparedStatement.setInt(1, averageExpiryDays);
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows == 1;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dropDB() {
        return false;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }
}
