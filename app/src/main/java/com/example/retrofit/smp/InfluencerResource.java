package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfluencerResource {
    @SerializedName("UNIT_NUMBER")
    public static String unitNumber;
    @SerializedName("LANGUAGE")
    public static List<String> language = null;
    @SerializedName("POSTAL_CODE")
    public static Integer postalCode;
    @SerializedName("BLOCK_NUMBER")
    public static String blockNumber;
    @SerializedName("CONTACT_NUMBER")
    public static Integer contactNumber;
    @SerializedName("USER_TYPE")
    public static String userType;
    @SerializedName("TAGS")
    public static List<String> tags = null;
    @SerializedName("CATEGORY")
    public static String category;
    @SerializedName("NATIONALITY")
    public static String nationality;
    @SerializedName("FULL_NAME")
    public static String fullName;
    @SerializedName("STREET_NAME")
    public static String streetName;
    @SerializedName("SOCIAL_MEDIA")
    public static String socialMedia;
    @SerializedName("BIRTHDATE")
    public static String birthdate;
    @SerializedName("EMAIL")
    public static String email;
}
