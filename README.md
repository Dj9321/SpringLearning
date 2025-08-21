# SpringLearning
Markdown syntax (e.g., # Title for headers, - or * for lists, etc.

# Description
This project contains Spring boot project.
It connects with Postgres SQL.
 * Needs Postgres Server to start.
 * Just start the Postgres in Docker
 * Port changed to 9090 and has an index.html in static resources folder

#Security
Check in applicaiton.properties file

# Postgres SQL
* Start Via Docker: 
In terminal: sudo docker ps -a // Check all containers
sudo docker start dj-postgres
In Postgres DB Admin: give password

* Table Structure:
Table structure and DDL script will be in resource folder.
* Hostname: 172.17.0.2
* Port: 5432
* Username: postgres

# Swagger:
Add openapi dependency and no configuration required for this. 
Use url: http://localhost:8080/swagger-ui/index.html


# Author
Dheeraj S