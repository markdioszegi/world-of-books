package com.mycompany.app;

import java.util.List;
import com.mycompany.app.models.Listing;
import com.mycompany.app.models.ListingStatus;
import com.mycompany.app.models.Location;
import com.mycompany.app.models.Marketplace;
import com.mycompany.app.services.APIService;
import com.mycompany.app.services.ConfigService;
import com.mycompany.app.services.LoggerService;
import com.mycompany.app.services.MySQLService;
import com.mycompany.app.services.ValidationService;

public class App {
    private LoggerService logger;
    private ConfigService config;
    private MySQLService mysql;
    private ValidationService validation;

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.init();
    }

    public void init() {
        logger = new LoggerService();
        config = new ConfigService("config.properties", logger);
        mysql = new MySQLService(config, logger);

        APIService api = new APIService(logger, config);
        // Get others first so we can validate the listings
        // TODO use these
        List<Listing> listings = api.getListings();
        List<Location> locations = api.getLocations();
        List<ListingStatus> listingStatuses = api.getListingStatuses();
        List<Marketplace> marketplaces = api.getMarketplaces();

        // List<Listing> listings = mysql.getListings();
        // List<Location> locations = mysql.getLocations();
        // List<ListingStatus> listingStatuses = mysql.getListingStatuses();
        // List<Marketplace> marketplaces = mysql.getMarketplaces();

        validation =
                new ValidationService(listings, locations, listingStatuses, marketplaces, logger);

        // for (Listing listing : listings) {
        // mysql.insertListing(listing);
        // }

        // for (Location location : locations) {
        // mysql.insertLocation(location);
        // }

        // for (ListingStatus listingStatus : listingStatuses) {
        // mysql.insertListingStatus(listingStatus);
        // }

        // for (Marketplace marketplace : marketplaces) {
        // mysql.insertMarketplace(marketplace);
        // }

        // testLocationRoute(api, logger);
        // testListingStatusRoute(api);
        // testMarketplaceRoute(api);

        // mysql.performQuery("SELECT * FROM listings");
        // System.out.println(mysql.getListings().get(0));
    }

    // #region Test methods
    public static void testListingRoute(APIService api) {
        List<Listing> listings = api.getListings();
        for (Listing listing : listings) {
            System.out.println(listing);
        }
    }

    public static void testLocationRoute(APIService api, LoggerService logger) {
        List<Location> locations = api.getLocations();
        for (Location location : locations) {
            // System.out.println(location);
            logger.info("\n" + location.toString());
        }
    }

    public static void testListingStatusRoute(APIService api) {
        List<ListingStatus> listingStatuses = api.getListingStatuses();
        for (ListingStatus listingStatus : listingStatuses) {
            System.out.println(listingStatus);
        }
    }

    public static void testMarketplaceRoute(APIService api) {
        List<Marketplace> marketplaces = api.getMarketplaces();
        for (Marketplace marketplace : marketplaces) {
            System.out.println(marketplace);
        }
    }
    // #endregion
}
