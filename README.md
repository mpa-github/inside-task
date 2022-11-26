# inside-task repository

 _The Java Spring Boot application manages information about users and their messages._<br/>
 _Implemented authentication and authorization functionality using Spring Web / Security._<br/>
 _All data stores in PosgreSQL Database._
___
Java version "11.0.3"<br/>
___
### How to use?
* Clone repository.
* Start PostgreSQL Server and create empty database.<br/>
Spring connection property: "jdbc:postgresql://localhost:5432/users_db"
* Start the App. All initial data will be loaded to the database.

API endpoints (http://localhost:3000):<br/>
* POST api/auth/signup (user registration)<br/>
accepts JSON {"name": "user name", "password": "user password"}
* POST api/user/message (receiving user's message and saving in db)<br/>
accepts JSON {"name": "user name", "message": "user message"}<br/>
accepts JSON {"name": "user name", "message": "history N"}<br/> 
("history" - reserved word, N - positive integer value)