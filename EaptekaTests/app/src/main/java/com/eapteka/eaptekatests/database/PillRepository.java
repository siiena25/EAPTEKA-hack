package com.eapteka.eaptekatests.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import retrofit2.Response;

public class PillRepository {


    private static final String LOG_TAG = "PillRepository";

    private final Context context;

    private final UserApi userApi;

    private final Logger logger;

    private final MutableLiveData<Pill> currentPill = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Pill>> allPills = new MutableLiveData<>();

    public PillRepository(Context context) {
        this.context = context;

        logger = new Logger(LOG_TAG, true);

        userApi = DatabaseApiRepository.from(context).getUserApi();
    }

    public LiveData<Pill> getPillData() {
        return currentPill;
    }

    public LiveData<ArrayList<Pill>> getAllPills() {
        return allPills;
    }

    public void updatePillFromRemoteDB(final String nameOfPill) {
        userApi.getPillByName(nameOfPill).enqueue(new DatabaseCallback<Pill>(LOG_TAG) {
            @Override
            public void onNullResponse(Response<Pill> response) {
                logger.errorLog("Fail with update");
            }

            @Override
            public void onSuccessResponse(Response<Pill> response) {
                currentPill.postValue(response.body());
            }
        });
    }

    public void updateAllPillsFromRemoteDB() {
        userApi.getAllPills().enqueue(new DatabaseCallback<Pills>(LOG_TAG) {
            @Override
            public void onNullResponse(Response<Pills> response) {
                logger.errorLog("Fail with update");
            }

            @Override
            public void onSuccessResponse(Response<Pills> response) {
                logger.log("Success response");
                ArrayList<Pill> pills= response.body().pills;
                assert pills != null;
                for (Pill pill : pills) {
                    logger.log(pill.name);
                }
                allPills.postValue(pills);
            }
        });
    }
}
