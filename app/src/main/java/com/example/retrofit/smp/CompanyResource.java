package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyResource {
    @Override
    public String toString() {
        return "CompanyResource{" +
                "unitNumber='" + unitNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", verified=" + verified +
                ", campaignFunds=" + campaignFunds +
                ", postalCode='" + postalCode + '\'' +
                ", nationality='" + nationality + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @SerializedName("UNIT_NUMBER")
    public String unitNumber;
    @SerializedName("COMPANY_NAME")
    public String companyName;
    @SerializedName("VERIFIED")
    public Boolean verified;
    @SerializedName("CAMPAIGN_FUNDS")
    public Integer campaignFunds;
    @SerializedName("POSTAL_CODE")
    public String postalCode;
    @SerializedName("NATIONALITY")
    public String nationality;
    @SerializedName("BLOCK_NUMBER")
    public String blockNumber;
    @SerializedName("CONTACT_NUMBER")
    public String contactNumber;
    @SerializedName("STREET_NAME")
    public String streetName;
    @SerializedName("USER_TYPE")
    public String userType;
    @SerializedName("EMAIL")
    public String email;

    public static String c_unitNumber;
    public static String c_companyName;
    public static Boolean c_verified;
    public static Integer c_campaignFunds;
    public static String c_postalCode;
    public static String c_nationality;
    public static String c_blockNumber;
    public static String c_contactNumber;
    public static String c_streetName;
    public static String c_userType = "Company";
    public static String c_email;

    public CompanyResource() {}


    public CompanyResource(String email, String contactNum, String blockNum, String streetName,
                             String unitNum, String postalCode){
        this.c_email = email;
        this.c_contactNumber = contactNum;
        this.c_blockNumber = blockNum;
        this.c_streetName = streetName;
        this.c_unitNumber = unitNum;
        this.c_postalCode = postalCode;

    }

    public CompanyResource(String companyName, String nationality){
        this.c_companyName = companyName;
        this.c_nationality = nationality;
        this.c_campaignFunds = 0;
    }

    public String[] getStringArray(){
        String[] s = new String[10];
        s[0] = c_companyName;
        s[1] = c_nationality;
        s[2] = String.valueOf(c_campaignFunds);
        s[3] = c_email;
        s[4] = c_userType;
        s[5] = c_contactNumber;
        s[6] = c_blockNumber;
        s[7] = c_streetName;
        s[8] = c_unitNumber;
        s[9] = c_postalCode;
        return s;
    }

    public void setBodyValues() {
        unitNumber = c_unitNumber;
        companyName = c_companyName;
        verified = c_verified;
        campaignFunds = c_campaignFunds;
        postalCode = c_postalCode;
        nationality = c_nationality;
        blockNumber = c_blockNumber;
        contactNumber = c_contactNumber;
        streetName = c_streetName;
        userType = c_userType;
        email = c_email;
    }

    public void setValues() {
        c_unitNumber = unitNumber;
        c_companyName = companyName;
        c_verified = verified;
        c_campaignFunds = campaignFunds;
        c_postalCode = postalCode;
        c_nationality = nationality;
        c_blockNumber = blockNumber;
        c_contactNumber = contactNumber;
        c_streetName = streetName;
        c_userType = userType;
        c_email = email;
    }
}
