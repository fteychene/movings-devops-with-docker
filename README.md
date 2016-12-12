# Moving devops with docker
Docker mode swarm showcase using docker lab : `Cadavres Exquis "Swarm edition""`
Original source for full codelab : [https://github.com/CodeStory/lab-docker]

## Infra

This demo need at least 2 machines (3 in the integrated exemple) with docker 1.12+ and a shared network.

### Assisted provisioning
To run the assisted demo you need : Vagrant, tmux, tmuxinator
`tmuxinator run`
This will run the Vagrant up and launch a tmux session that will connect to the 3 nodes.

Vagrant configuration :
 - master : ubuntu/xenial + docker,  192.168.0.50
 - node1 : ubuntu/xenial + docker, 192.168.0.51
 - node2 : ubuntu/xenial + docker, 192.168.0.52

## Start the cluster

Start the cluster (on master) :
`master > docker swarm init --advertise-addr {MASTER_EXTERNAL_IP}`

Join the cluster on each node with the given command on the result of the started cluster on master.

## Launch services
Start the services and a network (on master) :
```
docker network create --driver overlay words
docker service create --name mongo --network words mongo:3.3.15
docker service create --name words --network words --replicas 5 dockerdemos/lab-words-java
docker service create --name dispatcher --network words dockerdemos/lab-words-dispatcher
docker service create --name ui -p 8000:80 --network words dockerdemos/lab-web
```

Update the word service to 20 replicas :
`docker service update --replicas 20 words`

These steps are already prepared in the script `scripts/start_services.sh`
If you used the assisted provisioning, it is available `/srv/scripts/start_services.sh`
