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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Dylan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML TableView eventTable;
    @FXML TableColumn idColumn;
    @FXML TableColumn titleColumn;
    @FXML TableColumn dateColumn;
    @FXML TableColumn capacityColumn;
    @FXML Menu adminMenu;
    @FXML MenuItem addEventItem, editeEventItem, generateMapItem,
            addTicketItem, createUserItem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateEventTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setSortType(TableColumn.SortType.ASCENDING);
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("ticketCount"));
        eventTable.getSortOrder().add(dateColumn);
    }
    
    public void populateEventTable(){
        eventTable.setItems(FXCollections.observableArrayList(DBDriver.getAllEvents()));
        eventTable.getSortOrder().add(dateColumn);
    }
    
    @FXML
    public void addEvent() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/capstone/view/AddEvent.fxml"));
        Capstone.showNewScene(root, "Add Event");
        populateEventTable();
    }
    
    @FXML
    public void editEvent() throws IOException{
        if(eventTable.getSelectionModel().getSelectedItem() == null){
            Capstone.makeAlert("Please select an event to edit.");
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/EditEvent.fxml"));
            Parent root = loader.load();
            loader.<EditEventController>getController().initEvent((BusinessEvent)eventTable.getSelectionModel().getSelectedItem());
            Capstone.showNewScene(root, "Edit Event");
            populateEventTable();
        }
    }
    
    @FXML
    public void generateMap() throws IOException{
        if(eventTable.getSelectionModel().getSelectedItem() == null){
            Capstone.makeAlert("Please select an event to generate a map.");
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/GenerateMap.fxml"));
            Parent root = loader.load();
            loader.<GenerateMapController>getController().initEvent((BusinessEvent)eventTable.getSelectionModel().getSelectedItem());
            Capstone.showNewScene(root, "Generate Map");
        }
    }
    
    @FXML
    public void addTickets() throws IOException{
        if(eventTable.getSelectionModel().getSelectedItem() == null){
            Capstone.makeAlert("Please select an event to add ticket sales to.");
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/capstone/view/AddTickets.fxml"));
            Parent root = loader.load();
            loader.<AddTicketsController>getController().initEvent((BusinessEvent)eventTable.getSelectionModel().getSelectedItem());
            Capstone.showNewScene(root, "Add Tickets");
            populateEventTable();
        }
    }
    
    @FXML
    public void createUser() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/capstone/view/CreateUser.fxml"));
        Capstone.showNewScene(root, "Create User");
    }
    
    public void initUserType(String type){
        if(type.equals("admin")){
            adminMenu.setVisible(true);
        }
    }
    
    
    
}
