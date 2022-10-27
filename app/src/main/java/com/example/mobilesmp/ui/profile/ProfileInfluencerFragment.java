package com.example.mobilesmp.ui.profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentProfileCompanyBinding;
import com.example.mobilesmp.databinding.FragmentProfileInfluencerBinding;
import com.example.retrofit.smp.ClassificationsResource;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.FeedbackContent;
import com.example.retrofit.smp.InfluencerContent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfluencerFragment extends Fragment {

    static TextView date;
    APIInterface apiInterface;

    final String[] COUNTRIES = {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};
    final String[] LANGUAGES = {"English","Chinese","Malay","Hindi"};

    FragmentProfileInfluencerBinding binding;

    ClassificationsResource classificationsResource = new ClassificationsResource();
    String[] tagsArray;
    boolean[] selectedTags;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileInfluencerBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = (TextView) view.findViewById(R.id.textView21);
        binding.buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getParentFragmentManager(), "datePicker");
            }
        });

        List<String> CATERGORIES = new ArrayList<>();
        List<String> TAGS = new ArrayList<>();
        CATERGORIES.add("");
        TAGS.add("");
        for(Map.Entry<String,String> entry: classificationsResource.categories.entrySet()) {
            CATERGORIES.add(entry.getKey());
        }

        Spinner spinner1 = (Spinner) view.findViewById(R.id.autoCompletetextView22);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, CATERGORIES);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        // set item selected listener on min spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // clear max list
                TAGS.clear();
                // use for loop
                String key = spinner1.getSelectedItem().toString();

                for(Map.Entry<String, List<String>> entry: classificationsResource.tags.entrySet()) {
                    if (entry.getKey().equals(classificationsResource.categories.get(key))){
                        List<String> tmpList = entry.getValue();
                        for (String s : tmpList)
                            TAGS.add(s);
                    }
                }

                selectedTags = new boolean[TAGS.size()];
                // notify adapter
                //dataAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView textView0 = (TextView) view.findViewById(R.id.textView23);
        ArrayList<Integer> tagList = new ArrayList<>();

        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tagsArray = new String[TAGS.size()];
                for (int i = 0; i < tagsArray.length; i++){
                    tagsArray[i] = TAGS.get(i);
                }

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // set title
                builder.setTitle("Select Language");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(tagsArray, selectedTags, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            tagList.add(i);
                            // Sort array list
                            Collections.sort(tagList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            tagList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < tagList.size(); j++) {
                            // concat array value
                            stringBuilder.append(tagsArray[tagList.get(j)]);
                            // check condition
                            if (j != tagList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView0.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedTags.length; j++) {
                            // remove all selection
                            selectedTags[j] = false;
                            // clear language list
                            tagList.clear();
                            // clear text view value
                            textView0.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        AutoCompleteTextView spinner3 = (AutoCompleteTextView) view.findViewById(R.id.autoCompletetextView24);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, COUNTRIES);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);

        TextView textView = (TextView) view.findViewById(R.id.textView25);
        boolean[] selectedLanguage;
        ArrayList<Integer> langList = new ArrayList<>();

        selectedLanguage = new boolean[LANGUAGES.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // set title
                builder.setTitle("Select Language");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(LANGUAGES, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(LANGUAGES[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguage.length; j++) {
                            // remove all selection
                            selectedLanguage[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });


        EditText social = (EditText) view.findViewById(R.id.textView26);
        EditText fname = (EditText) view.findViewById(R.id.textView27);


        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String[] tagsAr = textView0.getText().toString().split(",");
                    String[] langAr = textView.getText().toString().split(",");
                    List<String> tArr = new ArrayList<>();
                    List<String> lArr = new ArrayList<>();
                    for (String x : tagsAr)
                        tArr.add(x);

                    for (String x : langAr)
                        lArr.add(x);

                    InfluencerContent influencerContent = new InfluencerContent(date.getText().toString(),spinner1.getSelectedItem().toString(),tArr,
                            spinner3.getText().toString(),lArr,social.getText().toString(),fname.getText().toString());

                    if ((date.getText().toString().equals(""))||(spinner1.getSelectedItem().toString().equals(""))||(spinner3.getText().toString().equals(""))||(social.getText().toString().equals(""))||
                            (fname.getText().toString().equals("")))
                        throw new Exception("Error");
                    else
                        influencerContent.setBodyValues();

                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<InfluencerContent> call1 = apiInterface.submitInfluencer(influencerContent);
                    call1.enqueue(new Callback<InfluencerContent>() {
                        @Override
                        public void onResponse(Call<InfluencerContent> call, Response<InfluencerContent> response) {
                            Log.d("InfluencerFragment","Save");
                            Toast.makeText(getContext(), "Profile Save", Toast.LENGTH_SHORT).show();
                            updateInfluencerProfile();
                            NavHostFragment.findNavController(ProfileInfluencerFragment.this)
                                    .navigate(R.id.action_profileInfluencerFragment_to_nav_home);
                        }

                        @Override
                        public void onFailure(Call<InfluencerContent> call, Throwable t) {
                            call.cancel();
                        }
                    });

                }catch(Exception e) {
                    Toast.makeText(getContext(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date.setText(""+year+"/"+month+"/"+day);
        }
    }

    // calling Company profile function
    private void updateInfluencerProfile(){
        CurrentUser currentUser = new CurrentUser();

        Call<InfluencerContent> call1 = apiInterface.doGetInfluenceResources(currentUser.getUserEmail());
        CurrentUser finalCurrentUser = currentUser;
        call1.enqueue(new Callback<InfluencerContent>() {
            @Override
            public void onResponse(Call<InfluencerContent> call, Response<InfluencerContent> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                InfluencerContent influencerContent = response.body();
                influencerContent.setValues();
                Intent intent = new Intent("InfluencerEvent");
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);
                finalCurrentUser.setType("Influencer");
            }

            @Override
            public void onFailure(Call<InfluencerContent> call, Throwable t) {
                Log.d("CurrentUser","No Influencer");
                call.cancel();
            }
        });
    }

}