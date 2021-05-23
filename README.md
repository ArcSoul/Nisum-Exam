# Examen Técnico - Nisum

## Rutas

#### Rest:
`http://localhost:8081/`
`http://localhost:8081/v1/user/login`

#### Métricas:
`http://localhost:8081/actuator`

#### Swagger - Documentación de API Rest
`http://localhost:8081/swagger-ui.html`

#### H2 Console
`http://localhost:8081/h2-console`

## SQL

Correr los scripts que estan en la siguiente ruta en el siguiente orden:

1. `src/main/resources/sql/tables/tables-init.sql`
2. `src/main/resources/sql/data/data-test.sql`
   
En caso de limpiar la base de datos entonces correr el siguiente script:

`src/main/resources/sql/clean`
