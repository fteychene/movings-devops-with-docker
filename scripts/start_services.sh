#!/bin/bash

NETWORK_COMMAND="docker network create --driver overlay words"
echo "> $NETWORK_COMMAND"
$NETWORK_COMMAND

DB_COMMAND="docker service create --name mongo --network words mongo:3.3.15"
echo "> $DB_COMMAND"
$DB_COMMAND

WORDS_COMMAND="docker service create --name words --network words --replicas 5 dockerdemos/lab-words-java"
echo "> $WORDS_COMMAND"
$WORDS_COMMAND

DISPATCHER_COMMAND="docker service create --name dispatcher --network words dockerdemos/lab-words-dispatcher"
echo "> $DISPATCHER_COMMAND"
$DISPATCHER_COMMAND

WEB_COMMAND="docker service create --name ui -p 8000:80 --network words dockerdemos/lab-web"
echo "> $WEB_COMMAND"
$WEB_COMMAND

WATCH_SERVICES="watch docker service ls"
echo "> $WATCH_SERVICES"
$WATCH_SERVICES

UPDATE_WORDS_COMMAND="docker service update --replicas 20 words"
echo "> $UPDATE_WORDS_COMMAND"
read
$UPDATE_WORDS_COMMAND
