### Getting start

1. run docker mysql

```
$ docker pull mysql # download mysql image
$ docker run --name mysql -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:latest #
```

2. create schema