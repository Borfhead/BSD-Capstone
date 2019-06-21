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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    
    public static ArrayList<Integer> insertTickets(int custId, int eventId, int numTickets){
        String query = "INSERT INTO tickets(customer_id, event_id, table_char) "
                + "VALUES(?, ?, ?)";
        Map<Character, Integer> seats = getTicketsPerTable(eventId);
        Iterator iter = seats.entrySet().iterator();
        char tableChar = 'A';
        while(iter.hasNext()){
            Map.Entry pair = (Map.Entry)iter.next();
            if(numTickets + (int)pair.getValue() <= 8){
                tableChar = (char)pair.getKey();
                break;
            }
            else{
                tableChar = (char)((char)pair.getKey() + 1);
            }
        }
        if(tableChar == 'U'){
            return null;
        }
        
        ArrayList<Integer> toReturn = new ArrayList();
        try(Connection conn = DBDriver.connect();
            PreparedStatement stmt = conn.prepareStatement(query)){
        stmt.setInt(1, custId);
        stmt.setInt(2, eventId);
        stmt.setString(3, String.valueOf(tableChar));
        for(int i = numTickets; i > 0; i--){
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            toReturn.add(rs.getInt(1));
        }

        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return toReturn;
    }
    
    public static ArrayList<Customer> getAllCustomers(){
        String query = "SELECT * FROM customers";
        ArrayList<Customer> toReturn = new ArrayList();
        try(Connection conn = DBDriver.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("customer_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String email = rs.getString("email");
                toReturn.add(new Customer(id, fname, lname, email));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return toReturn;
    }
    
    public static ArrayList<Customer> broadCustomerSearch(String search){
        String query = "select * from customers \n" +
                        "where customer_id = ? \n" +
                        "or first_name like ? \n" +
                        "or last_name like ?\n" +
                        "or email like ?;";
        int index = -10;
        ArrayList<Customer> toReturn = new ArrayList();
        try(Connection conn = DBDriver.connect(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, search);
            stmt.setString(2, "%" +search+ "%");
            stmt.setString(3, "%" +search+ "%");
            stmt.setString(4, "%" +search+ "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("customer_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String email = rs.getString("email");
                toReturn.add(new Customer(id, fname, lname, email));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return toReturn;
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
    
    public static BusinessEvent getEvent(int eventId){
        String query = "SELECT * FROM events WHERE event_id = ?;";
        BusinessEvent toReturn = new BusinessEvent();
        try(Connection conn = DBDriver.connect(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("event_id");
                String title = rs.getString("title");
                LocalDate date = LocalDate.parse(rs.getString("event_date"));
                int maxCap = rs.getInt("max_capacity");
                toReturn = new BusinessEvent(id, title, date, maxCap);
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
    
    public static Map<Character, Integer> getTicketsPerTable(int eventId){
        String query = "select table_char, count(ticket_id) num_tickets\n" +
                        "from tickets\n" +
                        "where event_id = ? \n" +
                        "group by table_char;";
        Map<Character, Integer> toReturn = new HashMap<>();
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                char c = rs.getString("table_char").charAt(0);
                int numTickets = rs.getInt("num_tickets");
                toReturn.put(c, numTickets);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return toReturn;
    }
    
    public static ArrayList<TableUtil> getTicketsPerCustomerByEvent(int eventId){
        String query = "select c.last_name, t.table_char, count(ticket_id) num\n" +
                        "from tickets t, customers c\n" +
                        "where c.customer_id = t.customer_id\n" +
                        "and t.event_id = ? \n" +
                        "group by c.last_name;";
        ArrayList<TableUtil> toReturn = new ArrayList();
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String lname = rs.getString("last_name");
                String tchar = rs.getString("table_char");
                int num = rs.getInt("num");
                TableUtil util = new TableUtil(lname, tchar, num);
                toReturn.add(util);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return toReturn;
    }
    
    public static boolean updateEvent(int id, String title, LocalDate date, int maxCap){
        String query = "UPDATE events \n"
                        + "SET title = ?, \n"
                        + "event_date = ?, \n"
                        + "max_capacity = ? \n"
                        + "WHERE event_id = ?;";
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, title);
            stmt.setString(2, Date.valueOf(date).toString());
            stmt.setInt(3, maxCap);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    
    public static void deleteEvent(int eventId){
        String query = "DELETE from events \n"
                        + "WHERE event_id = ?;";
        try(Connection conn = DBDriver.connect();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    
}
