/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Volunteer.bean;

import java.util.*;


/**
 *
 * @author Fatin Amalin
 */
public class ActivitiesDesc implements java.io.Serializable{
    private int ActID;
    private String ActLocation;
    private Date ActDate;

    public ActivitiesDesc() {
    }

    public ActivitiesDesc(int ActID, String ActLocation, Date ActDate) {
        this.ActID = ActID;
        this.ActLocation = ActLocation;
        this.ActDate = ActDate;
    }

    public int getActID() {
        return ActID;
    }

    public String getActLocation() {
        return ActLocation;
    }

    public Date getActDate() {
        return ActDate;
    }

    public void setActID(int ActID) {
        this.ActID = ActID;
    }

    public void setActLocation(String ActLocation) {
        this.ActLocation = ActLocation;
    }

    public void setActDate(Date ActDate) {
        this.ActDate = ActDate;
    }
    
    
    
}
