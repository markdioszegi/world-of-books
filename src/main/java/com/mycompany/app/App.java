package com.mycompany.app;

public class App {
    public static void main(String[] args) throws InterruptedException {
        LoggerService logger = new LoggerService();

        ConfigService config = new ConfigService("config.properties", logger);

        // Test logger
        logger.info("Hello! testing...");

        MySQLService mysql = new MySQLService(config, logger);
        mysql.performQuery("SELECT * FROM users");
    }
}
