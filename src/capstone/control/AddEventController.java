/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.DBDriver;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class AddEventController implements Initializable {

    @FXML TextField titleField, capacityField;
    @FXML DatePicker dateField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Capstone.addIntListener(capacityField);
    }    
    
    @FXML
    public void addBtnPressed(){
        if(titleField.getText().equals("")){
            Capstone.makeAlert("Please enter a title.");
        }
        else if(dateField.getEditor().getText().equals("")){
            Capstone.makeAlert("Please pick a date.");
        }
        else if(capacityField.getText().equals("")){
            Capstone.makeAlert("Please enter a max capacity");
        }
        else if(Integer.parseInt(capacityField.getText()) > 160){
            Capstone.makeAlert("Max capacity cannot be higher than 160.");
        }
        else{
            String title = titleField.getText();
            LocalDate date = dateField.getValue();
            int maxCap = Integer.parseInt(capacityField.getText());
            DBDriver.insertEvent(title, date, maxCap);
            Capstone.makePosAlert("Event Added");
            cancelBtnPressed();
        }
    }
    
    @FXML 
    public void cancelBtnPressed(){
        Stage s = (Stage)titleField.getScene().getWindow();
        s.close();
        
    }
    
}
