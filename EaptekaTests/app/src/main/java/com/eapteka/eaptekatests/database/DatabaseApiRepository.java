package com.eapteka.eaptekatests.database;

import android.content.Context;

import com.eapteka.eaptekatests.ApplicationModified;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatabaseApiRepository {

    private static final String BASE_URL = "https://eapteka-bb5ce-default-rtdb.firebaseio.com/";
    private final UserApi mUserApi;

    public DatabaseApiRepository() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mUserApi = retrofit.create(UserApi.class);
    }

    public UserApi getUserApi() {
        return mUserApi;
    }

    public static DatabaseApiRepository from(Context context) {
        return ApplicationModified.from(context).getApis();
    }
}