package com.example.mobilesmp.ui.payment;

import android.util.Log;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.retrofit.smp.FeedbackContent;
import com.example.retrofit.smp.PaymentContent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentExample {

    APIInterface apiInterface;
    String payment_id = "1663440886336";
    String payment_id_fake = "1663440886335";
    Boolean haveResponse;

    public PaymentExample(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        haveResponse = false;

        Call<PaymentContent> call1 = apiInterface.doGetPaymentResources(payment_id_fake);
        call1.enqueue(new Callback<PaymentContent>() {
            @Override
            public void onResponse(Call<PaymentContent> call, Response<PaymentContent> response) {
                Log.d("PaymentExample",response.code()+" => response code");
                PaymentContent paymentContent = response.body();
                Log.d("PaymentExample","PaymentContent =>\n" + paymentContent.paymentsId +"\n" +
                         paymentContent.paymentType + "\n" + paymentContent.amount);
            }

            @Override
            public void onFailure(Call<PaymentContent> call, Throwable t) {
                Log.d("PaymentExample","Payment nv get through");
                call.cancel();
            }
        });



    }
}
