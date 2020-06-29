# Internet-Shop
![Internet-shop](/images/shopping.png)

## Table of contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#for_developer)
* [Authors](#authors)

# <a name="purpose"></a>Project purpose
This project is a simple version of internet-shop. 

It reproduces the main functions and opportunities 
of online shopping.
<hr>

#### The main functions of Internet-Shop: 
* Registration and log in forms
* Bucket and order services
* Two roles: User and Admin

### Depending on the role you will have such opportunities:

#### Functions available for all users:
* log in
* log out
* register
* view the main menu
* view the products

#### Functions available only for users with a USER-role:
* add products to your bucket
* delete products from your bucket
* make orders
* view the list of all your orders
* view the details of your order

#### Functions available only for users with a ADMIN-role:
* add products to the store
* delete products from the store
* view the list of users
* delete users from the store

<hr>
In order to add some security and give the access to appropriate resources,
depending on the role,
authentication and authorization filters were implemented. 

In addition, DAO, Service layers, Servlets and JSP pages are absent.

DAO layer has two implementations which gives you a possibility to test it both
on inner storage and on storage based on MySQL DataBase.
Hashing and salting of user's password are implemented, so nobody has access to your secret data.

# <a name="structure"></a>Project structure
* Java 13
* Maven 4.0.0
* javax.servlet 3.1.0
* jstl 1.2
* log4j 1.2.17
* maven-checkstyle-plugin
* mysql-connector-java 8.0.15

<hr> 

# <a name="for_developer"></a>For developer
#### To run this project you need to install:
* [Java 13](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html)
* [Tomcat](https://tomcat.apache.org/download-90.cgi)
* [MySQL 8 ](https://www.mysql.com/downloads/)

#### After installation, you should do the following:
Add this project to your IDE as Maven project.

Configure Tomcat : 
* Add artifact
* Add Java SDK 13

Change a path in src.main.java.resources.log4j.properties. It has to reach your logFile.


#### If you want to test it using SQL DataBase, you should: 
* Create a schema "internet_shop" in any SQL database.

* Use file src.main.java.resources.init_db.sql to create all the tables required by this app.

* At src.main.java.mate.academy.shop.util.ConnectionUtil class use username and password for your DB to create a Connection.

![Configure_db](/images/configureDB.png)

* Configure the dependency for the connector to your DBMS. You should write in your DBMS,
 establish a connection, having downloaded the driver for it and specify the version of the DBMS.

![Configuring_pom](/images/pom.png)

#### If you want to test it using inner storage, you should:
* Remove the annotations @Dao from src.main.java.mate.academy.shop.dao.jdbc
and add to src.main.java.mate.academy.shop.dao.impl

The example of how to do it is shown below

![Configure_DAO](/images/configureDAO.png)


Run the project and register.

If it's your first visit, click on Inject Data.
Then, by default two users will be generated.
 
The first one - with a USER role (login = steve, password = 1) 
The second one – with an ADMIN role (login = admin, password = 2). 
<hr>

# <a name="authors"></a>Authors
* [NastiaFasova](https://github.com/NastiaFasova)
