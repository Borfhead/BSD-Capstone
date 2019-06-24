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
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class CreateUserController implements Initializable {

    @FXML TextField userNameField, passwordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Called when the add button is pressed.  Ensures that all fields are
     * filled in, then inserts the new user into the database.
     */
    @FXML
    public void addBtnPressed(){
        String uName = userNameField.getText();
        String pass = passwordField.getText();
        if(uName.equals("")){
            Capstone.makeAlert("Please fill every field");
        }
        else if(pass.equals("")){
            Capstone.makeAlert("Please fill every field");
        }
        else{
            DBDriver.insertUser(uName, pass);
            Capstone.makePosAlert("User created.");
            close();
        }
        
    }
    
    /**
     * Closes the window.
     */
    public void close(){
        Scene scene = userNameField.getScene();
        Stage s = (Stage)scene.getWindow();
        s.close();
    }
    
}
