Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"

  config.vm.define "master", primary: true do |master|
    master.vm.provider :virtualbox do |vb|
    end
    master.vm.box = "ubuntu/xenial64"

    master.vm.synced_folder "scripts/", "/srv/scripts"
    master.vm.network :private_network, ip: "192.168.0.50"
    master.vm.hostname = "swarm-master"
    master.vm.provision :docker
  end

  config.vm.define "node1" do |node1|
    node1.vm.provider :virtualbox do |vb|
    end
    node1.vm.box = "ubuntu/xenial64"
    node1.vm.network :private_network, ip: "192.168.0.51"
    node1.vm.hostname = "swarm-node1"
    node1.vm.provision :docker
  end

  config.vm.define "node2" do |node2|
    node2.vm.provider :virtualbox do |vb|
    end
    node2.vm.box = "ubuntu/xenial64"
    node2.vm.network :private_network, ip: "192.168.0.52"
    node2.vm.hostname = "swarm-node2"
    node2.vm.provision :docker
  end

end
