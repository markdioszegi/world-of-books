package com.mycompany.app.services;

import com.mycompany.app.models.Listing;
import com.mycompany.app.models.ListingStatus;
import com.mycompany.app.models.Location;
import com.mycompany.app.models.Marketplace;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLService {
    // #region Field vars
    private Properties connectionProperties;
    private Connection connection = null;
    private LoggerService logger;
    // #endregion

    // Constructor
    public MySQLService(ConfigService config, LoggerService logger) {
        setConnectionProperties(config.getProps());
        setLogger(logger);
        connect();
    }

    // #region Getters / Setters
    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public LoggerService getLogger() {
        return logger;
    }

    public void setLogger(LoggerService logger) {
        this.logger = logger;
    }
    // #endregion

    // Methods
    // Connect to a MySQL Database
    private void connect() {
        /*
         * Properties connProps = new Properties(); connProps.put("host", "localhost:3306");
         * connProps.put("database", "world_of_books"); connProps.put("user", "root");
         * connProps.put("password", "admin");
         */
        logger.info(
                "Connecting to database " + connectionProperties.getProperty("database.database")
                        + " on " + connectionProperties.getProperty("database.host") + "...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + connectionProperties.getProperty("database.host") + "/"
                            + connectionProperties.getProperty("database.database"),
                    connectionProperties.getProperty("database.user"),
                    connectionProperties.getProperty("database.password"));

            logger.success("Successfully connected to the MySQL database.");
        } catch (Exception e) {
            System.out.println("An error occurred while trying to connect to the database.");
            e.printStackTrace();
        }

        // TODO remove later
        // connection.close();
    }

    // #region SELECT Query Methods
    // Basic query
    public void performQuery(String query) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                logger.info("" + rs.getRow());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get listings
    public List<Listing> getListings() {
        List<Listing> result = new ArrayList<Listing>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM listings;");
            while (rs.next()) {
                Listing listing = new Listing();

                listing.setId(rs.getString("id"));
                listing.setTitle(rs.getString("title"));
                listing.setDescription(rs.getString("description"));
                listing.setInventoryItemLocationId(rs.getString("inventory_item_location_id"));
                listing.setListingPrice(rs.getFloat("listing_price"));
                listing.setCurrency(rs.getString("currency"));
                listing.setQuantity(rs.getInt("quantity"));
                listing.setListingStatus(rs.getInt("listing_status"));
                listing.setMarketplace(rs.getInt("marketplace"));
                listing.setUploadTime(rs.getDate("upload_time"));
                listing.setOwnerEmailAddress(rs.getString("owner_email_address"));

                result.add(listing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Get locations
    public List<Location> getLocations() {
        List<Location> result = new ArrayList<Location>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM locations;");
            while (rs.next()) {
                Location location = new Location();

                location.setId(rs.getString("id"));
                location.setManagerName(rs.getString("manager_name"));
                location.setPhone(rs.getString("phone"));
                location.setAddressPrimary(rs.getString("address_primary"));
                location.setAddressSecondary(rs.getString("address_secondary"));
                location.setCountry(rs.getString("country"));
                location.setTown(rs.getString("town"));
                location.setPostalCode(rs.getString("postal_code"));

                result.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Get listing statuses
    public List<ListingStatus> getListingStatuses() {
        List<ListingStatus> result = new ArrayList<ListingStatus>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM listing_statuses;");
            while (rs.next()) {
                ListingStatus listingStatus = new ListingStatus();

                listingStatus.setId(rs.getInt("id"));
                listingStatus.setStatusName(rs.getString("status_name"));

                result.add(listingStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Get marketplaces
    public List<Marketplace> getMarketplaces() {
        List<Marketplace> result = new ArrayList<Marketplace>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM marketplaces;");
            while (rs.next()) {
                Marketplace marketplace = new Marketplace();

                marketplace.setId(rs.getInt("id"));
                marketplace.setMarketplaceName(rs.getString("marketplace_name"));

                result.add(marketplace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    // #endregion

    // #region INSERT Query Methods
    public void insertListing(Listing listing) {
        String query = "INSERT INTO listings VALUES (?,?,?,?,?,?,?,?,?,?,?)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, listing.getId());
            preparedStatement.setString(2, listing.getTitle());
            preparedStatement.setString(3, listing.getDescription());
            preparedStatement.setString(4, listing.getInventoryItemLocationId());
            preparedStatement.setFloat(5, listing.getListingPrice());
            preparedStatement.setString(6, listing.getCurrency());
            preparedStatement.setInt(7, listing.getQuantity());
            preparedStatement.setInt(8, listing.getListingStatus());
            preparedStatement.setInt(9, listing.getMarketplace());
            preparedStatement.setDate(10, listing.getUploadTime());
            preparedStatement.setString(11, listing.getOwnerEmailAddress());

            int records = preparedStatement.executeUpdate();
            logger.success(records + " records inserted into listings.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLocation(Location location) {
        String query = "INSERT INTO locations VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, location.getId());
            preparedStatement.setString(2, location.getManagerName());
            preparedStatement.setString(3, location.getPhone());
            preparedStatement.setString(4, location.getAddressPrimary());
            preparedStatement.setString(5, location.getAddressSecondary());
            preparedStatement.setString(6, location.getCountry());
            preparedStatement.setString(7, location.getTown());
            preparedStatement.setString(8, location.getPostalCode());

            int records = preparedStatement.executeUpdate();
            logger.success(records + " records inserted into locations.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertListingStatus(ListingStatus listingStatus) {
        String query = "INSERT INTO listing_statuses VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, listingStatus.getId());
            preparedStatement.setString(2, listingStatus.getStatusName());

            int records = preparedStatement.executeUpdate();
            logger.success(records + " records inserted into listing_statuses.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMarketplace(Marketplace marketplace) {
        String query = "INSERT INTO marketplaces VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, marketplace.getId());
            preparedStatement.setString(2, marketplace.getMarketplaceName());

            int records = preparedStatement.executeUpdate();
            logger.success(records + " records inserted into marketplaces.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // #endregion
}
