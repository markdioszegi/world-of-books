package com.mycompany.app.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Location {
    // #region Field vars
    private String id; // PK
    @SerializedName("manager_name")
    private String managerName;
    private String phone;
    @SerializedName("address_primary")
    private String addressPrimary;
    @SerializedName("address_secondary")
    private String addressSecondary;
    private String country;
    private String town;
    @SerializedName("postal_code")
    private String postalCode;
    // #endregion

    // #region Getters / Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressPrimary() {
        return addressPrimary;
    }

    public void setAddressPrimary(String addressPrimary) {
        this.addressPrimary = addressPrimary;
    }

    public String getAddressSecondary() {
        return addressSecondary;
    }

    public void setAddressSecondary(String addressSecondary) {
        this.addressSecondary = addressSecondary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    // #endregion

    // Empty ctor
    public Location() {

    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(this);
    }
}
