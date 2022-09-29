package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfluencerContent {
    @SerializedName("UNIT_NUMBER")
    public String unitNumber;
    @SerializedName("LANGUAGE")
    public List<String> language = null;
    @SerializedName("POSTAL_CODE")
    public Integer postalCode;
    @SerializedName("BLOCK_NUMBER")
    public String blockNumber;
    @SerializedName("CONTACT_NUMBER")
    public Integer contactNumber;
    @SerializedName("USER_TYPE")
    public String userType;
    @SerializedName("TAGS")
    public List<String> tags = null;
    @SerializedName("CATEGORY")
    public String category;
    @SerializedName("NATIONALITY")
    public String nationality;
    @SerializedName("FULL_NAME")
    public String fullName;
    @SerializedName("STREET_NAME")
    public String streetName;
    @SerializedName("SOCIAL_MEDIA")
    public String socialMedia;
    @SerializedName("BIRTHDATE")
    public String birthdate;
    @SerializedName("EMAIL")
    public String email;

    public InfluencerContent(String email, int contactNum, String blockNum, String streetName,
                             String unitNum, int postalCode){
        this.email = email;
        this.contactNumber = contactNum;
        this.blockNumber = blockNum;
        this.streetName = streetName;
        this.unitNumber = unitNum;
        this.postalCode = postalCode;

    }
    public InfluencerContent(String birthdate, String category, List<String> tags, String nationality,
                             List<String> language, String socialMedia, String fullName){
        this.birthdate = birthdate;
        this.category = category;
        this.tags = tags;
        this.nationality = nationality;
        this.language = language;
        this.socialMedia = socialMedia;
        this.fullName = fullName;

    }
}
