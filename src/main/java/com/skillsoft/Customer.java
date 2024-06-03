package com.skillsoft;

import java.io.Serializable;
import java.util.Date;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;

@Named("CustomerBean")
@SessionScoped
public class Customer implements Serializable {

    private String name;
    private String gender;
    private Date dob;
    private String address;
    private String emailAddress;
    private String mobileNumber;
    private String maritalStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void validateEmail(FacesContext context, UIComponent toValidate, Object value)
            throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Email Address is invalid");
            throw new ValidatorException(message);
        }
    }

    public String storeEmployeeInfo() {

        FacesMessage message = null;
        String outcome = null;

        try {
            message = new FacesMessage("Registration information has been stored.");
            outcome = "regsuccess";
        }
        catch (Exception e) {
            message = new FacesMessage("Registration information could NOT be stored.");
            outcome = "customer";
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return outcome;
    }
}