#!/bin/bash

echo \> docker network create network_sample
read
docker network create network_sample

echo
echo
echo
echo \> docker run -d --name postgres --net network_sample --net-alias postgres -p 5432:5432 -e POSTGRES_PASSWORD=jug postgres
read
docker run -d --name postgres --net network_sample --net-alias postgres -p 5432:5432 -e POSTGRES_PASSWORD=jug postgres

echo
echo
echo

echo \> docker ps
docker ps

echo
echo
echo

echo \> docker run --net network_sample  -p 8080:8080 fteychene/jee:links
read
docker run --net network_sample  -p 8080:8080 fteychene/jee:links


docker rm -f postgres
docker network rm network_sample
