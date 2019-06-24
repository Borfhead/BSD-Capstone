/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

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
public class AddCustomerController implements Initializable {

    @FXML TextField firstNameField, lastNameField, emailField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Function called when the add button is pressed. Ensures that all fields
     * are filled in, then inserts to database.
     **/
    @FXML
    public void addBtnPressed(){
        if(firstNameField.getText().equals("")){
            Capstone.makeAlert("Please fill in the first name field.");
        }
        else if(lastNameField.getText().equals("")){
                        Capstone.makeAlert("Please fill in the last name field.");
        }
        else if(emailField.getText().equals("")){
                        Capstone.makeAlert("Please fill in the email field.");
        }
        else{
            String fname = firstNameField.getText();
            String lname = lastNameField.getText();
            String email = emailField.getText();
            DBDriver.insertCustomer(fname, lname, email);
            Capstone.makePosAlert("Customer Added.");
            cancelBtnPressed();
        }
    }
    
    /**
     * Closes the current window.
     */
    @FXML
    public void cancelBtnPressed(){
        Stage s = (Stage)emailField.getScene().getWindow();
        s.close();
        
    }
    
}
