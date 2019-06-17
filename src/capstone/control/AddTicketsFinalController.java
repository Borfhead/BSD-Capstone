/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.BusinessEvent;
import capstone.model.Customer;
import capstone.model.DBDriver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class AddTicketsFinalController implements Initializable {

    BusinessEvent event;
    Customer cust;
    @FXML TextField numField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Capstone.addIntListener(numField);
    }
    
    public void addBtnPressed(){
        int num = Integer.parseInt(numField.getText());
        if(num > 8 || num < 1){
            Capstone.makeAlert("Please enter number between 1 and 8");
        }
        else{
            for(int i = 0; i < num; i++){
                DBDriver.insertTicket(cust.getCustId(), event.getEventId());
            }
            Capstone.makePosAlert("Ticket(s) added.");
            cancelBtnPressed();
        }
        
    }
    
    public void cancelBtnPressed(){
        Stage s = (Stage)numField.getScene().getWindow();
        s.close();
    }
    
    public void initValues(BusinessEvent e, Customer c){
        this.event = e;
        this.cust = c;
    }
    
}
