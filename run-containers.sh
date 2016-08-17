#!/bin/bash
clear


echo Running hello-world | figlet | lolcat

echo
echo
echo \> docker run hello-world
read
docker run hello-world

echo
echo
echo Running whoami | figlet | lolcat
echo

echo \> docker run -p 8000:80 emilevauge/whoami
read

docker run -p 8000:80 emilevauge/whoami

echo
echo
echo
echo Running postgres | figlet | lolcat
echo

echo \> docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=jug postgres
read

docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=jug postgres

echo
echo
echo
echo \> docker ps
read
docker ps

echo
echo
echo
echo \> psql -h localhost -U postgres -W
read
psql -h localhost -U postgres -W

docker rm -f postgres
