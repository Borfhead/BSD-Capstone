/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;


import capstone.model.DBDriver;
import java.util.Iterator;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */
public class Capstone extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/capstone/view/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void showNewScene(Parent root, String title){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.showAndWait();
    }
    
    public static void makeAlert(String message){
        Alert newAlert = new Alert(Alert.AlertType.WARNING);
        newAlert.setTitle("Invalid");
        newAlert.setHeaderText(message);
        newAlert.setContentText("");
        newAlert.showAndWait();
    }
    
    public static void makePosAlert(String message){
        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
        newAlert.setTitle("Success");
        newAlert.setHeaderText(message);
        newAlert.setContentText("");
        newAlert.showAndWait();
    }
    
    public static void addIntListener(TextField field){
        field.textProperty().addListener((ObservableValue<? extends String> obs, String oldString, String newString) ->{
        if(!newString.matches("\\d{0,9}")){
            field.setText(oldString);
        }
    });
    }
    
}
