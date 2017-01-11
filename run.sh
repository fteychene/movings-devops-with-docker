#!/bin/bash

echo "> mvn clean package"
read
mvn package

echo
echo "> docker-compose up --build"
read
docker-compose up --build

docker-compose down
