package com.rj.di_social.common;

import com.rj.di_social.model.FiInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * User: rjain
 * Date: 01/02/14
 * Time: 12:22 PM
 */
public class Helper {

    public void populateDBForFiInfo(List<FiInfo> fiInfoList) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/di_social", "root", "//");

            for (FiInfo fiInfo : fiInfoList) {
                String sql = "INSERT INTO FiInfo (FiId, Name, State, PhoneNumber)" +
                        "VALUES (?, ?, ?, ?)";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, fiInfo.getFiId());
                preparedStatement.setString(2, fiInfo.getName());
                preparedStatement.setString(3, fiInfo.getState());
                preparedStatement.setString(4, fiInfo.getPhoneNumber());
                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Map<String,FiInfo> loadFiInfo() {
        BufferedReader br = null;

        HashMap<String, FiInfo> fiInfoMap= new HashMap<String, FiInfo>();
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/rjain/Desktop/FiInfo.csv"));

            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine= sCurrentLine.substring(0, sCurrentLine.length()-1);
                String[] values= sCurrentLine.split(",");
                String fiId= values[0].trim();
                String fiName= values[1].trim();
                String state= values[2].trim();

                FiInfo fiInfo= new FiInfo();
                fiInfo.setFiId(fiId);
                fiInfo.setName(fiName);
                fiInfo.setState(state);

                fiInfoMap.put(fiId, fiInfo);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fiInfoMap;
    }

    public Map<String,String> loadFiPhoneNumbers() {
        HashMap<String, String> phoneNumberMap= new HashMap<String, String>();

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/rjain/Desktop/fiPhoneNumber.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] values= sCurrentLine.split(";");
                String fiId= values[0];
                if(fiId.length()==5)
                    fiId=fiId.substring(1);
                String fiPhoneNumber= values[1];
                if(fiPhoneNumber.length()>45)
                    fiPhoneNumber=fiPhoneNumber.substring(0,44);
                phoneNumberMap.put(fiId, fiPhoneNumber);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return phoneNumberMap;
    }

    public List<FiInfo> getFiInfoFromDB() {
        List<FiInfo> fiInfoList= new ArrayList<FiInfo>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/di_social", "root", "//");

            Statement statement = conn.createStatement();
            // Result set get the result of the SQL query
            ResultSet resultSet = statement.executeQuery("select * from di_social.FiInfo");

            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
                String fiId = resultSet.getString(1);
                String name = resultSet.getString(2);
                String state = resultSet.getString(3);
                String phoneNumber = resultSet.getString(4);

                FiInfo fiInfo= new FiInfo();
                fiInfo.setFiId(fiId);fiInfo.setState(state);
                fiInfo.setName(name);fiInfo.setPhoneNumber(phoneNumber);

                fiInfoList.add(fiInfo);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fiInfoList;
    }

    public Map<String,String> loadFbId() {
        HashMap<String, String> fbIdMap= new HashMap<String, String>();

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/rjain/projects/work/DI_Social/NEW_Fi_FB.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] values= sCurrentLine.split(":");
                String fiId= values[0];
                String fbId= values[1];
                fbIdMap.put(fiId, fbId);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fbIdMap;
    }

    public void populateDBForFbId(Map<String,String> fbIdMap) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/di_social", "root", "//");

            Iterator it = fbIdMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();

                String fiId= (String)pairs.getKey();
                String fbId= (String)pairs.getValue();

                String sql = "UPDATE FiInfo SET FbId = ? where FiId = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, fbId);
                preparedStatement.setString(2, fiId);
                preparedStatement.executeUpdate();

                it.remove(); // avoids a ConcurrentModificationException
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
