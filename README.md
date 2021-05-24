# Examen Técnico - Nisum

## IMPORTANTE

Al ejecutar el proyecto ir a la ruta:

`http://localhost:8081/h2-console`

Y correr los scripts que están en la siguiente ruta en el siguiente orden:

1. `src/main/resources/sql/tables/tables-init.sql`
2. `src/main/resources/sql/data/data-test.sql`

En caso de limpiar la base de datos entonces correr el siguiente script:

`src/main/resources/sql/clean`

## Aplicaciones

1. Swagger - Documentación de API
2. Actuator - Métricas
3. Java Validation - Para las validaciones de correo y password
4. Pruebas unitarias - De repositorio y controlador
5. Log4j2 - Logging
6. Hibernate - ORM
7. H2 Database - Base de datos
8. Spring Security - Json Web Tokens

## Rutas

#### Rest:
`http://localhost:8081/`

`http://localhost:8081/v1/user/login`

#### Métricas:
`http://localhost:8081/actuator`

`http://localhost:8081/actuator/prometheus`

NOTA: Las métricas de prometheus para usarlos en grafana u otro gestor adicional a prometheus

`http://localhost:8081/actuator/metrics/users.login.user`

NOTA: Las métricas de users.login.user solo se activan si has consumido el servicio aunque sea una vez por instancia de jvm

#### Swagger - Documentación de API Rest
`http://localhost:8081/swagger-ui.html`

#### H2 Console
`http://localhost:8081/h2-console`
