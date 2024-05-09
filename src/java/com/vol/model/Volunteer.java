/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vol.model;

/**
 *
 * @author Asiah
 */
public class Volunteer implements java.io.Serializable {
    
    private String volName;
    private String volIC;
    private String phoneNo;
    private String email;
    private String password;

    public Volunteer() {
    }

    public Volunteer(String volName, String volIC, String phoneNo, String email, String password) {
        this.volName = volName;
        this.volIC = volIC;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    public String getVolIC() {
        return volIC;
    }

    public void setVolIC(String volIC) {
        this.volIC = volIC;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
