package com.example.mobilesmp.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobilesmp.R;

import java.util.List;

public class TwoHorizontalTextViewsAdapter extends ArrayAdapter<TwoStrings> {
    private int layoutResource;

    public TwoHorizontalTextViewsAdapter(Context context, int layoutResource, List<TwoStrings> twoStringsList) {
        super(context, layoutResource, twoStringsList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        TwoStrings twoStrings = getItem(position);

        if (twoStrings != null) {
            TextView leftTextView = (TextView) view.findViewById(R.id.leftTextView);
            TextView rightTextView = (TextView) view.findViewById(R.id.rightTextView);

            if (leftTextView != null) {
                leftTextView.setText(twoStrings.getLeft());
            }
            if (rightTextView != null) {
                rightTextView.setText(twoStrings.getRight());
            }
        }

        return view;
    }
}
