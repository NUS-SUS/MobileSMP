package com.example.mobilesmp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobilesmp.databinding.FragmentProfileViewBinding;
import com.example.mobilesmp.ui.profile.ProfileEditFragment;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewFragment extends Fragment {

    FragmentProfileViewBinding binding;
    TextView textView;
    View view;
    APIInterface apiInterface;
    String FILENAME = "Draft";
    CurrentUser currentUser = new CurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ProfileViewFrag","OnCreateView");
        binding = FragmentProfileViewBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_profile_view, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ProfileViewFrag","ThreadID in frag -> " +Thread.currentThread().getId());
        Log.d("ProfileViewFrag","OnViewCraeted");

        Log.d("ProfileViewFrag","Profile -> "+currentUser.getType());
        if (currentUser.getType().equals("Influencer")){
            textView.setText("Influencer");
        }else if (currentUser.getType().equals("Company")){
            textView.setText("Company");
        }else{
            NavHostFragment.findNavController(ProfileViewFragment.this)
                    .navigate(R.id.action_profileViewFragment_to_profileEditFragment);
        }
        textView = (TextView) view.findViewById(R.id.textView);



        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProfileViewFragment.this)
                        .navigate(R.id.action_profileViewFragment_to_profileEditFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}