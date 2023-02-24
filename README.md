# digital-wallet

Basic digital wallet made using spring boot and basic html/css/js.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 7.0+](https://services.gradle.org/distributions/gradle-7.6-bin.zip)
- [Intellij IDEA](https://www.jetbrains.com/idea/download/#section=windows)
- [Postgres](https://www.postgresql.org/download/)
- [Git Bash](https://git-scm.com/downloads)

All of them except postgres will be downloaded and configured automatically if you're using intellij to run the project, so you don't have to worry :P

## Postgres Setup

Download the postgres engine from [here](https://www.postgresql.org/download/).
This will run postgres on port 5432.
make sure to memorize the password you set for the postgres user as it will be used in the shell script later.

## Source Code Setup

Before running the application you must set up a postgres database instance and open the project from intellij once.
Please clone this [repository](https://github.com/MohammadDarsa/digital-wallet) and open it in intellij.

## Running The Application

Follow the steps:

1. Open a new git bash session and navigate to the project directory (using cd command).
2. run `sh ./run.sh` command from the git bash session.

For any further assistance please contact us, and we'll set a meeting to help you out :)

## Available Data:

To log in with an existing user use:
- username: admin
- password: admintest

The reference ID of this user is: fb85f093-dc4c-4812-88e9-33b77ee9e0fc (this is used in money transfer)

or 

- username: admin2
- password: admintest

The reference ID of this user is: 43758877-503c-471e-be04-7611476ec11e (this is used in money transfer)

To top up your wallet use the following card details:

for USD currency:
- pan: 1122334455667788
- cvv: 123
- expiry: 01/24

for LBP currency:
- pan: 1212121212121212
- cvv: 123
- expiry: 01/24
