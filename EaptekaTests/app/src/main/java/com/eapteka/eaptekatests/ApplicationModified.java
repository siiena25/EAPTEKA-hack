package com.eapteka.eaptekatests;

import android.app.Application;
import android.content.Context;

import com.eapteka.eaptekatests.database.DatabaseApiRepository;

public class ApplicationModified extends Application {

    private DatabaseApiRepository databaseApiRepository;
    @Override
    public void onCreate() {
        super.onCreate();
        databaseApiRepository = new DatabaseApiRepository();
    }

    public DatabaseApiRepository getApis() {
        return databaseApiRepository;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
