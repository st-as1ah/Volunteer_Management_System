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
public class Activities implements java.io.Serializable{
    private int actID;
    private String actName;
    private String actDesc;

    public Activities(int actID,String actName, String actDesc) {
        this.actID=actID;
        this.actName = actName;
        this.actDesc = actDesc;
    }

    public Activities() {
    }

    public int getActID() {
        return actID;
    }

    public String getActName() {
        return actName;
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActID(int actID) {
        this.actID = actID;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }
    
}
