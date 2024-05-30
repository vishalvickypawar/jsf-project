package com.skillsoft;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("HelloWorld")
@RequestScoped
public class HelloBean implements Serializable{

    private static final long serialVersionUID = -6913972022251814607L;

    private String message = "Hello World!!";

    public String getMessage() {
        System.out.println(message);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}