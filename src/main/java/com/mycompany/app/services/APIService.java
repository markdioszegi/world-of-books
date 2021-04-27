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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
        URL newURL = apiURL;
        String strJson = "";

        try {
            newURL = new URL(String.format("%s%s?key=%s", apiURL.toURI(), route, apiKey));
        } catch (MalformedURLException | URISyntaxException urlException) {
            // TODO Auto-generated catch block
            urlException.printStackTrace();
        }

        logger.info(String.format("Getting data from %s...", newURL));

        // Get content from the API
        try {
            InputStream is = newURL.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                strJson += br.readLine();
            }
            br.close();
            is.close();
        } catch (Exception exception) {
            // TODO: handle exception
            exception.printStackTrace();
        }

        if (!classType.getSimpleName().equals("Listing"))
            System.out.println("JSON: " + strJson);

        JsonArray array = JsonParser.parseString(strJson).getAsJsonArray();

        List<T> result = new ArrayList<T>();
        for (final JsonElement json : array) {
            T entity = new GsonBuilder().setDateFormat("MM/dd/yyyy").create().fromJson(json,
                    classType);
            result.add(entity);
        }

        logger.success("Data retrieved successfully!");

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
