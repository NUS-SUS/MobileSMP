package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

public class PaymentContent {
    @SerializedName("TRANSACTION_ID")
    public String transactionId;
    @SerializedName("PAYMENT_TYPE")
    public String paymentType;
    @SerializedName("CAMPAIGN_FUNDS_PURCHASED")
    public Integer campaignFundsPurchased;
    @SerializedName("COMPANIES_ID")
    public String companiesId;
    @SerializedName("PAYMENT_STATUS")
    public String paymentStatus;
    @SerializedName("AMOUNT")
    public Integer amount;
    @SerializedName("PAYMENTS_ID")
    public String paymentsId;

}
