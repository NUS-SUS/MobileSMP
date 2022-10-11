package com.example.mobilesmp.ui.discover;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentCampaignViewBinding;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;
import com.example.mobilesmp.ui.profile.ProfileEditFragment;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.InfluencerContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CampaignViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignViewFragment extends Fragment {

    private static CampaignContent campaignContent;
    private static CampaignContent.CampaignContentItem campaignContentItem;
    FragmentCampaignViewBinding binding;
    public static TextView campaignId;
    public static TextView campaignName ;
    public static TextView campaignDesc;
    public static TextView campaignCategory;
    public static TextView campaignTags;
    public static TextView campaignVenue ;
    public static TextView campaignStart;
    public static TextView campaignEnd;


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
     * @return A new instance of fragment CampaignViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CampaignViewFragment newInstance(String param1) {

        CampaignViewFragment fragment = new CampaignViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        campaignContent = new CampaignContent();
        campaignContentItem = campaignContent.item_map.get(param1).getCampaignItem();


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

        campaignTags = (TextView) view.findViewById(R.id.textView15);
        campaignVenue = (TextView) view.findViewById(R.id.textView16);
        campaignStart = (TextView) view.findViewById(R.id.textView17);
        campaignEnd = (TextView) view.findViewById(R.id.textView18);

        setAllText();
        binding.buttonApply.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       Toast.makeText(getContext(),"Applied",Toast.LENGTH_SHORT).show();
                                                   }
                                               }
        );

        // Inflate the layout for this fragment
        return view;
    }

    private static void setAllText(){
        campaignId.setText(campaignContentItem.c_campaignsId);
        campaignName.setText(campaignContentItem.c_campaignName);
        campaignDesc.setText(campaignContentItem.c_description);
        campaignCategory.setText(campaignContentItem.c_category);

        String tags = "";
        for (String s : campaignContentItem.c_tags)
            tags += s + "\n";

        campaignTags.setText(tags);
        campaignVenue.setText(campaignContentItem.c_venue);
        campaignStart.setText(campaignContentItem.c_startDate);
        campaignEnd.setText(campaignContentItem.c_endDate);
    }



}