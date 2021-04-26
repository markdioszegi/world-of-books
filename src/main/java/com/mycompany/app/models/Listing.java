package com.mycompany.app.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.GsonBuilder;

/**
 * @param age
 * @param name
 * @param dob
 * 
 * @see google.com
 */
public class Listing {
    // #region Field vars
    private String id; // PK
    private String title;
    private String description;
    private String inventoryItemLocationId; // FK
    private float listingPrice;
    private String currency;
    private int quantity;
    private int listingStatus; // FK
    private int marketplace; // FK
    private Date uploadTime;
    private String ownerEmailAddress;
    // #endregion

    // #region Getters / Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInventoryItemLocationId() {
        return inventoryItemLocationId;
    }

    public void setInventoryItemLocationId(String inventoryItemLocationId) {
        this.inventoryItemLocationId = inventoryItemLocationId;
    }

    public float getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(float listingPrice) {
        this.listingPrice = listingPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(int listingStatus) {
        this.listingStatus = listingStatus;
    }

    public int getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(int marketplace) {
        this.marketplace = marketplace;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }
    // #endregion Getters / Setters

    // Empty ctor
    public Listing() {

    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(this);
    }

    // # region Validation
    public List<String> validate(List<Location> locations, List<ListingStatus> listingStatuses,
            List<Marketplace> marketplaces) {
        List<String> invalidFields = new ArrayList<String>();

        // CHECK id UUID & not null
        if (id == null) {
            invalidFields.add("id");
        }

        // CHECK title not null
        if (title == null) {
            invalidFields.add("title");
        }

        // CHECK description not null
        if (description == null) {
            invalidFields.add("description");
        }

        // CHECK inventoryItemLocationId not null & has reference to location table
        if (inventoryItemLocationId == null) {
            invalidFields.add("inventoryItemLocationId");
        }
        boolean hasReferenceToLocation = false;
        for (Location location : locations) {
            if (location.getId().equals(this.inventoryItemLocationId)) {
                hasReferenceToLocation = true;
                break;
            }
        }
        if (!hasReferenceToLocation) {
            invalidFields.add("inventoryItemLocationId");
        }

        // CHECK listingPrice not null & greater than 0 & decimals are 2
        if (listingPrice == 0.0f) {
            invalidFields.add("listingPrice");
        }

        // CHECK currency not null & length is strictly 3
        if (currency == null || currency.length() != 3) {
            invalidFields.add("currency");
        }

        // CHECK quantity not null & greater than 0
        if (quantity == 0 || quantity <= 0) {
            invalidFields.add("quantity");
        }

        // CHECK listingStatus not null & has reference to listing status table
        if (listingStatus == 0) {
            invalidFields.add("listingStatus");
        }
        boolean hasReferenceToListingStatus = false;
        for (ListingStatus listingStatus : listingStatuses) {
            if (listingStatus.getId() == this.listingStatus) {
                hasReferenceToListingStatus = true;
                break;
            }
        }
        if (!hasReferenceToListingStatus) {
            invalidFields.add("listingStatus");
        }

        // CHECK marketplace not null & has reference to marketplace table
        if (marketplace == 0) {
            invalidFields.add("marketplace");
        }
        boolean hasReferenceToMarketplace = false;
        for (Marketplace marketplace : marketplaces) {
            if (marketplace.getId() == this.marketplace) {
                hasReferenceToMarketplace = true;
                break;
            }
        }
        if (!hasReferenceToMarketplace) {
            invalidFields.add("marketplace");
        }

        // CHECK ownerEmailAddress not null & is it really an email
        Pattern validEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = validEmail.matcher(ownerEmailAddress);

        if (ownerEmailAddress == null || !matcher.find()) {
            invalidFields.add("ownerEmailAddress");
        }

        return invalidFields;
    }
    // #endregion
}
