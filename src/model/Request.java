/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Love Byiringiro
 */
public class Request {
    private Integer requestID;
    private String userID;
    private String projectID;
    private String  status;

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request(Integer requestID, String userID, String projectID, String status) {
        this.requestID = requestID;
        this.userID = userID;
        this.projectID = projectID;
        this.status = status;
    }

    public Request() {
    }
    
}
