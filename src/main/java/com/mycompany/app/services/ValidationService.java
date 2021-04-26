package com.mycompany.app.services;

import java.util.List;
import com.mycompany.app.models.Listing;
import com.mycompany.app.models.ListingStatus;
import com.mycompany.app.models.Location;
import com.mycompany.app.models.Marketplace;
import com.mycompany.app.utils.IOHandler;

public class ValidationService {
    private List<Listing> listings;
    private List<Location> locations;
    private List<ListingStatus> listingStatuses;
    private List<Marketplace> marketplaces;
    private LoggerService logger;

    public ValidationService(List<Listing> listings, List<Location> locations,
            List<ListingStatus> listingStatuses, List<Marketplace> marketplaces,
            LoggerService logger) {
        this.listings = listings;
        this.locations = locations;
        this.listingStatuses = listingStatuses;
        this.marketplaces = marketplaces;
        this.logger = logger;

        validate();
    }

    public void validate() {
        for (Listing listing : listings) {
            // System.out.println(listing.getInventoryItemLocationId());

            List<String> invalidFields = listing.validate(locations, listingStatuses, marketplaces);

            // TASK: Collect the invalid lines and write it to importLog CSV file with the following
            // content for each row. "ListingId;MarketplaceName;InvalidField"
            // Q: What if there is no reference to marketplace?

            String marketplaceName = "";

            invalidFields.add("currency");
            invalidFields.add("marketplace");

            for (Marketplace marketplace : marketplaces) {
                if (listing.getMarketplace() == marketplace.getId()) {
                    marketplaceName = marketplace.getMarketplaceName();
                    break;
                }
            }

            if (invalidFields.size() > 0) {
                for (String invalidField : invalidFields) {
                    IOHandler.writeToFile("importLog.csv", String.format("%s;%s;%s",
                            listing.getId(), marketplaceName, invalidField));
                }
                logger.success("Invalid fields successfully exported to importLog.csv.");
            }
        }
    }
}
