package com.prolan.cars.rest;

import com.prolan.cars.model.Pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Prolan on 25/06/2016.
 */
public interface ApiInterface {
    @GET("vehicle/v2/makes")
    Call<Pojo> getPojo(@Query("year") int year, @Query("view") String view, @Query("fmt")String fmt  ,@Query("api_key")String apikey);

}
