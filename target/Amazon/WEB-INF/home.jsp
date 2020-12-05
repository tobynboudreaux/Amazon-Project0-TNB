<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Welcome to Bootleg Amazon</title>
    </head>
    <body>
        <h1>Bootleg Amazon</h1>
        <h2>API to place orders or sell items</h2>
        <br>
        <h3>Features</h3>
        <br>
        <p>View items (all, by ID, by price, by seller ID)</p>
        <p>Manage  (create, update, delete)</p>
        <p>View users (all, by ID)</p>
        <p>Manage users (create, update, delete)</p>
        <p>Set roles for users (user, admin)</p>
        <p>User Login / Logout</p>
        <br>
        <h3>Technologies Utilized</h3>
        <br>
        <p>Java Version 1.8</p>
        <p>PostgreSQL Database 13</p>
        <p>Apache Tomcat Server Version 9</p>
        <p>Java Enterprise Javax Servlet API Version 3.1</p>
        <p>Maven</p>
        <p>JUnit4 (Testing)</p>
        <p>Log4j Version 1.2 (Logging)</p>
        <br>

        <h3>Items Endpoint</h3>
        <br>
        <h4>View All Items</h4>
        <p>GET http://localhost:4321/items</p>
        <p>Response: Returns all items available</p>
        <br>

        <h4>View Items by ID, Price, Seller ID</h4>
        <p>GET http://localhost:4321/items?(id, price, seller)=(value)</p>
        <p>Response: Returns all items that match {value}.</p>
        <br>

        <h4>Create New Item</h4>
        <p>Post http://localhost:4321/items?values=(id, name, price, description, sellerID)</p>
        <p>Response: Creates new item with the field values</p>
        <br>

        <h4>Update Item Price</h4>
        <p>Put http://localhost:4321/items?id=(id)&price=(value)</p>
        <p>Response: Updates item price to new value</p>
        <br>

        <h4>Delete Item Based on ID</h4>
        <p>DELETE http://localhost:4321/items?id?id=(id)</p>
        <p>Response: Deletes item based on {id}.</p>
        <br>

        <h3>Users Domain</h3>
        <br>
        <h4>View All Users</h4>
        <p>GET http://localhost:4321/users</p>
        <p>Response: Returns all users available</p>
        <br>

        <h4>View Users by ID</h4>
        <p>GET http://localhost:4321/users?id=(value)</p>
        <p>Response: Returns the user that matches {value}.</p>
        <br>

        <h4>Create New User</h4>
        <p>Post http://localhost:4321/users?values=(id, email, password, balance, roleID)</p>
        <p>Response: Creates new user with the field values</p>
        <br>

        <h4>Update User Balance</h4>
        <p>Put http://localhost:4321/users?id=(id)&balance=(value)</p>
        <p>Response: Updates user balance to new value</p>
        <br>

        <h4>Delete User Based on ID:</h4>
        <p>DELETE http://localhost:4321/users?id?id=(id)</p>
        <p>Response: Deletes user based on {id}.</p>
        <br>

        <h3>Sessions Domain</h3>
        <br>
        <h4>Login</h4>
        <p>Post http://localhost:4321/sessions</p>
        <p>Request with JSON validates the user email, password, and role you want to log in as.</p>
        <p>    {
            "email": "toby@gmail.com",
            "password": "password",
            "role": {
                "id": 1,
                "role": "Admin"
            }
        }
    </p>
        <p>Response: Logs in User and returns a welcome message</p>
        <br>

        <h4>Logout</h4>
        <p>Delete http://localhost:4321/sessions</p>
        <p>Response: Logs out User if one is logged in</p>
        <br>

        <h4>Database Schema: 
            Schema</h4>
            <br>
        <h3>Installation</h3>
        <br>
        <h4>User</h4>
        <p>Download Java Runtime Environment at: https://www.oracle.com/java/technologies/javase-jre8-downloads.html</p>
        <p>Download PostgreSQL database at: https://www.postgresql.org/download/</p>
        <p>Download Apache Tomcat version 9 at: https://tomcat.apache.org/download-90.cgi</p>
        <p>Download Postman at: https://www.postman.com/downloads/</p>
        <p>Navigate to http://localhost:8080/users using Postman to begin using API.</p>
        <br>
        <h4>Developer</h4>
        <p>Download and install Java JDK, IDE, Postman, and Maven if needed.</p>
        <p>Install PostreSQL and Tomcat as instructed above for users.</p>
        <p>Be sure to load dependencies from pom.xml file in project directory (Maven will install these automatically).</p>
    </body>
</html>