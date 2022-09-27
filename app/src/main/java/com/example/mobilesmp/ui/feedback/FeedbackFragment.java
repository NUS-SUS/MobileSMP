package com.example.mobilesmp.ui.feedback;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentFeedbackBinding;
import com.example.retrofit.smp.FeedbackContent;
import com.example.retrofit.smp.FeedbackResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackFragment extends Fragment {

    FragmentFeedbackBinding binding;
    EditText editText;
    View view;
    APIInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FeedbackFramgent","onCreateView");
        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FeedbackFramgent","onViewCreated");

        editText = (EditText) view.findViewById(R.id.plain_text_input);


        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackResource feedbackResource = new FeedbackResource();
                FeedbackContent feedback = new FeedbackContent(editText.getText().toString(),"", feedbackResource.count+1+"","",
                                                        1,"",1 );
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Log.d("FeedbackFramgent",editText.getText().toString()+" --> EditText");
                Log.d("FeedbackFramgent",feedbackResource.count+" --> Feedback Size");
                Call<FeedbackContent> call1 = apiInterface.submitFeedback(feedback);
                call1.enqueue(new Callback<FeedbackContent>() {
                    @Override
                    public void onResponse(Call<FeedbackContent> call, Response<FeedbackContent> response) {
                        Log.d("FeedbackFramgent","Submitted Feedback");
                        feedbackResource.count++;
                    }

                    @Override
                    public void onFailure(Call<FeedbackContent> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}