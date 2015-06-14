package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance = null;

    public static DBConnection getInstance(){
        if(instance==null){
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
            instance = new DBConnection();
        }
        return instance;
    }

    private Connection connection;

    private DBConnection() {}

    public Connection getConnection() {
        connection = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            System.out.println("Connection done");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
