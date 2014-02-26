package com.rj.di_social.model;

/**
 * User: rjain
 * Date: 01/02/14
 * Time: 12:13 PM
 */
public class FiInfo {
    private String fiId;
    private String name;
    private String state;
    private String phoneNumber;
    private String fbId;
    private long lastFbUpdateTime;

    public String getFiId() {
        return fiId;
    }

    public void setFiId(String fiId) {
        this.fiId = fiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public long getLastFbUpdateTime() {
        return lastFbUpdateTime;
    }

    public void setLastFbUpdateTime(long lastFbUpdateTime) {
        this.lastFbUpdateTime = lastFbUpdateTime;
    }

    @Override
    public String toString() {
        return "FiInfo{" +
                "fiId='" + fiId + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fbId='" + fbId + '\'' +
                ", lastFbUpdateTime=" + lastFbUpdateTime +
                '}';
    }
}