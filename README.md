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
````Java
fdfSettings.DB_ROOT_USER = "root";
fdfSettings.DB_ROOT_PASSWORD = "";

````
    MySQL settings Version 5.7 only!!!!!!!! (no support for version 8)
    fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
    fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;
````

````SQL
mysql> show databases
````
You should see that there is a new database, called 'mapcushion'
