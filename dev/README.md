# Israel's Readme

Version: 1.0.0

This is a REST API, developed using **Java**, **Maven**, **JPA**, **Flyway** and **Docker**.
The database used is PostgreSQL running on Docker.

This API implements a CRUD for a clients with an health issues list.

The database creation script can be found in **"/src/main/resources/db/migration/V001__init.sql"**. Use flyway to migrate.
Docker must be installed. To start the migration, you must first run the command docker-compose the flyway migrate.


# Setup

- The PostgrSQL version configured to 13.4, using POSTGRES_USER=postgres POSTGRES_PASSWORD=olisaude in port 5432.
    - Run "**docker-compose up -d**" to create / run this database.
    - The pgAdmin version configured to 4 version. You can acces in http://localhost:9080/browser/ using "PGADMIN_DEFAULT_EMAIL=olisaude@olisaude.com.br" and  "PGADMIN_DEFAULT_PASSWORD=olisaude". To start, you need to create a new server with name: olisaude@olisaude.com.br
    - host: tad
    - port: 5432
    - then, you must to create database "israel-dev-test"
- The project uses Flyway to control version of database.
