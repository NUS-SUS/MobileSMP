package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

public class CompanyResource {
    @SerializedName("UNIT_NUMBER")
    public static String unitNumber;
    @SerializedName("COMPANY_NAME")
    public static String companyName;
    @SerializedName("VERIFIED")
    public static Boolean verified;
    @SerializedName("CAMPAIGN_FUNDS")
    public static Integer campaignFunds;
    @SerializedName("POSTAL_CODE")
    public static String postalCode;
    @SerializedName("NATIONALITY")
    public static String nationality;
    @SerializedName("BLOCK_NUMBER")
    public static String blockNumber;
    @SerializedName("CONTACT_NUMBER")
    public static String contactNumber;
    @SerializedName("STREET_NAME")
    public static String streetName;
    @SerializedName("USER_TYPE")
    public static String userType;
    @SerializedName("EMAIL")
    public static String email;
}
