#!/bin/bash

docker-machine start agent2

docker-machine regenerate-certs -f agent2

eval $(docker-machine env agent2)
