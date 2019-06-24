/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.DBDriver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class LoginController implements Initializable {

    @FXML TextField userNameField, passwordField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Checks to see if the username and password combination are found in the
     * database.  If found, a new window will replace the login screen allowing
     * the user into the application.  If the credentials are not found or are 
     * empty, an alert will notify the user.
     * @throws IOException 
     */
    @FXML
    public void loginBtnPressed() throws IOException{
        String uName = userNameField.getText();
        String pass = passwordField.getText();
        if(DBDriver.authenticate(uName, pass)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/FXMLDocument.fxml"));
            Parent root = loader.load();
            loader.<FXMLDocumentController>getController().initUserType(uName);
            Scene scene = (Scene)userNameField.getScene();
            Stage stage = (Stage)scene.getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Event Planner");
        }
        else{
            Capstone.makeAlert("Invalid Credentials");
        }
        
    }
    
    
    /**
     * Exits the application
     */
    @FXML
    public void cancelBtnPressed(){
        System.exit(0);
    }
    
}
