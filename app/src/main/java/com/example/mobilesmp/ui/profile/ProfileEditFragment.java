package com.example.mobilesmp.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentProfileEditBinding;
import com.example.retrofit.smp.CurrentUser;

public class ProfileEditFragment extends Fragment {

    FragmentProfileEditBinding binding;
    CurrentUser currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileEditBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser = new CurrentUser();
                Log.d("CurrentUser",currentUser.getType());
                if (currentUser.getType().equals("Company")) {
                    NavHostFragment.findNavController(ProfileEditFragment.this)
                            .navigate(R.id.action_profileEditFragment_to_profileCompanyFragment);
                }
                else if (currentUser.getType().equals("Influencer")){
                    NavHostFragment.findNavController(ProfileEditFragment.this)
                            .navigate(R.id.action_profileEditFragment_to_profileInfluencerFragment);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}