# start application without dependencies
# mvn package; cls; java -cp target/world-of-books-1.0-SNAPSHOT.war com.mycompany.app.App

# proper way to start a maven project
mvn package; cls; mvn exec:java