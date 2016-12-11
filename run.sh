#!/bin/bash

echo "> docker build -t local/curl curl" 
read
docker build -t local/curl --no-cache curl

echo
echo "> docker run local/curl http://www.google.com"
read
docker run local/curl http://www.google.com
