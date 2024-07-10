# Drone Dispatch Service
This service manages drones and their associated medications for delivery.

Prerequisites
* Docker
* Docker Compose
* Java 17
* Gradle

## Getting Started

```js
git clone git@github.com:Kalunge/drone-dispatch.git
cd drone-dispatc
```

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
[Here](https://github.com/Kalunge/drone-dispatch/blob/main/drone_service.postman_collection.json) is a postman collection to easily locate the endpoints. The collection can be found in the root of the app directory as well

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

* Response

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
### Load Medications to Drone
### URL: POST /drones/{droneId}/medications
* Request:
```js
[
  {
    "name": "Medication A",
    "weight": 50,
    "code": "MEDA001",
    "image": "base64imagestring"
  },
  {
    "name": "Medication B",
    "weight": 60,
    "code": "MEDB002",
    "image": "base64imagestring"
  }
]

```
* Response: No content (204 No Content)
###  Get Loaded Medications of a Drone
### URL: GET /drones/{droneId}/medications
* Response

Response:
```js
[
  {
    "id": 1,
    "name": "Medication A",
    "weight": 50,
    "code": "MEDA001",
    "image": "base64imagestring"
  },
  {
    "id": 2,
    "name": "Medication B",
    "weight": 60,
    "code": "MEDB002",
    "image": "base64imagestring"
  }
]

```


Get Available Drones
URL: GET /drones/available
Response

```js
[
  {
    "id": 1,
    "serialNumber": "DRONE1",
    "model": "MIDDLEWEIGHT",
    "weightLimit": 400,
    "batteryCapacity": 80,
    "state": "IDLE"
  },
  {
    "id": 2,
    "serialNumber": "DRONE2",
    "model": "HEAVYWEIGHT",
    "weightLimit": 500,
    "batteryCapacity": 60,
    "state": "IDLE"
  }
]

```

### Get Drone Battery Level
### URL: GET /drones/{droneId}/battery
* Response:
```js
80
```