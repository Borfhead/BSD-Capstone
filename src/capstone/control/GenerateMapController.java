/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.BusinessEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class GenerateMapController implements Initializable {

    BusinessEvent event;
    @FXML AnchorPane mainPane;
    @FXML GridPane tablePane;
    String l = "ABCDEFGHIJKLMNOPQRST";
    char[] letters = l.toCharArray();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void initEvent(BusinessEvent e){
        event = e;
        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                CircleTable c = new CircleTable();
                c.setText(String.valueOf(letters[count]));
                count++;
                tablePane.add(c, j, i);
            }
        }
        
    }
    
}
