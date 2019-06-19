/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.BusinessEvent;
import capstone.model.DBDriver;
import static capstone.model.DBDriver.getTicketsPerTable;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML TableColumn customerCol, tableCol, ticketCol;
    @FXML TableView infoTable;
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
                c.setVisible(false);
            }
        }
        
        Map<Character, Integer> seats = getTicketsPerTable(e.getEventId());
        Iterator iter = seats.entrySet().iterator();
        int count2 = 0;
        while(iter.hasNext()){
            Map.Entry pair = (Map.Entry)iter.next();
            tablePane.getChildren().get(count2).setVisible(true);
            count2++;
        }
        infoTable.setItems(FXCollections.observableArrayList(DBDriver.getTicketsPerCustomerByEvent(e.getEventId())));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("custName"));
        tableCol.setCellValueFactory(new PropertyValueFactory<>("tableChar"));
        ticketCol.setCellValueFactory(new PropertyValueFactory<>("ticketNum"));
        
    }
    
}
