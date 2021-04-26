package com.mycompany.app.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    // #region Field vars
    private LoggerService logger;
    Properties props;
    String filename;
    // #endregion

    // #region Getters / Setters
    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    // #endregion

    // Public ctor
    public ConfigService(String filename, LoggerService logger) {
        setProps(new Properties());
        setFilename(filename);
        this.logger = logger;

        try {
            loadConfig();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Load from a config file
    // (defaults to config.properties in the resource folder)
    public void loadConfig() throws IOException {
        InputStream inputStream = null;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(filename);
            logger.success(
                    "Config file loaded from " + getClass().getClassLoader().getResource(filename));

            if (inputStream != null) {
                props.load(inputStream);
            } else {
                logger.error("An error occurred reading the config file.");
                throw new FileNotFoundException(
                        "Property file '" + filename + "' is not present in the classpath.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.err.println(e);
        } finally {
            inputStream.close();
        }
    }
}
