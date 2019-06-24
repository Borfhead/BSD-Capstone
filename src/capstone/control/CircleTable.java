/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Class represents physical round-shaped dining tables for use in generating
 * a seating chart. This particular class of table fits 8 seats.
 * @author Dylan
 */
public class CircleTable extends AnchorPane{
    
    @FXML Text tableText, seat1, seat2, seat3,
            seat4, seat5, seat6, seat7, seat8;
    @FXML Circle tableCircle;
    ArrayList<Text> seats;
    
    public CircleTable(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/CircleTable.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch ( IOException e ) {
            System.out.println(e);
        }
        seats = new ArrayList();
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
        seats.add(seat4);
        seats.add(seat5);
        seats.add(seat6);
        seats.add(seat7);
        seats.add(seat8);
        
    }
    
    /**
     * Sets the text within the center of the table.
     * @param text 
     */
    public void setText(String text){
        tableText.setText(text);
    }
    
    /**
     * Gets the text at the center of the table
     * @return 
     */
    public String getText(){
        return tableText.getText();
    }
    
    /**
     * Returns an array of Text objects representing seat numbers.
     * @return 
     */
    public ArrayList<Text> getSeats(){
        return seats;
    }
    
    /**
     * Allows the return of the Circle object if properties of the circle are 
     * desired, e.g, background color.
     * @return The Circle Object.
     */
    public Circle getCircle(){
        return tableCircle;
    }
    
}
