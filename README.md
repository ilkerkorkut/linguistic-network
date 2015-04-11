# Linguistic Network
To create nodes, and edges between facebook comments' words. Datacollector collects facebook comments and stores in the redis set. After all linguistic-network creates sequential links between nodes or creates mesh network(n*n-1/2) stores on postgresql.

## Prerequisites

* JDK 1.7 or greater
* Redis
* PostgreSql
* Facebook AccessToken (for Datacollector)
* Gephi

##### TODOS
* Write tests
* Write comments