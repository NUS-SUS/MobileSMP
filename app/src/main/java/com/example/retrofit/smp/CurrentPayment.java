package com.example.retrofit.smp;

public class CurrentPayment {
    private static String clientRefId;
    private static String amount;
    private static String userEmail;

    public static String getClientRefId() {
        return clientRefId;
    }

    public static void setClientRefId(String clientRefId) {
        CurrentPayment.clientRefId = clientRefId;
    }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        CurrentPayment.amount = amount;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        CurrentPayment.userEmail = userEmail;
    }
}
