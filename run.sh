#!/bin/bash

echo \> mvn clean package
read
mvn clean package

echo
echo
echo
echo \> docker build -t fteychene/jee:dockerfile .
read
docker build -t fteychene/jee:dockerfile .


echo
echo
echo
echo \> docker run -p 8080:8080 fteychene/jee:dockerfile
read
docker run -p 8080:8080 fteychene/jee:dockerfile

echo
echo
echo
echo \> docker run -p 8080:8080 fteychene/jee:dockerfile
read
docker run -p 8080:8080 fteychene/jee:dockerfile
