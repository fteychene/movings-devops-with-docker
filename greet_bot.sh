#!/bin/bash

while :
do
	echo $(curl http://localhost:8080/rest/services/greet/Francois)
	sleep 1
	echo $(curl http://localhost:8080/rest/services/greet/Romain)
	sleep 1
	echo $(curl http://localhost:8080/rest/services/greet/Maxime)
	sleep 1
	echo $(curl http://localhost:8080/rest/services/greet/John)
	sleep 1
done


