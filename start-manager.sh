#!/bin/bash

echo \> docker run -d --name consul --restart=unless-stopped -h consul -p 8500:8500 progrium/consul -server -bootstrap
read
docker run -d --name consul --restart=unless-stopped -h consul -p 8500:8500 progrium/consul -server -bootstrap

echo
echo
echo

echo \> docker run -d -p 3375:2375 -t -v /var/lib/boot2docker:/certs:ro swarm manage --tlsverify --tlscacert=/certs/ca.pem --tlscert=/certs/server.pem --tlskey=/certs/server-key.pem consul://$(docker-machine ip 
read
docker run -d -p 3375:2375 -t -v /var/lib/boot2docker:/certs:ro swarm manage --tlsverify --tlscacert=/certs/ca.pem --tlscert=/certs/server.pem --tlskey=/certs/server-key.pem consul://$(docker-machine ip 
