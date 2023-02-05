# digital-wallet

Basic digital wallet made using spring boot and thymleaf.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 7.0+](https://services.gradle.org/distributions/gradle-7.6-bin.zip)

Both of them will be downloaded and configured automatically if you're using intellij to run the project, so you don't have to worry :P

## Running the application locally

Before running the application you must set up a postgres database instance

### Postgres setup

You can do this in 2 ways:

The first one is to download the postgres engine from [here](https://www.postgresql.org/download/).
This will run postgres on port 5432. If you use this method make sure to set the username to `pepsi` and password to `cola`, or you can change the username and password from `application.properties`

**(Recommended but requires more RAM)**
The second way is to download and run [docker](https://www.docker.com/products/docker-desktop/), then use the command line to run
`docker/docker-compose-postgres.yml`.

in cmd run from the project directory:

```shell
docker-compose -f .\docker\docker-compose-postgres.yml up
```

This will do the same thing as the first method, but using docker. This is more handy.

### Spring profiles

Profiles are a way to manage different spring configurations, in our case we have 2 different configurations

- local: creates an in memory database (without postgres), good for testing.
- dev: uses the postgres database.

To switch between profiles go to `src/main/resources/application.properties`
and change `spring.profiles.active` to whatever profile you want to set active.

### Running the application

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `ul.info.digitalwallet.DigitalWalletApplication` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/) like so:

```shell
./gradlew bootRun
```

After running the application make sure to execute the script `roles.sql` present in the sql directory, this will add the roles for authorization. Make sure to select the right database.

### Bank Backend
The bank is ready, you can run it on port 8081 by just running the application. No APIs are called from the bank yet.