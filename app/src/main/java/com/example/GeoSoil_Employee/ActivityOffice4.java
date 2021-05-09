package com.example.GeoSoil_Employee;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ActivityOffice4 extends AppCompatActivity {
    Workbook workbook;
    AsyncHttpClient asyncHttpClient;
    List<String> fullnames,nicknames,phonenumbers,officelevels,images;
    RecyclerView recyclerView;
    Adapter adapter;
    ProgressBar progressBar;
    TextView wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office4);

        recyclerView = findViewById(R.id.listOfData);
        progressBar = findViewById(R.id.progressBar);
        wait = findViewById(R.id.wait);

        String url = "https://github.com/bumrungyat/GeoSoil_Employee/blob/main/Civilengineer_Employee.xls?raw=true";
        //String apiURL = "https://bikashthapa01.github.io/excel-reader-android-app/";
        fullnames = new ArrayList<>();
        nicknames = new ArrayList<>();
        phonenumbers = new ArrayList<>();
        officelevels = new ArrayList<>();
        images = new ArrayList<>();

//        // checking if the excel file has new content
//
//        try {
//            URL mURL = new URL(apiURL);
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) mURL.openConnection();
//            InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
//            // getting network os exception error
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(ActivityOffice4.this, "Error in Downloading Excel File", Toast.LENGTH_SHORT).show();
                wait.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if(file != null){
                    //text.setText("Success, DO something with the file.");
                    wait.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        //Cell[] row = sheet.getRow(1);
                        //text.setText(row[0].getContents());
                        for(int i = 0;i< sheet.getRows();i++){
                            Cell[] row = sheet.getRow(i);
                            fullnames.add(row[0].getContents());
                            nicknames.add(row[1].getContents());
                            phonenumbers.add(row[2].getContents());
                            officelevels.add(row[3].getContents());
                            images.add(row[4].getContents());
                        }

                        showData();


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,fullnames,nicknames,phonenumbers,officelevels,images);
        recyclerView.setAdapter(adapter);
    }
}