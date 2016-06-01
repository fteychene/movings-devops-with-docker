#!/bin/bash

eval $(docker-machine env manager)

echo \> docker run -d -p 3375:2375 -t -v /var/lib/boot2docker:/certs:ro swarm manage --tlsverify --tlscacert=/certs/ca.pem --tlscert=/certs/server.pem --tlskey=/certs/server-key.pem consul://$(docker-machine ip manager):8500
docker run -d -p 3375:2375 -t -v /var/lib/boot2docker:/certs:ro swarm manage --tlsverify --tlscacert=/certs/ca.pem --tlscert=/certs/server.pem --tlskey=/certs/server-key.pem consul://$(docker-machine ip manager):8500
