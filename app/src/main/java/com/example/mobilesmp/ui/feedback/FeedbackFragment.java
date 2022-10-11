package com.example.mobilesmp.ui.feedback;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentFeedbackBinding;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackFragment extends Fragment {

    FragmentFeedbackBinding binding;
    EditText editText;
    View view;
    APIInterface apiInterface;
    String FILENAME = "Draft";
    CurrentUser currentUser = new CurrentUser();

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

        File dir = getActivity().getFilesDir();
        File file = new File(dir, FILENAME);
        if (file.exists()){
            Log.d("FeedbackFramgent","Draft retrieved");
            try {
                FileInputStream fis = getActivity().openFileInput(FILENAME);
                InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String line = reader.readLine();
                    while (line != null) {
                        stringBuilder.append(line).append('\n');
                        line = reader.readLine();
                    }
                    editText.setText(stringBuilder);
                    Log.d("FeedbackFramgent","Draft set text");
                } catch (IOException e) {
                    // Error occurred when opening raw file for reading.
                } finally {
                    String contents = stringBuilder.toString();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
                LocalDate localDate = LocalDate.now();
                FeedbackResource feedbackResource = new FeedbackResource();
                FeedbackContent feedback;
                if (currentUser.getType().equals("Company"))
                    feedback = new FeedbackContent(editText.getText().toString(),feedbackResource.count+1+"", currentUser.getUserName(),Integer.parseInt(dtf.format(localDate)));
                else
                    feedback = new FeedbackContent(editText.getText().toString(),feedbackResource.count+1+"", Integer.parseInt(dtf.format(localDate)),currentUser.getUserName());




                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<FeedbackContent> call1 = apiInterface.submitFeedback(currentUser.getIdToken(),feedback);
                call1.enqueue(new Callback<FeedbackContent>() {
                    @Override
                    public void onResponse(Call<FeedbackContent> call, Response<FeedbackContent> response) {
                        Log.d("FeedbackFramgent","Submitted Feedback");
                        feedbackResource.count++;
                        if (file.exists()){
                            boolean deleted = file.delete();
                            Log.d("FeedbackFramgent","Draft deleted");
                        }
                        editText.getText().clear();
                        Toast.makeText(getContext(),"Feedback submitted",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<FeedbackContent> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });

        binding.buttonSaveAsDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream fos = getActivity().openFileOutput(FILENAME, getActivity().MODE_PRIVATE);
                    fos.write(editText.getText().toString().getBytes());
                    Log.d("FeedbackFramgent","Draft saved");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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