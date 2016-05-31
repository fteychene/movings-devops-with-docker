#!/bin/bash

echo \> mvn clean package
mvn clean package

read

echo
echo
echo
echo \> docker build -t fteychene/jee:dockerfile .
docker build -t fteychene/jee:dockerfile .

read

echo
echo
echo
echo \> docker run -p 8080:8080 fteychene/jee:dockerfile
docker run -p 8080:8080 fteychene/jee:dockerfile
