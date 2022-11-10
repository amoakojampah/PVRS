package com.kstu.perrys_project.PVRS.model;

/**
 * Created by femicodes on 1/8/2018.
 */

public class Drug {

    String drugName;
    String drugType;
    String fdaNumber;
    String imageUrl;
    String manufacturerName;
    String batchNumber;
    String manufacturingDate;
    String expiringDate;

    public Drug() {
    }

    public Drug(String drugName, String drugType, String fdaNumber, String imageUrl, String manufacturerName, String batchNumber, String manufacturingDate, String expiringDate) {
        this.drugName = drugName;
        this.drugType = drugType;
        this.fdaNumber = fdaNumber;
        this.imageUrl = imageUrl;
        this.manufacturerName = manufacturerName;
        this.batchNumber = batchNumber;
        this.manufacturingDate = manufacturingDate;
        this.expiringDate = expiringDate;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getFdaNumber() {
        return fdaNumber;
    }

    public void setFdaNumber(String fdaNumber) {
        this.fdaNumber = fdaNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }
}
