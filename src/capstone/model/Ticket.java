/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.model;

/**
 *
 * @author Dylan
 */
public class Ticket {
    private int ticketId;
    private int customerId;
    private int eventId;
    
    public Ticket(){
        ticketId = -1;
        customerId = -1;
        eventId = -1;
    }
    
    public Ticket(int ticketId, int customerId, int eventId){
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.eventId = eventId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    
    
}
