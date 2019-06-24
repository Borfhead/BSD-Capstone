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
    
    /**
     * Function called when the add button is pressed. Currently supports 8
     * tickets per customer per event.
     **/
    public void addBtnPressed(){
        int num = Integer.parseInt(numField.getText());
        if(num > 8 || num < 1){
            Capstone.makeAlert("Please enter number between 1 and 8");
        }
        else{
            if(DBDriver.insertTickets(cust.getCustId(), event.getEventId(), num) == null){
                Capstone.makeAlert("Not enough seats available");
                cancelBtnPressed();
            }
            else{
                Capstone.makePosAlert("Ticket(s) added.");
                cancelBtnPressed();
            }
        }
        
    }
    
    /**
     * Closes the current window.
     */
    public void cancelBtnPressed(){
        Stage s = (Stage)numField.getScene().getWindow();
        s.close();
    }
    
    /**
     * Method to allow initialization of variables based on selection in the
     * previous window.
     * @param e Event that the tickets will be added to
     * @param c Customer that the tickets will be added to
     */
    public void initValues(BusinessEvent e, Customer c){
        this.event = e;
        this.cust = c;
    }
    
}
