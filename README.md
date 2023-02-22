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
This will run postgres on port 5432.

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

Follow the steps:

1. Open a new command line session (cmd) **As Adminstrator** and navigate to the project directory (using cd command).
2. run `sh ./run.sh` command from the command line.

