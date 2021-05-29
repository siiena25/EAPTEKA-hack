package com.eapteka.eaptekatests.database;

import android.content.Context;

import com.eapteka.eaptekatests.ApplicationModified;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatabaseApiRepository {

    private static final String BASE_URL = "https://eapteka-bb5ce-default-rtdb.firebaseio.com/";
    private final UserApi mUserApi;

    public DatabaseApiRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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