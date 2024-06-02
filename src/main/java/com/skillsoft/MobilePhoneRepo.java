package com.skillsoft;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

@Named("PhoneRepo")
@RequestScoped
public class MobilePhoneRepo implements Serializable{

    private String repoName = "Qenel Repo";
    private static Map<String, Double> inventory;
    private String selectedPhone = "";

    static {
        inventory = new HashMap<String, Double>();
        inventory.put("Zoflina Z09", 250.0);
        inventory.put("Zoflina Z11", 299.0);
        inventory.put("Diallonic DS101", 429.0);
        inventory.put("Diallonic DS102", 479.0);
        inventory.put("Diallonic DL014", 299.0);
        inventory.put("Sonical XV", 320.0);
        inventory.put("Sonical X9", 360.0);
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Map<String, Double> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Double> inventoryMap) {
        inventory = inventoryMap;
    }

    public static void addPhone(String phoneName, double phonePrice){
        inventory.put(phoneName, phonePrice);
    }

    public String getSelectedPhone() {
        return selectedPhone;
    }

    public void setSelectedPhone(String selectedPhone) {
        this.selectedPhone = selectedPhone;
    }

    public void selectedPhoneChanged(ValueChangeEvent event) {
        this.selectedPhone = event.getNewValue().toString();
    }

}
