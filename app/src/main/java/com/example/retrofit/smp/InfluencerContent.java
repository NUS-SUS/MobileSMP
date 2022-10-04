package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfluencerContent {

    @Override
    public String toString() {
        return "InfluencerContent{" +
                "unitNumber='" + unitNumber + '\'' +
                ", language=" + language +
                ", postalCode=" + postalCode +
                ", blockNumber='" + blockNumber + '\'' +
                ", contactNumber=" + contactNumber +
                ", userType='" + userType + '\'' +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", nationality='" + nationality + '\'' +
                ", fullName='" + fullName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", socialMedia='" + socialMedia + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                '}';
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
    public static String i_userType = "Influencer";
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


    public String[] getStringArray(){
        String[] s = new String[14];
        s[0]=i_fullName;
        s[1] = i_nationality;
        s[2] =  i_category;
        s[3] = i_tags.toString();
        s[4] = i_email;
        s[5] = i_userType;
        s[6] = i_birthdate;
        s[7] = i_language.toString();
        s[8] = i_socialMedia;
        s[9] = i_contactNumber.toString();
        s[10] = i_blockNumber;
        s[11] = i_streetName;
        s[12] = i_unitNumber;
        s[13] = i_postalCode.toString();
        return s;
    }

    public void setValues() {
        i_unitNumber = unitNumber;
        i_language = language;
        i_postalCode = postalCode;
        i_blockNumber = blockNumber;
        i_contactNumber = contactNumber;
        i_userType = userType;
        i_tags = tags;
        i_category = category;
        i_nationality = nationality;
        i_fullName = fullName;
        i_streetName = streetName;
        i_socialMedia = socialMedia;
        i_birthdate = birthdate;
        i_email = email;
    }

    public void setBodyValues() {
        unitNumber = i_unitNumber;
        language = i_language;
        postalCode = i_postalCode;
        blockNumber = i_blockNumber;
        contactNumber = i_contactNumber;
        userType = i_userType;
        tags = i_tags;
        category = i_category;
        nationality = i_nationality;
        fullName = i_fullName;
        streetName = i_streetName;
        socialMedia = i_socialMedia;
        birthdate = i_birthdate;
        email = i_email;
    }
}
