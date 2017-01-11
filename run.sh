#!/bin/bash

echo "> mvn clean package"
read
mvn package

echo
echo "> docker-compose up --build"
read
tmux split-window -v
docker-compose up --build


docker-compose down

