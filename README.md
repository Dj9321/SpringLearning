# SpringLearning
Markdown syntax (e.g., # Title for headers, - or * for lists, etc.

# Description
This project contains Spring boot project.
It connects with Postgres SQL.
 * Needs Postgres Server to start. Doesn't start if DB is not started.
 * Just start the Postgres in Docker
 * Port changed to 9090 and has an index.html in static resources folder
 * Enabled all actuator endpoints
 * Added Dependency: Swagger
 * Added Dependency: Gson

#Security
Check in applicaiton.properties file

# Postgres SQL
* Start Via Docker: 
In terminal: sudo docker ps -a // Check all containers
sudo docker start dj-postgres
In Postgres DB Admin: give password
Created a table-mappings.properties to move table mapping to external file

* Table Structure:
Table structure and DDL script should be in resource folder.
* Hostname: 172.17.0.2
* Port: 5432
* Username: postgres
* We used a class that implements PhysicalNamingStrategy to get table names from property files. We can still use @Table to override things.

# Swagger:
Add openapi dependency and no configuration required for this. 
Use url: port changed to 9090) http://localhost:9090/swagger-ui/index.html

# To add: 
* Spring security > further additions
* Spring Session > using different methods
* Spring Cache
* Spring Eureka

# Author
Dheeraj S