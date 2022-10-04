package com.example.mobilesmp.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentProfileEditBinding;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.InfluencerContent;

import java.util.ArrayList;
import java.util.List;

public class ProfileEditFragment extends Fragment {

    FragmentProfileEditBinding binding;
    CurrentUser currentUser = new CurrentUser();
    TextView email;
    final String[] ACCOUNTTYPES = {"","Influencer","Company"};
    EditText contactNumber,blockNumber,streetName,unitNumber,postalCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileEditBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = (TextView) view.findViewById(R.id.textView21);
        email.setText(currentUser.getUserEmail());

        Spinner spinner1 = (Spinner) view.findViewById(R.id.autoCompletetextView22);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ACCOUNTTYPES);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        if (currentUser.getType().equals("Influencer")){
            spinner1.setSelection(1);
            spinner1.setEnabled(false);
        }
        else if (currentUser.getType().equals("Company")){
            spinner1.setSelection(2);
            spinner1.setEnabled(false);
        }

        contactNumber = (EditText) view.findViewById(R.id.textView23);
        blockNumber = (EditText) view.findViewById(R.id.textView24);
        streetName = (EditText) view.findViewById(R.id.textView25);
        unitNumber = (EditText) view.findViewById(R.id.textView26);
        postalCode = (EditText) view.findViewById(R.id.textView27);


        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner1.getSelectedItem().toString().equals("Company")) {
                    try{
                        CompanyResource companyResource = new CompanyResource(email.getText().toString(),contactNumber.getText().toString(),blockNumber.getText().toString(),
                                streetName.getText().toString(),unitNumber.getText().toString(),postalCode.getText().toString());
                        if ((email.getText().toString().equals(""))||(contactNumber.getText().toString().equals(""))||(blockNumber.getText().toString().equals(""))||(streetName.getText().toString().equals(""))||
                                (unitNumber.getText().toString().equals(""))||(postalCode.getText().toString().equals("")))
                            throw new Exception("Error");

                        NavHostFragment.findNavController(ProfileEditFragment.this)
                                .navigate(R.id.action_profileEditFragment_to_profileCompanyFragment);
                    }catch(Exception e){
                        Toast.makeText(getContext(),"Some fields are empty",Toast.LENGTH_SHORT).show();
                    }

                }
                else if (spinner1.getSelectedItem().toString().equals("Influencer")){
                    try{
                        InfluencerContent influencerContent = new InfluencerContent(email.getText().toString(),Integer.parseInt(contactNumber.getText().toString()),blockNumber.getText().toString(),
                                streetName.getText().toString(),unitNumber.getText().toString(),Integer.parseInt(postalCode.getText().toString()));
                        if ((email.getText().toString().equals(""))||(contactNumber.getText().toString().equals(""))||(blockNumber.getText().toString().equals(""))||(streetName.getText().toString().equals(""))||
                                (unitNumber.getText().toString().equals(""))||(postalCode.getText().toString().equals("")))
                            throw new Exception("Error");

                        NavHostFragment.findNavController(ProfileEditFragment.this)
                                .navigate(R.id.action_profileEditFragment_to_profileInfluencerFragment);
                    }catch(Exception e){
                        Toast.makeText(getContext(),"Some fields are empty",Toast.LENGTH_SHORT).show();
                    }

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