# image-search-website-api
Use java springboot

# How to run
* The project is using springboot2, so you need to install jdk1.8 or above, and you need to install docker
* Pull the image
```bash
docker pull public.ecr.aws/d2x9y6a2/wy-usage:1.0
```
* Run mirror, port mapped to local port 8990

* Please change the datasource in application.yml to your database parameter

* The database file is in src/resources/db/migration/V1.0.sql

* Run Application to start the project