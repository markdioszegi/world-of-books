package com.mycompany.app.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mycompany.app.models.Listing;
import com.mycompany.app.models.ListingStatus;
import com.mycompany.app.models.Location;
import com.mycompany.app.models.Marketplace;

public class APIService {
    // #region Field vars
    private LoggerService logger;
    private Properties apiProperties;
    private String apiKey;
    private URL apiURL;
    // #endregion

    // Ctor
    public APIService(LoggerService logger, ConfigService config) {
        this.logger = logger;
        this.apiProperties = config.getProps();
        this.apiKey = apiProperties.getProperty("api.key");
        try {
            this.apiURL = new URL(apiProperties.getProperty("api.url"));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // #region Get a list of specified objects from a route
    private <T> List<T> get(String route, Class<T> classType) {
        // Set the URL based on the API URL
        // URL newURL = apiURL;
        String strJson =
                "[{'id':'abc3452-acef453','inventoryItemLocationId':'abc3452-abef453','listingStatus':3,'marketplace':2,'title':'Some title','description':'A long description','listingPrice':674.6478,'currency':'EUR','quantity':1,'uploadTime':'2015/06/04','ownerEmailAddress':'someone@somemail.com'}]";

        // TODO: uncomment when API is available again

        // try {
        // newURL = new URL(String.format("%s%s?key=%s", apiURL.toURI(), route, apiKey));
        // } catch (MalformedURLException | URISyntaxException urlException) {
        // // TODO Auto-generated catch block
        // urlException.printStackTrace();
        // }

        // // Get content from the API
        // try {
        // InputStream is = newURL.openStream();
        // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        // while (br.ready()) {
        // strJson += br.readLine();
        // }
        // } catch (Exception exception) {
        // // TODO: handle exception
        // exception.printStackTrace();
        // }

        JsonArray array = JsonParser.parseString(strJson).getAsJsonArray();

        List<T> result = new ArrayList<T>();
        for (final JsonElement json : array) {
            T entity = new Gson().fromJson(json, classType);
            result.add(entity);
        }

        return result;
    }
    // #endregion

    // #region Get routes
    public List<Listing> getListings() {
        return get("listing", Listing.class);
    }

    public List<Location> getLocations() {
        return get("location", Location.class);
    }

    public List<ListingStatus> getListingStatuses() {
        return get("listingStatus", ListingStatus.class);
    }

    public List<Marketplace> getMarketplaces() {
        return get("marketplace", Marketplace.class);
    }
    // #endregion
}
