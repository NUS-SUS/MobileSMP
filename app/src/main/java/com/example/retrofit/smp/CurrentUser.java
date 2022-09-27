package com.example.retrofit.smp;

public class CurrentUser {
    private static String userName;
    private static String userEmail;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userNameInput) {
        userName = userNameInput;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmailInput) {
        userEmail = userEmailInput;
    }
}
