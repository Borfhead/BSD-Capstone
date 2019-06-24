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
    
    /**
     * Populates the customer table with data.  Sets appropriate property values
     * for each column in the table.  Adds a listener on the search field that
     * updates the table with each keystroke.
     */
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
    
    /**
     * Resets the customer table with updated data.
     */
    public void populateCustomerTable(){
        customerTable.setItems(FXCollections.observableArrayList(DBDriver.getAllCustomers()));
    }
    
    /**
     * Filters the data within the customer table to only show data that matches
     * a search string.
     * @param search String to filter customer data within the table.
     */
    public void filterCustomerTable(String search){
        customerTable.setItems(FXCollections.observableArrayList(DBDriver.broadCustomerSearch(search)));
    }
    
    /**
     * Opens new dialogue that allows the user to add a customer.
     * @throws IOException 
     */
    public void addCustomerBtnPressed() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/capstone/view/AddCustomer.fxml"));
        Capstone.showNewScene(root, "Add Customer");
        populateCustomerTable();
    }
    
    /**
     * When a customer is selected in the table, a new dialogue will open,
     * initializing a value for the selected customer to pass data to the new 
     * window.
     * @throws IOException 
     */
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
    
    /**
     * Allows the previous window to pass data regarding the selected event
     * to this window.
     * @param e The event that has been selected.
     */
    public void initEvent(BusinessEvent e){
        this.event = e;
    }
    
    /**
     * Closes the window.
     */
    public void cancelBtnPressed(){
        Stage s = (Stage)searchField.getScene().getWindow();
        s.close();
    }
    
}
