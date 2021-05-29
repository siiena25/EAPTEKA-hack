package com.eapteka.eaptekatests.database;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pills {
    private ArrayList<Pill> pills;

    public void setPills(ArrayList<Pill> pills) {
        this.pills = pills;
    }

    public ArrayList<Pill> getPills() {
        return pills;
    }
}
