package com.eapteka.eaptekatests.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class PillViewModel extends AndroidViewModel {

    private static final String LOG_TAG = "PillViewModel";
    private final Logger logger;

    private final PillRepository repository;

    private final LiveData<Pill> pill;
    private final LiveData<ArrayList<Pill>> allPills;

    public PillViewModel(@NonNull Application application) {
        super(application);
        logger = new Logger(LOG_TAG, true);

        repository = new PillRepository(getApplication());
        pill = repository.getPillData();
        allPills = repository.getAllPills();
    }

    public void updatePill(String nameOfPill) {
        repository.updatePillFromRemoteDB(nameOfPill);
    }

    public void updateAllPills() {
        repository.updateAllPillsFromRemoteDB();
    }

    public LiveData<Pill> getPill() {
        return pill;
    }

    public LiveData<ArrayList<Pill>> getAllPills() {
        return allPills;
    }
}
