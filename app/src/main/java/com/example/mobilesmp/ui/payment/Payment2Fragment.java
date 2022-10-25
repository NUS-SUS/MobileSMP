package com.example.mobilesmp.ui.payment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.NavHomeActivity;
import com.example.mobilesmp.R;
import com.example.mobilesmp.databinding.FragmentPayment1Binding;
import com.example.mobilesmp.databinding.FragmentPayment2Binding;
import com.example.retrofit.smp.CurrentPayment;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.PaymentContent;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Payment2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Payment2Fragment extends Fragment {

    private String clientRefId;
    FragmentPayment2Binding binding;
    View view;
    APIInterface apiInterface;
    Boolean haveResponse;
    int counter;

    public Payment2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Payment2Fragment", "onCreateView");
        binding = FragmentPayment2Binding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        haveResponse = false;
        counter = 0;
        Log.d("initial haveResponse-> ", "false");
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Binding Buttons
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bm = getBitmapFromView(view);
                try {
                    savePDF(bm,CurrentPayment.getClientRefId());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NavHomeActivity.class);
                startActivity(intent);
            }
        });
        Log.d("Payment2Fragment ", "onViewCreated -> Start");
        clientRefId = CurrentPayment.getClientRefId();
        Log.d("clientRefId -> ", clientRefId);
        View saveReceiptBtn = view.findViewById(R.id.saveBtn);
        saveReceiptBtn.setVisibility(View.GONE);
        // Payment Background Checking -- START
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Handler mHandler= new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do your stuff here, called every second
                Call<PaymentContent> call1 = apiInterface.doGetPaymentResources(clientRefId);
                call1.enqueue(new Callback<PaymentContent>() {
                    @Override
                    public void onResponse(Call<PaymentContent> call, Response<PaymentContent> response) {
                        Log.d("Payment2Fragment",response.code()+" => response code");
                        PaymentContent paymentContent = response.body();
                        Log.d("Payment2Fragment","PaymentContent =>\n" + paymentContent.paymentsId +"\n" +
                                paymentContent.paymentType + "\n" + paymentContent.amount);
                        haveResponse = true;
                        Log.d("update haveResponse-> ", "true");
                    }

                    @Override
                    public void onFailure(Call<PaymentContent> call, Throwable t) {
                        Log.d("PaymentExample","Payment nv get through");
                        call.cancel();
                        counter +=1;
                    }
                });
                if(haveResponse){
                    Intent intent = new Intent("CompanyEventUpdate");
                    intent.putExtra("Amount",CurrentPayment.getAmount());
                    LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);
                    TextView textViewPayment2 = (TextView) view.findViewById(R.id.textViewPayment2);
                    textViewPayment2.setText("Payment complete! Please click on SAVE RECEIPT to save a copy. Otherwise click on RETURN HOME to exit");
                    View progressBar1 = view.findViewById(R.id.progressBar1);
                    progressBar1.setVisibility(View.GONE);
                    TextView textViewPayment2a = (TextView) view.findViewById(R.id.textViewPayment2a);
                    textViewPayment2a.setText(
                            "Receipt ID:"+"\n"
                            +"Credit Purchased:"+"\n"
                            +"Email:"
                    );
                    TextView textViewPayment2b = (TextView) view.findViewById(R.id.textViewPayment2b);
                    textViewPayment2b.setText(
                            clientRefId +"\n"
                            +"$"+CurrentPayment.getAmount() +"\n"
                            +CurrentPayment.getUserEmail()
                    );
                    saveReceiptBtn.setVisibility(View.VISIBLE);

                }else if(counter > 15){
                    //stop background thread if too long
                }else{
                    mHandler.postDelayed(this, 5000);
                }
                // Payment Background Checking -- END
            }
        };
        mHandler.post(runnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public  Bitmap getBitmapFromView(View view) {
     /*   //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap*/
        // create bitmap screen capture
        View v1 = getActivity().getWindow().getDecorView().getRootView();
        v1.setDrawingCacheEnabled(true);
        Bitmap returnedBitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);
        return returnedBitmap;
    }


    public void savePDF(Bitmap bm, String name) throws FileNotFoundException, DocumentException {
        // If you have access to the external storage, do whatever you need
        if (Environment.isExternalStorageManager()){

             // If you don't have access, launch a new activity to show the user the system's dialog
             // to allow access to the external storage
        }else{
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
        try
        {
            String folder_main = "invoices";
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                            ,Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                    1);
//            String origin = Environment.getExternalStorageDirectory().getAbsolutePath();
            File folder = new File(Environment.getExternalStorageDirectory(), folder_main);
            if (!folder.exists()) {
                Log.d("Payment2 SD DIRECTORY","DONT EXIST");
                folder.mkdirs();

            }else{
                Log.d("Payment2 SD DIRECTORY","EXIST");
            }
            String FILE = folder+"/invoice_"+ name +".pdf";
//            String FILE = origin+"/invoice_"+ name +".pdf";
            Log.d("Payment2 SD PATH->",FILE);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            addImage(document,byteArray);
            document.close();
            openPDF(FILE);

            Log.d("Payment2 RECIPT DONE","CHECKINGGGGGG");
//            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void addImage(Document document,byte[] byteArray)
    {
        try
        {
            Image image = Image.getInstance(byteArray);
             image.scaleAbsolute(360f, 780f);
            document.add(image);
        }
        catch (BadElementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException | DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private void openPDF(String pdfFile) {
        // Get the File location and file name.
        File file = new File(pdfFile);
        Log.d("pdfFIle", "" + file);

        // Get the URI Path of file.
        Uri uriPdfPath = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
        Log.d("pdfPath", "" + uriPdfPath);

        // Start Intent to View PDF from the Installed Applications.
        Intent pdfOpenIntent = new Intent(Intent.ACTION_VIEW);
        pdfOpenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenIntent.setClipData(ClipData.newRawUri("", uriPdfPath));
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf");
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |  Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        try {
            startActivity(pdfOpenIntent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText(getActivity(),"There is no app to load corresponding PDF",Toast.LENGTH_LONG).show();

        }
    }
}