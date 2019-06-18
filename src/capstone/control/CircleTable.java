/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Dylan
 */
public class CircleTable extends AnchorPane{
    
    @FXML Text tableText, seat1, seat2, seat3,
            seat4, seat5, seat6, seat7, seat8;
    
    public CircleTable(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/CircleTable.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch ( IOException e ) {
            System.out.println(e);
        }
    }
    
    public void setText(String text){
        tableText.setText(text);
    }
    
    public String getText(){
        return tableText.getText();
    }
    
}
