# ITBC-Logger

Logger application where users can be registered, logged in and create logs. Implementation of Rest API 
with Springboot, JPA, Microsoft SQL Server Management Studio database.

## Technologies used in project:
* Java version 19
* Spring Boot 2.7.4
* Microsoft SQL Server Management Studio 18

## Business Requirements, MVP (Minimum Viable Product)

### User
1. The user can register
   * Username, email, password
2. The user can retrieve his "key"
   * Using username, email, and password
3. The user can enter the log 
   * Message
   * Log type (ERROR, WARNING, INFO)
   * Created date
4. User can search logs
   * By time (from - to)
   * By text in the log message
   * By log type (ERROR, WARNING, INFO)
   * Or any combination

### Admin
1. There is an admin account (or admin key)
2. Admin can see all users
3. Admin can see the number of logs for each user
4. The admin can change the user's password at his request

## Endpoints
  1. Register
        * HTTP Method: POST
        * Endpoint URL: /api/clients/register
        * Request body:
        
              {
              "username": "string",
              "password": "string",
              "email": "string"
              }
        * Responses:
            * 201 - Registered
            * 400 - Bad Request
               * email must be valid
               * username at least 3 characters
               * password at least 8 characters and one letter and one number
            * 409 - Conflict
               * username already exists
               * email already exists
  2. Login
        * HTTP Method: POST
        * Endpoint URL: /api/clients/login
        * Request body:
       
              {
              "account": "string", // email or username
              "password": "string"
              }
        * Responses:
             * 200 - OK
             
              {
              "token": "string" // uuid* || JWT || username
              }
             * 400 - Bad Request
               * Email/Username or password incorrect
  3. Create log
        * HTTP Method: POST
        * Endpoint URL: /api/logs/create
        * Request body:
         
              {
              "message": "string",
              "logType": 0
              }
        * Request headers:
          * Authorization - token
        * Responses:
          * 201 - Created
          * 400 - Bad Request
             * Incorrect logType
          * 401 - Unauthorized
             * Incorrect token
          * 413 - Payload too large
             * Message should be less than 1024

  4. Search logs
        * HTTP Method: GET
        * Endpoint URL: /api/logs/search
        * Request params:
          * dateFrom - date
          * dateTo - date
          * message - string
          * logType - int
        * Request headers:
          * Authorization - token
        * Responses:
          * 200 - OK

                [
                {
                "message": "string",
                "logType": 0,
                "createdDate": "date"
                }
                ]
          * 400 - Bad request
            * Invalid dates
            * Invalid logType
          * 401 - Unauthorized
            * Incorrect token

Admin
  1. Get all clients
        * HTTP Method: GET
        * Endpoint URL: /api/clients
        * Request headers:
          * Authorization - token (Admin token)
        * Responses:
          * 200 - OK
          
                [
                {
                "id": "uuid",
                "username": "string",
                "email": "string",
                "logCount": 0
                }
                ]
          * 401 - Unauthorized
            * Correct token, but not admin
          * 403 - Forbidden
            * Incorrect token
  2. Change client password
        * HTTP Method: PATCH
        * Endpoint URL: /api/clients/{clientId}/reset-password
        * Request body:

                {
                "password": "string"
                }
        * Request headers:
          * Authorization - token (Admin token)
        * Responses:
          * 204 - No content
          * 401 - Unauthorized
            * Correct token, but not admin
          * 403 - Forbidden
            * Incorrect token

## Installation and settup:
* Open project with Java IDE
* Open your SQL server
* In java project on the path (src/main/resources/static/SQLQuery-Creating DB and Tables.sql) you can create Database and tables
* You will need to set a username and password for created database and set it up in src/main/resources/application.properties
* After that application is ready to run
