/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.model;

/**
 * Class created for presenting data within a TableView. The data provided is
 * based on aggregated/joined queries from the database that don't belong to any
 * one class. Properties are customer name, the name of the table, and the
 * amount of tickets the customer has purchased at that table.
 * @author Dylan
 */
public class TableUtil {
    private String custName;
    private String tableChar;
    private int ticketNum;
    
    public TableUtil(String custName, String tableChar, int ticketNum){
        this.custName = custName;
        this.tableChar = tableChar;
        this.ticketNum = ticketNum;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getTableChar() {
        return tableChar;
    }

    public void setTableChar(String tableChar) {
        this.tableChar = tableChar;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
    
    
}
