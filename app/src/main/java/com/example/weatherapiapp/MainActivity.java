package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Button btn_cityID,btn_getWeatherByID,btn_getWeatherByName;
    public EditText et_dataInput;
    public ListView lv_weatherReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // asssigning values
        btn_cityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID =findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);
         final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        //click listener

        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               

                //This doesnot return anything
                weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something is wrong" , Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this,"Returned an ID of" + cityID, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something is Wrong",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
//                        Toast.makeText(MainActivity.this,weatherReportModel.toString(),Toast.LENGTH_LONG).show();

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModel );
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });

//                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
//                    @Override
//                    public void onError(String message) {
//                        Toast.makeText(MainActivity.this,"Something is wrong" , Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onResponse(String cityID) {
//                        Toast.makeText(MainActivity.this,"Returned an ID of" + cityID, Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,et_dataInput.getText().toString() ,Toast.LENGTH_SHORT).show();

                    weatherDataService.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.GetCityForecastByName() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this,"Something is Wrong",Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onResponse(List<WeatherReportModel> weatherReportModel) {
//                        Toast.makeText(MainActivity.this,weatherReportModel.toString(),Toast.LENGTH_LONG).show();

                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModel );
                            lv_weatherReport.setAdapter(arrayAdapter);
                        }
                    });

//                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
//                    @Override
//                    public void onError(String message) {
//                        Toast.makeText(MainActivity.this,"Something is wrong" , Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onResponse(String cityID) {
//                        Toast.makeText(MainActivity.this,"Returned an ID of" + cityID, Toast.LENGTH_SHORT).show();
//                    }
//                });


            }
        });


    }
}