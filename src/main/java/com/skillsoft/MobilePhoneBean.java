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

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
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

}
