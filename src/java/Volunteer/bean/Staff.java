/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Volunteer.bean;

/**
 *
 * @author Fatin Amalin
 */
public class Staff implements java.io.Serializable{
    private int staffID;
    private String staffIC;
    private String staffName;
    private String staffPhone;
    private String staffPass;

    public Staff(int staffID, String staffIC, String staffName, String staffPhone, String staffPass) {
        this.staffID = staffID;
        this.staffIC = staffIC;
        this.staffName = staffName;
        this.staffPhone = staffPhone;
        this.staffPass = staffPass;
    }

    public Staff() {
    }

    public int getStaffID() {
        return staffID;
    }

    public String getStaffIC() {
        return staffIC;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass;
    }
    
}
