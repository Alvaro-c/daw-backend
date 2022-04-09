package com.freetoursegovia.freetoursegovia.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alvaro Canas
 * This class handles the requests to the DB
 */
public class Connections {


    /**
     * Opens a Connection with the DB to be passed to another method
     * so it is possible to execute queries
     * @return con as the working connection with the DB
     */
    public static Connection openConnection() {

        Connection con = null;

        try {
            //path for the Driver to work with MySQL
            Class.forName("com.mysql.jdbc.Driver");

            /*
            The following commented lines hold different information to connect
            with different databases for test purposes.
            Comment or uncomment depending on the DB to be used. Only one database could be uncommented
             */

//            Remote Host Database:
//            String bbdd = "jdbc:mysql://45.87.81.0:3306/u267900572_ftsegovia?useSSL=false";
//            String usuario = "u267900572_ftsegovia";
//            String password = "";

//            Laptop Database:
            String bbdd = "jdbc:mysql://localhost:3306/freetoursegoviav2?useSSL=false";
            String usuario = "root";
            String password = "roble";

//          Raspberry Database:
//            String bbdd = "jdbc:mysql://192.168.31.35:3306/freetoursegovia?useSSL=false";
//            String usuario = "alvaro";
//            String password = "Awesome!";

            // Open connection with DB
            con = DriverManager.getConnection(bbdd, usuario, password);

            return con;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when opening the connection with the DB");

            return con;
        }



    }

    /**
     * This method execute a insert-type query
     * @param query The query to be executed
     * @param con The open connection with the DB
     * @return a boolean that will be true if data has been added correctly to the DB or false otherwise
     */
    public static boolean insertQuery(String query, Connection con) {

        Statement stm = null;

        try {

            stm = con.createStatement();
            //Execute query
            stm.executeUpdate(query);

            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();

            return false;
        }



    }


}
