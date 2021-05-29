package com.eapteka.eaptekatests.database;

import com.google.gson.annotations.SerializedName;

public class Pill {
    @SerializedName("apply")
    private String apply;

    @SerializedName("form")
    private String form;

    @SerializedName("life")
    private String life;

    @SerializedName("name")
    private String name;

    @SerializedName("storageConditions")
    private String storageConditions;

    @SerializedName("substance")
    private String substance;

    @SerializedName("testimony")
    private String testimony;

    public Pill(String apply, String form, String life, String name, String storageConditions, String substance, String testimony) {
        this.apply = apply;
        this.form = form;
        this.life = life;
        this.name = name;
        this.storageConditions = storageConditions;
        this.substance = substance;
        this.testimony = testimony;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public void setTestimony(String testimony) {
        this.testimony = testimony;
    }

    public String getApply() {
        return apply;
    }

    public String getForm() {
        return form;
    }

    public String getLife() {
        return life;
    }

    public String getName() {
        return name;
    }

    public String getStorageConditions() {
        return storageConditions;
    }

    public String getSubstance() {
        return substance;
    }

    public String getTestimony() {
        return testimony;
    }
}
