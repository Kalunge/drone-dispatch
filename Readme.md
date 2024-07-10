# Drone Dispatch Service
This service manages drones and their associated medications for delivery.
Prerequisites
* Docker
* Docker Compose
* Java 11
* Gradle

## Getting Started
### Start the Database (PostgreSQL)
1. Make sure Docker is installed. Run the following command in the project directory to start the PostgreSQL database using Docker Compose:
```
docker-compose up -d
```
This will start a PostgreSQL database container named drone_service_db on port 5435.
2. Run the Spring Boot Application
Navigate to the project directory and run the Spring Boot application using Gradle:
```
./gradlew bootRun
```
The application will start on port 8080 by default.
3. Accessing the Application
Once the application is running, you can access the following endpoints:

Register Drone
http://localhost:8080/api/drones: POST 

Reqeust Body
```js
{
  "serialNumber": "DRONE1",
  "model": "MIDDLEWEIGHT",
  "weightLimit": 400,
  "batteryCapacity": 80,
  "state": "IDLE"
}

```

Response

```js
{
  "id": 1,
  "serialNumber": "DRONE1",
  "model": "MIDDLEWEIGHT",
  "weightLimit": 400,
  "batteryCapacity": 80,
  "state": "IDLE"
}

```
Load Medications to Drone
URL: POST /drones/{droneId}/medications
Request:
