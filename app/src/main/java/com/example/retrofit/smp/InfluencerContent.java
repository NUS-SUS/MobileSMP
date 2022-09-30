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

    @Override
    public String toString() {
        return "Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_fullName + "\n\n" +
                "Nationality\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_nationality + "\n\n" +
                "Category\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_category + "\n\n" +
                "Tags\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_tags + "\n\n" +
                "Email\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_email + "\n\n" +
                "Account Type\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_userType + "\n\n" +
                "Birth Date\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_birthdate + "\n\n" +
                "Language\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_language + "\n\n" +
                "Social Media\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_socialMedia + "\n\n" +
                "Contact Number\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_contactNumber + "\n\n" +
                "Block Number\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_blockNumber + "\n\n" +
                "Street Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_streetName + "\n\n" +
                "Unit Number\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t'" + i_unitNumber + "\n\n" +
                "Postal Code\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + i_postalCode;
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
}
