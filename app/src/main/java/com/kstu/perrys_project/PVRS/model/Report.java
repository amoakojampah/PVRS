package com.kstu.perrys_project.PVRS.model;

/**
 * Created by femicodes on 1/12/2018.
 */

public class Report {
    private String drugName;
    private String drugType;
    private String pharmacyName_and_location;
    private String complainDetails;
    private String complaintName;
    private String complaintTel;
    private String dateOfComplain;
    private String fdaNumber;

    public Report(String drugName, String drugType, String fdaNumber, String pharmacyName_and_location, String complainDetails, String complaintName, String complaintTel, String dateOfComplain) {
        this.drugName = drugName;
        this.drugType = drugType;
        this.pharmacyName_and_location = pharmacyName_and_location;
        this.complainDetails = complainDetails;
        this.complaintName = complaintName;
        this.complaintTel = complaintTel;
        this.fdaNumber = fdaNumber;
        this.dateOfComplain = dateOfComplain;
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

    public String getPharmacyName_and_location() {
        return pharmacyName_and_location;
    }

    public void setPharmacyName_and_location(String pharmacyName_and_location) {
        this.pharmacyName_and_location = pharmacyName_and_location;
    }

    public String getComplainDetails() {
        return complainDetails;
    }

    public void setComplainDetails(String complainDetails) {
        this.complainDetails = complainDetails;
    }

    public String getComplaintName() {
        return complaintName;
    }

    public void setComplaintName(String complaintName) {
        this.complaintName = complaintName;
    }

    public String getComplaintTel() {
        return complaintTel;
    }

    public void setComplaintTel(String complaintTel) {
        this.complaintTel = complaintTel;
    }

    public String getDateOfComplain() {
        return dateOfComplain;
    }

    public void setDateOfComplain(String dateOfComplain) {
        this.dateOfComplain = dateOfComplain;
    }

    public String getFdaNumber() {
        return fdaNumber;
    }

    public void setFdaNumber(String fdaNumber) {
        this.fdaNumber = fdaNumber;
    }
}
