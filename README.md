
###
1. In cmd run: docker-compose up -d
2. Need to register postgres connector for <i> inventory.customers </i> table to debezium by sending a POST request to <link> http://localhost:8083/connectors </link> with this payload:
```json
 {
    "name": "inventory-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "database.hostname": "postgres",
      "database.port": "5432",
      "database.user": "postgres",
      "database.password": "postgres",
      "database.dbname" : "postgres",
      "database.server.name": "dbserver1",
     "table.include.list": "inventory.customers"
    }
  }
```
Check if it was successfully registered by sending a GET request to <link> http://localhost:8083/connectors </link>.
It should return an array of registered connectors:
```json
[
    "inventory-connector"
]
```
3. Using <i> localhost:8080/customers </i> endpoints: create, update, delete value from customers table.
Each time any of these actions are done, debezium will produce a Kafka message, which in turn will be consumed by KafkaConsumer
and printed out to console.