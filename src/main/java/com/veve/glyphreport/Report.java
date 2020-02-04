package com.veve.glyphreport;


import java.sql.Timestamp;

public class Report {

    int id;

    Timestamp timestamp;

    String appVersion;

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    String deviceModel;

    String sdk;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Report(int id, String appVersion, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
        this.appVersion = appVersion;
    }

    public Report(int id,  String appVersion, Timestamp timestamp, String deviceModel, String sdk) {
        this.id = id;
        this.timestamp = timestamp;
        this.appVersion = appVersion;
        this.deviceModel = deviceModel;
        this.sdk = sdk;
    }
}
