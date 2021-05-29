package com.eapteka.eaptekatests.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface UserApi {

    @GET("/Pills/{nameOfPill}.json")
    Call<Pill> getPillByName(@Path("nameOfPill") String nameOfPill);

    @Headers("Content-Type: application/json")
    @GET("Pills")
    Call<Pills> getAllPills();
}