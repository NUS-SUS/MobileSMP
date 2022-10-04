package com.example.mobilesmp.ui.profile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentProfileViewBinding;
import com.example.mobilesmp.ui.profile.TwoHorizontalTextViewsAdapter;
import com.example.mobilesmp.ui.profile.TwoStrings;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.InfluencerContent;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewFragment extends Fragment {

    TwoHorizontalTextViewsAdapter twoHorizontalTextViewsAdapter;
    static List<TwoStrings> twoStringsList = new ArrayList<>();
    FragmentProfileViewBinding binding;
    ListView listView;
    View view;
    final String[] inArray = {"Name","Nationlity","Category","Tags","Email","Account Type","Birth Date","Language","Social Media","Contact Number","Block Number","Street name","Unit Number","Postal Code"};
    final String[] coArray = {"Company Name","Nationlity","Campaign Funds","Email","Account Type","Contact Number","Block Number","Street name","Unit Number","Postal Code"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ProfileViewFrag","OnCreateView");
        binding = FragmentProfileViewBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_profile_view, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private BroadcastReceiver aLBReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            twoStringsList.clear();
            InfluencerContent influencerContent = new InfluencerContent();
            String[] s = influencerContent.getStringArray();
            for (int i = 0; i < 14;i++){
                twoStringsList.add(new TwoStrings(inArray[i],s[i]));
            }
            twoHorizontalTextViewsAdapter.notifyDataSetChanged();
        }
    };

    private BroadcastReceiver aLBReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            twoStringsList.clear();
            CompanyResource companyResource = new CompanyResource();
            String[] s = companyResource.getStringArray();
            for (int i = 0; i < 10;i++){
                twoStringsList.add(new TwoStrings(coArray[i],s[i]));
            }
            twoHorizontalTextViewsAdapter.notifyDataSetChanged();
        }
    };

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ProfileViewFrag","OnViewCreated");
        listView = (ListView) view.findViewById(R.id.listView);
        twoHorizontalTextViewsAdapter = new TwoHorizontalTextViewsAdapter(getContext(), R.layout.profile_list, twoStringsList);
        listView.setAdapter(twoHorizontalTextViewsAdapter);

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(aLBReceiver,
                new IntentFilter("InfluencerEvent"));

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(aLBReceiver2,
                new IntentFilter("CompanyEvent"));

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