package com.prolan.cars.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prolan on 25/06/2016.
 */
public class ApiClient {
    public static final String BASE_URL = "https://api.edmunds.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
