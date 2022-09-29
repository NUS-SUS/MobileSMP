package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfluencerContent {
    public InfluencerContent(String unitNumber, List<String> language, Integer postalCode, String blockNumber, Integer contactNumber, String userType, List<String> tags, String category, String nationality, String fullName, String streetName, String socialMedia, String birthdate, String email) {
        this.unitNumber = unitNumber;
        this.language = language;
        this.postalCode = postalCode;
        this.blockNumber = blockNumber;
        this.contactNumber = contactNumber;
        this.userType = userType;
        this.tags = tags;
        this.category = category;
        this.nationality = nationality;
        this.fullName = fullName;
        this.streetName = streetName;
        this.socialMedia = socialMedia;
        this.birthdate = birthdate;
        this.email = email;
    }

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


    public static String i_unitNumber;
    public static List<String> i_language = null;
    public static Integer i_postalCode;
    public static String i_blockNumber;
    public static Integer i_contactNumber;
    public static String i_userType;
    public static List<String> i_tags = null;
    public static String i_category;
    public static String i_nationality;
    public static String i_fullName;
    public static String i_streetName;
    public static String i_socialMedia;
    public static String i_birthdate;
    public static String i_email;

    public InfluencerContent(){

    }

    public InfluencerContent(String email, int contactNum, String blockNum, String streetName,
                             String unitNum, int postalCode){
        this.i_email = email;
        this.i_contactNumber = contactNum;
        this.i_blockNumber = blockNum;
        this.i_streetName = streetName;
        this.i_unitNumber = unitNum;
        this.i_postalCode = postalCode;

    }
    public InfluencerContent(String birthdate, String category, List<String> tags, String nationality,
                             List<String> language, String socialMedia, String fullName){
        this.i_birthdate = birthdate;
        this.i_category = category;
        this.i_tags = tags;
        this.i_nationality = nationality;
        this.i_language = language;
        this.i_socialMedia = socialMedia;
        this.i_fullName = fullName;

    }

    @Override
    public String toString() {
        return "InfluencerContent{" +
                "unitNumber='" + i_unitNumber + '\'' +
                ", language=" + i_language +
                ", postalCode=" + i_postalCode +
                ", blockNumber='" + i_blockNumber + '\'' +
                ", contactNumber=" + i_contactNumber +
                ", userType='" + i_userType + '\'' +
                ", tags=" + i_tags +
                ", category='" + i_category + '\'' +
                ", nationality='" + i_nationality + '\'' +
                ", fullName='" + i_fullName + '\'' +
                ", streetName='" + i_streetName + '\'' +
                ", socialMedia='" + i_socialMedia + '\'' +
                ", birthdate='" + i_birthdate + '\'' +
                ", email='" + i_email + '\'' +
                '}';
    }

}
