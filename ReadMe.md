Amazon
API to place orders or sell items

Features
View items (all, by ID, by price, by seller ID)
Manage  (create, update, delete)
View users (all, by ID)
Manage users (create, update, delete)
Set roles for users (user, admin)
User Login / Logout
Technologies Utilized
Java Version 1.8
PostgreSQL Database 13
Apache Tomcat Server Version 9
Java Enterprise Javax Servlet API Version 3.1
Maven
JUnit4 (Testing)
Log4j Version 1.2 (Logging)

Items Endpoint
View All Items
GET http://localhost:4321/items
Response: Returns all items available

View Items by ID, Price, Seller ID
GET http://localhost:4321/items?(id, price, seller)=(value)
Response: Returns all items that match {value}.

Create New Item
Post http://localhost:4321/items?values=(id, name, price, description, sellerID)
Response: Creates new item with the field values

Update Item Price
Put http://localhost:4321/items?id=(id)&price=(value)
Response: Updates item price to new value

Delete Item Based on ID
DELETE http://localhost:4321/items?id?id=(id)
Response: Deletes item based on {id}.

Users Domain
View All Users
GET http://localhost:4321/users
Response: Returns all users available

View Users by ID
GET http://localhost:4321/users?id=(value)
Response: Returns the user that matches {value}.

Create New User
Post http://localhost:4321/users?values=(id, email, password, balance, roleID)
Response: Creates new user with the field values

Update User Balance
Put http://localhost:4321/users?id=(id)&balance=(value)
Response: Updates user balance to new value

Delete User Based on ID:
DELETE http://localhost:4321/users?id?id=(id)
Response: Deletes user based on {id}.

Database Schema
Schema

Installation
User
Download Java Runtime Environment at: https://www.oracle.com/java/technologies/javase-jre8-downloads.html
Download PostgreSQL database at: https://www.postgresql.org/download/
Download Apache Tomcat version 9 at: https://tomcat.apache.org/download-90.cgi
Download amazon.war file from GitHub at: https://github.com/tobynboudreaux/Amazon-Project0-TNB
Download Postman at: https://www.postman.com/downloads/
Navigate to http://localhost:8080/animalshelter using Postman to begin using API.
Developer
Download and install Java JDK, IDE, Postman, and Maven if needed.
Install PostreSQL and Tomcat as instructed above for users.
Download source code from GitHub at: https://github.com/tobynboudreaux/Amazon-Project0-TNB
Be sure to load dependencies from pom.xml file in project directory (Maven will install these automatically).

