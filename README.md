# App backend - Adopt a Pet

# Overview
App backend - Adopt a Pet

## Tech stack

- Java 17
- Gradle
- H2 database
- Spring Boot 3
- Spring Webflux
- Spring Data JPA

## Integrations

- https://api.thedogapi.com
- https://api.thecatapi.com

## Instructions

1. Install Java/JDK 17 (https://adoptium.net/) - mark the option to set o JAVA_HOME, if possible.

2. If Java/JDK 17 is installed, verify JAVA_HOME.

3. git clone this repository

4. In the root folder of project, open terminal and run ```./gradlew bootRun```.

5. Open  http://localhost:8080/swagger-ui.html

## Test

```
curl -X POST --location "http://localhost:8080/api/pets/indexing"
```

```
curl -X POST --location "http://localhost:8080/api/pets/search"
```

```
curl -X POST --location "http://localhost:8080/api/pets/search?term=dog+1"
```

```
curl -X PUT --location "http://localhost:8080/api/pets/1/available"
```

```
curl -X PUT --location "http://localhost:8080/api/pets/1/adopt"
```

```
curl -X POST --location "http://localhost:8080/api/pets/search?status=Adopt"
```


## License
MIT

The code in this repository is covered by the included license.