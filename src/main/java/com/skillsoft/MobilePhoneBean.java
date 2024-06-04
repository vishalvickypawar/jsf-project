package com.skillsoft;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("PhoneBean")
@RequestScoped
public class MobilePhoneBean implements Serializable{

    private String repoName = "Qenel Repo";
    Connection connection;

    String brandName;
    String productName;
    String os;
    int storageCapacity;
    int ram;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public static Connection getDataSourceConnection() throws SQLException {

        MysqlDataSource getDS = null;

        try {
            getDS = new MysqlDataSource();

            getDS.setURL("jdbc:mysql://localhost:3306/Products");
            getDS.setUser("root");
            getDS.setPassword("vicky");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return getDS.getConnection();
    }

    public ArrayList getPhones() {

        ArrayList<MobilePhone> listOfPhones = new ArrayList();

        try {
            connection = getDataSourceConnection();
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM phones");

            while (rs.next()) {
                MobilePhone phone = new MobilePhone();
                phone.setId(rs.getInt("id"));
                phone.setBrandName(rs.getString("brandName"));
                phone.setProductName(rs.getString("productName"));
                phone.setOs(rs.getString("os"));
                phone.setStorageCapacity(rs.getInt("storageCapacity"));
                phone.setRam(rs.getInt("ram"));
                listOfPhones.add(phone);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return listOfPhones;
    }

    public String save() {
        int result = 0;
        try {
            connection = getDataSourceConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO phones(brandName, productName, os, storageCapacity, ram) " +
                            "VALUES(?,?,?,?,?)");

            stmt.setString(1, brandName);
            stmt.setString(2, productName);
            stmt.setString(3, os);
            stmt.setInt(4, storageCapacity);
            stmt.setInt(5, ram);
            result = stmt.executeUpdate();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        if (result != 0)
            return "view.xhtml?faces-redirect=true";
        else
            return "create.xhtml?faces-redirect=true";
    }
}
