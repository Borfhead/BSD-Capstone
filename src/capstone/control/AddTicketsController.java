/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.control;

import capstone.model.BusinessEvent;
import capstone.model.Customer;
import capstone.model.DBDriver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class AddTicketsController implements Initializable {

    @FXML TextField searchField;
    @FXML Button createCustomerBtn, addTicketsBtn;
    @FXML TableColumn idColumn, lastNameColumn, firstNameColumn, emailColumn;
    @FXML TableView customerTable;
    BusinessEvent event;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCustomerTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        searchField.textProperty().addListener((ObservableValue<? extends String> obs, String oldString, String newString) ->{
        if(newString.equals("")){
            populateCustomerTable();
        }
        else{
            filterCustomerTable(newString);
        }
    });
    }
    
    public void populateCustomerTable(){
        customerTable.setItems(FXCollections.observableArrayList(DBDriver.getAllCustomers()));
    }
    
    public void filterCustomerTable(String search){
        customerTable.setItems(FXCollections.observableArrayList(DBDriver.broadCustomerSearch(search)));
    }
    
    public void addCustomerBtnPressed() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/capstone/view/AddCustomer.fxml"));
        Capstone.showNewScene(root, "Add Customer");
        populateCustomerTable();
    }
    
    public void addTicketBtnPressed() throws IOException{
        if(customerTable.getSelectionModel().getSelectedItem() == null){
            Capstone.makeAlert("Please select a customer");
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/AddTicketsFinal.fxml"));
            Parent root = loader.load();
            loader.<AddTicketsFinalController>getController().initValues(event, (Customer)customerTable.getSelectionModel().getSelectedItem());
            Capstone.showNewScene(root, "Add Ticket");
            cancelBtnPressed();
        }
    }
    
    public void initEvent(BusinessEvent e){
        this.event = e;
    }
    
    public void cancelBtnPressed(){
        Stage s = (Stage)searchField.getScene().getWindow();
        s.close();
    }
    
}
