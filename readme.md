# Best Matched Restaurants
This application is an API with functionality to search for restaurants based on specific criteria.

Best used alongside the [frontend counterpart](https://github.com/billybrianm/best-matched-restaurants-bf-fe).

## Features
- Endpoint ```GET /restaurants``` to list all restaurants
  - Following parameters can be used to filter: ```name, customerRating, distance, price, cuisineId```.
- Endpoint ```GET /cuisines``` to list all cuisines

## Requirements
- Java 11
- Maven

## Building
```mvn clean install```

## Running
```java -jar target/best-matched-restaurants-bf.jar```

## Testing
```mvn test```

## Assumptions
- This project was created as a REST API since where were no restrictions and I wanted to create a fully fledged system with backend and frontend communications
- No security is necessary for the scope of this project
- No need for bootstrapping or setting up docker
