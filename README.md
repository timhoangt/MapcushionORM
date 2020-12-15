<h1>Mapcushion</h1>
<h3>4DFLib Mapcushion application!!!</h3>
This is an Mapcushion application using 4dflib (https://github.com/briangormanly/4dflib) to manage all application data over time and it is an ORM tool to boot, abstracting away your database so you can concentrate on the really important things, like coding!

<h3>Instructions</h3>
<strong>prerequisites</strong>
<ul>
<li>MySQL is used</li>
<li>Java 8 JRE is available</li>
<li>You have Git and Gradle installed</li>
</ul>

<h3>Database Configuration</h3>

Root username and password:
```Java
fdfSettings.DB_ROOT_USER = "root";
fdfSettings.DB_ROOT_PASSWORD = "";
```

MySQL settings Version 5.7 only!!!!!!!! (no support for version 8)
```Java
    fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
    fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;
```

<h3>Run the Mapcushion ORM application<h3> 
go into the main folder and performing the following in your Command Line.
```
gradle run
```
<h3>Database Configuration</h3>

```SQL
mysql> SHOW databases;
```
You should see that there is a new database, called 'mapcushion'.

If you change the code to perform different queries or create new tables and relations, be sure to delete the mapcushion database.
If you do not delete the database, it will add the same data twice.
```SQL
mysql> DROP DATABASE mapcushion;
```

<h3>Data in Mapcushion Database</h3>
There are pauses in the modification of data so that we get different timestamps later on when viewing history.

<h5>2 tenants<h5>

<h5>Both tenants get pre-defined system roles and 1 tenant has at least 3 custom roles<h5>

<h5>10 users per tenant where 2 users exist in both tenants<h5>

-8 of the users in each client have id information recorded

-30 assignments of roles to users, some users have more than 1 role assigned

-5 user records are modified (name change, change of address, phone number, etc) after they are created

<h5>20 Beacons per tenant<h5>

<h5>2 location per tenant<h5>

-1 location must has > 1 floor

-10 beacons should be assigned locations on floors

<h5>8 check ins of users and at least 1 in each tenant<h5>

-at least one checkin in each of the statuses

-Changes to status (example: moving from Just Arrived to checked in)

<h5>20 reported beacons by user devices<h5>

<h3>Queries for Mapcushion Database</h3>

-all tenants

-all users for each tenant

-2 users and their associated roles

-5 users that were modified. Outputing the original and modified records including the time range represented by each

-all checkins by tenant

-for a checkin that has gone through the complete cycle show the history of the events

-all the beacons located on floors in buildings for tenants