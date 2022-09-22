package com.example.mobilesmp.ui.discover;

import androidx.annotation.NonNull;
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilesmp.R;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;
import com.example.mobilesmp.ui.discover.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<CampaignContent.CampaignContentItem> mValues;

    TextView responseText ;

    public MyItemRecyclerViewAdapter(List<CampaignContent.CampaignContentItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        //return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mItem = mValues.get(position);
        holder.position = position;
        holder.campaignName.setText(mValues.get(position).c_campaignName);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView campaignName;
        public CampaignContent.CampaignContentItem mItem;
        View rootView;
        int position;
/*
        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemCCampaignName;
            mContentView = binding.itemCCampaignName;
        }

 */

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            rootView = itemView;
            campaignName = itemView.findViewById(R.id.item_c_campaignName);

            itemView.findViewById(R.id.item_button_view_campaign).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.campaignViewFragment);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + campaignName.getText() + "'";
        }


    }

}