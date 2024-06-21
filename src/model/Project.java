/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Love Byiringiro
 */
public class Project {
    private String projectID;

    public String getProjectID() {
        return projectID;
    }
    @Override
public String toString() {
    return name + " - " + description; // Customize this to display project information
}

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Project(String projectID, String name, String description, String location, Date startDate, Date endDate) {
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    


    public Project() {
    }
    private String name;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    
}
