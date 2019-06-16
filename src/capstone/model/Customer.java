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
public class Customer {
   private String firstName;
   private String lastName;
   private int custId;
   private String email;
   
   public Customer(){
       firstName = "";
       lastName = "";
       custId = -1;
       email = "";
   }
   
   public Customer(int id, String fname, String lname, String email){
       this.custId = id;
       this.firstName = fname;
       this.lastName = lname;
       this.email = email;
   }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
   
   
}
