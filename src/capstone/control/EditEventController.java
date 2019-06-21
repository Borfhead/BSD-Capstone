/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.BusinessEvent;
import capstone.model.DBDriver;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
public class EditEventController implements Initializable {
    
    @FXML TextField titleField, capacityField;
    @FXML DatePicker dateField;
    BusinessEvent event;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Capstone.addIntListener(capacityField);
    }    
    
    public void initEvent(BusinessEvent event){
        this.event = event;
        titleField.setText(event.getTitle());
        dateField.setValue(event.getDate());
        capacityField.setText(Integer.toString(event.getMaxCapacity()));
    }
    
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
        else if(Integer.parseInt(capacityField.getText()) < event.getMaxCapacity()){
            Capstone.makeAlert("Max capacity cannot be lower than previous. (" +event.getMaxCapacity()+ ")");
        }
        else{
            int id = event.getEventId();
            String title = titleField.getText();
            LocalDate date = dateField.getValue();
            int maxCap = Integer.parseInt(capacityField.getText());
            DBDriver.updateEvent(id, title, date, maxCap);
            Capstone.makePosAlert("Event Updated");
            cancelBtnPressed();
        }
    }
    
    public void cancelBtnPressed(){
        Stage s = (Stage)titleField.getScene().getWindow();
        s.close();
    }
    
    public void deleteEvent(){
        DBDriver.deleteEvent(event.getEventId());
        cancelBtnPressed();
    }
}
