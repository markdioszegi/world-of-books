package com.mycompany.app.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Marketplace {
    // #region Field vars
    private int id; // PK
    @SerializedName("marketplace_name")
    private String marketplaceName;
    // #endregion

    // #region Getters / Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }
    // #endregion

    // Empty ctor
    public Marketplace() {

    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(this);
    }
}
