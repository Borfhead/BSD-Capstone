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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    @FXML MenuItem addEventItem, editeEventItem, generateMapItem, addTicketItem;
    
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
    public void editEvent(){

    }
    
    @FXML
    public void generateMap(){

    }
    
    @FXML
    public void addTickets(){

    }
    
    
    
}
