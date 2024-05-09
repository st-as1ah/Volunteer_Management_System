/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author User
 */
public class Staff implements java.io.Serializable{
    private int staffID;
    private String staffIC;
    private String staffName;
    private String staffPhone;
    private String staffPass;
    
    public Staff(){}

    public Staff(int staffID, String staffIC, String staffName, String staffPhone, String staffPass) {
        this.staffID = staffID;
        this.staffIC = staffIC;
        this.staffName = staffName;
        this.staffPhone = staffPhone;
        this.staffPass = staffPass;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public String getStaffIC() {
        return staffIC;
    }

    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass;
    }
    
}
