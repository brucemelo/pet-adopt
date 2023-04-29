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

2. In terminal, clone this repository with ```git clone``` command.
   
    Example, ```git clone https://github.com/brucemelo/pet-adopt.git```

3. In terminal, go to the project folder ```cd pet-adopt/``` command.

4. In the root folder of project, open terminal and run ```./gradlew bootRun```.

## Test

1. Open new window in terminal and test with commands above.

```
curl -X POST --location "http://localhost:8080/api/pets/indexing"
```

```
curl -X GET --location "http://localhost:8080/api/pets/search"
```

```
curl -X GET --location "http://localhost:8080/api/pets/search?term=dog+1"
```

```
curl -X PUT --location "http://localhost:8080/api/pets/1/available"
```

```
curl -X PUT --location "http://localhost:8080/api/pets/1/adopt"
```

```
curl -X GET --location "http://localhost:8080/api/pets/search?status=Available"
```

```
curl -X GET --location "http://localhost:8080/api/pets/search?status=Adopt"
```


## License
MIT

The code in this repository is covered by the included license.