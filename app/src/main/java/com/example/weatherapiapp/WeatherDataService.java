package com.example.weatherapiapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {
    Context context;
    String cityId;

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(String cityID);
    }



    public String getCityID(String cityName, VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_CITY_ID + cityName;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,
                null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray jsonArray;
                 cityId = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityId = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(context,"City Id =" + cityId ,Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityId);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context,"Something is Wrong" ,Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something is Wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
//        return cityId;
        return url;
    }


//
//    public List<WeatherReportModel> getCityForecastByID(String cityID){
//
//    }
//    public List<WeatherReportModel> getCityForecastByName(String cityName){
//
//    }


}
