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

# API

## CLIENT API

The Client API has 6 Endpoints:
 - [POST] create client 
 - [GET] List all clients        
 - [GET] Get client by id
 - [GET] List the 10 client with the highest level of health problems
 - [PUT] Update client by id
 - [DELETE] Delete client by id

  
### Endpoints
 
#### [POST] Create new client

Receives a JSON with client and save the client on database.
  
##### Path: 

>   /cliente/create

 
##### Validations:

 - Name and Surname must be greater than 2 letters
 - Gender must be "masculino" or "feminino"
 - Birth Date must be in "dd/MM/yyyy" format.
 
##### Example:

```
{
    "name": "João",
    "surname": "da Silva",
    "birthDate": "13/12/1987",
    "gender": "masculino"
}

```


##### Return

Will return a JSON with created client or error message.

##### HTTP Status return:

* 201: Success on client creation
* 400: Invalid request parameters
* 500: Server internal error

#### [GET] List all clients

##### Path: 

>    /cliente/todos

Will return a JSON array with all clients


 
#### [GET] Get client by id

##### Path:

>    /cliente/{id}
    
##### Return

Will return a JSON with the client or error .

##### HTTP Status return:

* 200: Success on list clients
* 404: Not found - no clients saved on database with the received ID
* 500: Server internal error

#### [GET] List the 10 client with the highest level of health problems

##### Path:

>    /cliente/maior-risco
    
##### Return

Will return a JSON with the 10 clients with the highest level of health problems or error.

##### HTTP Status return:

* 200: Success on list clients
* 500: Server internal error


 
#### [PUT] Update client by id

##### Path:

>    /cliente/{id}
    
##### Return

Will return a JSON with the updated client or error message.

##### HTTP Status return:

* 200: Success on update
* 404: Not found - no clients saved on database with the received ID
* 500: Server internal error

##### Example:

```
{
    "name": "João",
    "surname": "José da Silva",
    "birthDate": "13/12/1989",
    "gender": "masculino"
}

```

```

{
    "id": 23,
    "name": "João",
    "surname": "José da Silva",
    "birthDate": "1989-12-13T00:00:00-03:00",
    "gender": "masculino",
    "registrationDate": "2022-01-30T15:58:32.150769-02:00",
    "lastUpdate": "2022-01-31T23:50:03.0333749-02:00"
}

```


#### [DELETE] Delete client by id

##### Path:

>    /cliente/{id}
    
##### Return

Will return a JSON with the deleted client or error message.

##### HTTP Status return:

* 200: Success on delete
* 404: Not found - no clients saved on database with the received ID
* 500: Server internal error




---

## HealthIssuesAPI

The HealthIssues API have 6 Endpoints:
 - [POST] create HealthIssues
 - [GET] List all HealthIssues 
 - [GET] List all HealthIssues by client id        
 - [GET] Get an HealthIssues by id
 - [PUT] Update an HealthIssues by id
 - [DELETE] Delete an HealthIssues by id
  
### Endpoints
 



#### [POST] Create new HealthIssues for a client

Receives a JSON with HealthIssues and save the HealthIssues on database.

##### Path

>    /problemas-de-saude/create

##### Example

```
{
    "name": "Diabetes",
    "levelDisease": 2,
    "userId": 7
}
```

##### Return

Will return a JSON with created HealthIssues or error message.
 
##### HTTP Status return:

*  201: Success on HealthIssues creation
*  400: Invalid request parameters
*  404: Client id not found
*  500: Server internal error


#### [GET] List all HealthIssues

##### Path

>    /problemas-de-saude/todas
    
##### Return

Will return a JSON with all HealthIssues.
 
##### HTTP Status return:

*  200: Success on list HealthIssues
*  500: Server internal error

#### [GET] List all HealthIssues by client

##### Path

>    /problemas-de-saude/todas/{id}
    
##### Return

Will return a JSON with all HealthIssues by client.
 
##### HTTP Status return:

*  200: Success on list HealthIssues
*  404: Client id not found
*  500: Server internal error


#### [GET] Get HealthIssues by id

##### Path

>    /problemas-de-saude/{id}
    
##### Return

Will return a JSON with the HealthIssues by id.
 
##### HTTP Status return:

*  200: Success on list HealthIssues
*  404: HealthIssues id not found
*  500: Server internal error



#### [PUT] Update an HealthIssues by id 

Receive a HealthIssues. Update the HealthIssues and return the created HealthIssues as JSON.

##### Path

>    /problemas-de-saude/{id}
    
##### Return

Will return a JSON with HealthIssues, or error message.
 
##### HTTP Status return:

*  200: HealthIssues found
*  404: HealthIssues Not found
*  500: Server internal error


#### [DELETE] Delete an HealthIssues by id

Receive a HealthIssues id and return the deleted HealthIssues as JSON.

##### Path

>    /problemas-de-saude/{id}
    
##### Return

Will return a success or error message.
 
##### HTTP Status return:

* 200: Success on update
* 404: HealthIssues Not found
* 500: Server internal error


# License

GNU General Public License, version 3 (GPLv3)