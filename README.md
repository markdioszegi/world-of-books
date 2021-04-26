## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependencies used

- `mysql-connector-java` driver from https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.24 to connect to a MySQL database.

- `gson` from https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.6 for serializing and deserializing

## Running the project

First build the project with `mvn package`.

Then simply run with `mvn exec:java`.

## Directory structure

    �📁 main/
    ├─�📁 java/
    │ └─�📁 com/
    │   └─�📁 mycompan
    │     └─�📁 app/
    │       ├─�📄 App.java          # The main class
    │       ├─�📁 models/
    │       │ ├─�📄 Listing.java                # Listing model
    │       │ ├─�📄 ListingStatus.java          # ListingStatus model
    │       │ ├─�📄 Location.java               # Location model
    │       │ └─�📄 Marketplace.java            # Marketplace model
    │       ├─�📁 services/
    │       │ ├─�📄 APIService.java                 # Getting data from the API
    │       │ ├─�📄 ConfigService.java              # Gets properties from an external config file
    │       │ ├─�📄 LoggerService.java              # Simple logging to file and/or console
    │       │ ├─�📄 MySQLService.java               # Responsible for establishing a connection and executing queries
    │       │ ├─�📄 ReportService.java              # Handles the logic of reporting using the MySQL service
    │       │ └─�📄 ValidationService.java          # Validation separated for listings
    │       └─�📁 utils/
    │         └─�📄 IOHandler.java          # Simple handler writing/reading files
    └─�📁 resources/
      └─�📄 config.properties

## Conclusion

Unfortunately, I didn't have the time to finish the entire project. The tasks weren't that difficult, it's just I was thinking about the design too much. Besides, it was too late when I realized that the API is limited to 200 requests a day so I couldn't test it correctly. 🙁
