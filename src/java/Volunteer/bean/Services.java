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
public class Services implements java.io.Serializable{
    private int servicesID;
    private String servicesName;
    private String servicesDesc;

    public Services() {
    }

    public Services(int servicesID, String servicesName, String servicesDesc) {
        this.servicesID = servicesID;
        this.servicesName = servicesName;
        this.servicesDesc = servicesDesc;
    }

    public int getServicesID() {
        return servicesID;
    }

    public String getServicesName() {
        return servicesName;
    }

    public String getServicesDesc() {
        return servicesDesc;
    }

    public void setServicesID(int servicesID) {
        this.servicesID = servicesID;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public void setServicesDesc(String servicesDesc) {
        this.servicesDesc = servicesDesc;
    }
    
}
