#Servlet-JSP-JSTL-PostgreSQL

Hi everyone,

In this project, i just made a CRUD system using Java Servlet for the backend, JSP + JSTL for the frontend and include the PostgreSQL for the database system.

#How to Install

##Setting Database

1. Install the latest of PostgreSQL
2. Open the psql
3. Create database BlibliMovies (Syntax: create database bliblimovies;)
4. Close the psql
5. Open the psql again as database = bliblimovies
6. Copy all the text at the files/bliblimovies.sql
7. Run
8. Database successfully installed

##Setting IntellIJ

1. Open with IntellIJ (such as import or open) with BlibliMovies2 as root folder
2. Change the configuration in BlibliMovies2/src/config.properties with your user, password and url of your database
3. Just run in with Tomcat that include in files

###FAQ

1. Q: If port is unavalaible
A: Change the port at tomcat configuration http port as 8089 or smth and jmx port with 1089 or smth

2. Q: If driver can't access driver of the database
A: Copy the postgresql.jar file to the Tomcat lib

##Good Luck ;)