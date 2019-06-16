/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class DBDriver {
    
    private static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:capstone.db");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static int insertCustomer(String fname, String lname, String email){
        String query = "INSERT INTO customers(first_name, last_name, email) "
                + "VALUES(?, ?, ?)";
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, email);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }
    
    public static int insertEvent(String name, LocalDate date, int maxCap){
        DateTimeFormatter fm = DateTimeFormatter.ISO_DATE;
        date.format(fm);
        Date d = Date.valueOf(date);
        String query = "INSERT INTO events(title, event_date, max_capacity) "
                + "VALUES(?, ?, ?)";
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, name);
            stmt.setString(2, d.toString());
            stmt.setInt(3, maxCap);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }
    
    public static int insertTicket(int custId, int eventId){
        String query = "INSERT INTO tickets(customer_id, event_id) "
                + "VALUES(?, ?)";
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, custId);
            stmt.setInt(2, eventId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }
    
    public static ArrayList<BusinessEvent> getAllEvents(){
        String query = "SELECT * FROM events";
        ArrayList<BusinessEvent> toReturn = new ArrayList();
        try(Connection conn = DBDriver.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("event_id");
                String title = rs.getString("title");
                LocalDate date = LocalDate.parse(rs.getString("event_date"));
                int maxCap = rs.getInt("max_capacity");
                toReturn.add(new BusinessEvent(id, title, date, maxCap));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return toReturn;
    }
    
    public static int getTicketSalesForEvent(int eventId){
        String query = "SELECT count(ticket_id) from tickets t\n" +
                        "JOIN events e\n" +
                        "ON t.event_id = e.event_id\n" +
                        "GROUP BY e.event_id\n" +
                        "HAVING e.event_id = ?;";
        int toReturn = 0;
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                toReturn = rs.getInt("count(ticket_id)");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return toReturn;
    }
    
//    public static String getEvent(int eventId){
//        String query = "SELECT event_date FROM events WHERE event_id = ?";
//        try(Connection conn = DBDriver.connect();
//                PreparedStatement stmt = conn.prepareStatement(query)){
//            stmt.setInt(1, eventId);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                return(rs.getString("event_date"));
//            }
//        }
//        catch(SQLException e){
//            System.out.println(e);
//        }
//        return "";
//    }
    
    
}
