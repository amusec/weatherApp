package com.mycompany.weatherapp;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseOperations {
    static Connection conn;
    private String username = null;
    
    public String getUsername(){
        return username;
    }
    public DatabaseOperations(){
        establishConnection();
        String query = "Create TABLE IF NOT EXISTS userCredentials(id int UNIQUE AUTO_INCREMENT NOT NULL,username VARCHAR(100) PRIMARY KEY NOT NULL, password VARCHAR(200) NOT NULL)";
        String query2 = "CREATE TABLE IF NOT EXISTS userFavPlaces(id int UNIQUE AUTO_INCREMENT NOT NULL,username VARCHAR(100), name VARCHAR(100), region VARCHAR(100), country VARCHAR(100), FOREIGN KEY(username) REFERENCES userCredentials (username), UNIQUE(username,name,region,country) )";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    public boolean establishConnection(){
        String url = "jdbc:mysql://localhost:3306/heroku_67cfe86c5b5b98b";
        String user = "b6d189d1d2d7ea";
        String password = "39a3c234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            if(conn != null){
                return true;
            }else{
                return false;
            }
        }catch(ClassNotFoundException | SQLException e){
            return false;
        }
    }
    public boolean addUser(String username,String password){
        String query = "INSERT INTO userCredentials( username , password) VALUES ('"+username+"','"+password+"')";
        try{
            Statement st = conn.createStatement();
           int rowsAffected = st.executeUpdate(query);
            return rowsAffected > 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }  
    }
    public boolean checkLogin(String username, String password){
        try{
            PreparedStatement st = conn.prepareStatement("SELECT * FROM userCredentials where username = ? and password = ?");
            st.setString(1, username);
            st.setString(2,password);
            ResultSet rs = st.executeQuery();
            System.out.println(st.toString());
            if(rs.next()){
                username = rs.getString("username");
                return true;
            }return false;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean addFavoritePlace( String username, String region, String country, String place) {
        String query = "INSERT INTO userFavPlaces (username, name, region, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, username);
            st.setString(2, place);
            st.setString(3, region);
            st.setString(4, country);
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public boolean closeConnection(){
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public ArrayList<String> getFavPlaces(String username) {
        ArrayList<String> favoritePlaces = new ArrayList<String>();
        String query = "SELECT name, region, country FROM userFavPlaces WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String place = String.format("%s, %s, %s", rs.getString("name"), rs.getString("region"), rs.getString("country"));
                favoritePlaces.add(place);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return favoritePlaces;
    } 
}
