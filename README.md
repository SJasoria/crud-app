# Inventory Tracking CRUD Web App
This application is designed to perform the following operations on the database:
1. Create inventory item
2. Read all inventory items
3. Update inventory item
4. Delete inventory item

Additionally, it allows the user to download CSV file of the inventory table.

Currently, the application uses an in-memory H2 database. The data will not be persisted across sessions.

### System Requirements
1. JDK 1.8 or higher
2. Maven 3.6.3 or higher

### Setup
1. Clone the repository
2. In the project directory call the following commands
```bash
mvn clean install
mvn spring-boot:run
```
The web-app will be hosted on http://localhost:8080/.
