/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dylan
 */
public class BusinessEvent {
    private int eventId;
    private String title;
    private LocalDate date;
    private int maxCapacity;
    private StringProperty ticketCount;
    
    
    public BusinessEvent(){
        eventId = -1;
        title = "";
        date = null;
        maxCapacity = -1;
    }
    
    public BusinessEvent(int id, String title, LocalDate date, int maxCap){
        this.eventId = id;
        this.title = title;
        this.date = date;
        this.maxCapacity = maxCap;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public String getTicketCount(){
        ticketCount = new SimpleStringProperty(DBDriver.getTicketSalesForEvent(eventId) +"/"+ this.maxCapacity);
        return ticketCount.get();
    }
    
    
}
