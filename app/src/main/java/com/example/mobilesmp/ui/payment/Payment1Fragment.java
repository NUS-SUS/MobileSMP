package com.example.mobilesmp.ui.payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentFeedbackBinding;
import com.example.mobilesmp.databinding.FragmentPayment1Binding;
import com.example.mobilesmp.ui.profile.ProfileEditFragment;
import com.example.retrofit.smp.CurrentPayment;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.FeedbackContent;
import com.example.retrofit.smp.FeedbackResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Payment1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Payment1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String priceId;
    private String generateId;
    private String userEmail;
    FragmentPayment1Binding binding;
    View view;


    public Payment1Fragment() {
        // Required empty public constructor
    }

    /* */

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Payment1Fragment.
     */
    /*
    // TODO: Rename and change types and number of parameters
    public static Payment1Fragment newInstance(String param1, String param2) {
        Log.d("Payment1Fragment ","newInstance -> Start");
        Payment1Fragment fragment = new Payment1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Payment1Fragment ","onCreate -> Start");
        super.onCreate(savedInstanceState);
        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Payment1Fragment ","onCreateView -> Start");
        userEmail = CurrentUser.getUserEmail();
        Log.d("userEmail -> ",userEmail);
        //Binding Buttons
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPurchase10();
            }
        });
*//*        binding.button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPurchase50();
            }
        });
        binding.button100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPurchase100();
            }
        });*//*
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment1, container, false);
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Payment1Fragment", "onCreateView");
        binding = FragmentPayment1Binding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Payment1Fragment ", "onViewCreated -> Start");
        userEmail = CurrentUser.getUserEmail();
        Log.d("userEmail -> ", userEmail);
        //Binding Buttons
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPurchase10();
            }
        });
        binding.button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPurchase50();
            }
        });
        binding.button100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPurchase100();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onPurchase10() {
        priceId = "test_28o9CocbD2Mf3Sw5kl";
        purchaseStripe("10",priceId);
    }

    private void onPurchase50() {
        priceId = "test_14k8ykb7z2Mfdt69AC";
        purchaseStripe("50",priceId);
    }

    private void onPurchase100() {
        priceId = "test_4gwg0McbDcmP74I000";
        purchaseStripe("100",priceId);
    }

    private void purchaseStripe(String amount, String priceId) {
        Long generateLong = System.currentTimeMillis();
        generateId = generateLong.toString();
        //Create Payment Link

        Uri link = Uri.parse(
                "https://buy.stripe.com/"
                        + priceId
                        + "?prefilled_email="
                        + Uri.encode(userEmail)
                        + "&client_reference_id="
                        + generateId
        );
        Log.d("Payment Link Generate= ", link.toString());
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, link);
        startActivity(browserIntent);
        /*Intent payment2Intent = new Intent(getActivity(), Payment2Fragment.class);
        startActivity(payment2Intent);*/
       /* Payment2Fragment frag = new Payment2Fragment ();
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.payment2FragmentContainer,frag,"Payment2 Fragment");
        transaction.commit();*/
        CurrentPayment.setAmount(amount);
        CurrentPayment.setClientRefId(generateId);
        CurrentPayment.setUserEmail(userEmail);
        NavHostFragment.findNavController(Payment1Fragment.this)
                .navigate(R.id.action_payment1Fragment_to_payment2Fragment);
    }

}