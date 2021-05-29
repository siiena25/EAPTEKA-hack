package com.eapteka.eaptekatests.database;

import com.eapteka.eaptekatests.test_models.Test;

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

    @GET("/Users/{username}/Current tests/{number of test}.json")
    Call<Test> getTest(@Path("username") String username, @Path("number of test") String numberOfTest);

    @GET("/Users/{username}/Current tests.json")
    Call<ArrayList<Test>> getAllTests(@Path("username") String username);
}