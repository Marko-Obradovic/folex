# Folex Order Service

The Order Service provides a Restful interface for the orders within the Folex System. The workflow uses it to maintain the state of the orders.

## Building

To build the service run:

```
mvn clean package
```

## Configuration

The Order Service must be configured with database details. You will need to know the database hostname, the port number, the name of the database, the username and the password.

The configuration values can be set as environment variables using the Spring Boot convention.

| Environment Variable       | Value                                                                      |
|----------------------------|----------------------------------------------------------------------------|
| SPRING_DATASOURCE_URL      | "jdbc:postgresql://${database hostname}:${database port}/${database name}" |
| SPRING_DATASOURCE_USERNAME | ${database user}                                                           |
| SPRING_DATASOURCE_PASSWORD | ${database password}                                                       |

If you run the service with a database other than Postgres, remember to change the URL to use the format the database driver expects.

