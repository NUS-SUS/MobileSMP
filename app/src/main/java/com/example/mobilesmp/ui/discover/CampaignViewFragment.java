package com.example.mobilesmp.ui.discover;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentCampaignViewBinding;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CampaignViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignViewFragment extends Fragment {

    FragmentCampaignViewBinding binding;
    TextView campaignId;
    TextView campaignName ;
    TextView campaignDesc;
    TextView campaignCategory;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CampaignViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CampaignViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CampaignViewFragment newInstance(String param1, String param2) {
        CampaignViewFragment fragment = new CampaignViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCampaignViewBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_campaign_view, container, false);
        campaignId = (TextView) view.findViewById(R.id.textView11);
        campaignName = (TextView) view.findViewById(R.id.textView12);
        campaignDesc = (TextView) view.findViewById(R.id.textView13);
        campaignCategory = (TextView) view.findViewById(R.id.textView14);

        // Inflate the layout for this fragment
        return view;
    }

    public void setDetails(CampaignContent.CampaignContentItem campaignContentItem){
        campaignId.setText(campaignContentItem.c_campaignsId);
        campaignName.setText(campaignContentItem.c_campaignName);
        campaignDesc.setText(campaignContentItem.c_description);
        campaignCategory.setText(campaignContentItem.c_category);

    }
}