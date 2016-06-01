#!/bin/bash

echo \> mvn clean package
mvn clean package

echo
echo
echo 

echo \> docker build -t fteychene/jee:links .
read
docker build -t fteychene/jee:links .


echo
echo
echo
echo \> docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=jug postgres
read
docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=jug postgres

echo
echo
echo

echo \> docker ps
docker ps

echo
echo
echo

echo \> docker run --link postgres -p 8080:8080 fteychene/jee:links
read
docker run --link postgres -p 8080:8080 fteychene/jee:links

echo \> docker run --link postgres -p 8080:8080 fteychene/jee:links
read
docker run --link postgres -p 8080:8080 fteychene/jee:links


docker rm -f postgres
