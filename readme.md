### Getting start

1. run docker mysql

```
$ docker network create demo-network
$ docker pull mysql # download mysql image
$ docker run --name mysql --network demo-network -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql:latest
```

2. create schema

3. docker image build 

```
./gradlew clean build 
docker build . -t demo
docker run --name server --network demo-network -p 8080:8080 demo
```