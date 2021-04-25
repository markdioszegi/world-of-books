package com.mycompany.app;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
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
                        + " on" + connectionProperties.getProperty("database.host") + "...");

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

    public void performQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
