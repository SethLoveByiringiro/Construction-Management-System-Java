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
public class User {
    private String userID; 
    private String nationalID;
    private String fullNames;
    private String phoneNumber;
    private String position;
    private String password;
    private String confirmPassword;
    private String role;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String userID, String nationalID, String fullNames, String phoneNumber, String position, String password, String confirmPassword, String role) {
        this.userID = userID;
        this.nationalID = nationalID;
        this.fullNames = fullNames;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public User() {
    }

   
        
    }
    
