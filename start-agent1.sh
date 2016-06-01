#!/bin/bash

docker-machine start agent1

docker-machine regenerate-certs -f  agent1

eval $(docker-machine env agent1)
