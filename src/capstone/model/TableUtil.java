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
