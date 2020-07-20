# Dstagram backend

## How to run
```bash
// Run Database
docker run --name mysql-db -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=landvibe \
    -e MYSQL_DATABASE=dstagram \
    -e MYSQL_USER=admin \
    -e MYSQL_PASSWORD=admin \
    -d mysql
```

```bash
// Run Spring
./gradlew bootRun
```