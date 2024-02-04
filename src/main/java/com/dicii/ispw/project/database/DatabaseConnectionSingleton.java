package com.dicii.ispw.project.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionSingleton {

    private static DatabaseConnectionSingleton dbConn;
    private Connection conn;


    private DatabaseConnectionSingleton()  {
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/com/dicii/ispw/project/database/db.properties")) {

            Properties prop = new Properties();
            prop.load(fileInputStream);

            String username = prop.getProperty("dbUserName");
            String password = prop.getProperty("dbPassword");
            String url = prop.getProperty("dbUrl");

            conn = DriverManager.getConnection(url,username,password);

        } catch ( IOException | SQLException e) {
            this.conn = null;
        }
    }

    public Connection getConn()  {
        return conn;
    }



    public static synchronized DatabaseConnectionSingleton getInstance(){
        if(dbConn == null){
            dbConn = new DatabaseConnectionSingleton();
        }
        return dbConn;
    }

    public static void deleteInstance() {
        dbConn = null;
    }
}
