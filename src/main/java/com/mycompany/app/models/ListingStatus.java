package com.mycompany.app.models;

import com.google.gson.GsonBuilder;

public class ListingStatus {
    // #region Field vars
    private int id; // PK
    private String statusName;
    // #endregion

    // #region Getters / Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    // #endregion

    // Empty ctor
    public ListingStatus() {

    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(this);
    }
}
