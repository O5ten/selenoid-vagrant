# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.box_download_insecure = true

  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 4444, host: 4444

  config.vm.provider :virtualbox do |vb|
      vb.memory = "1024"
  end

  config.vm.provision "shell", inline: <<-SHELL
     sudo apt-get update
     sudo apt-get -y install \
       linux-image-extra-$(uname -r) \
       linux-image-extra-virtual
     sudo apt-get -y install \
       apt-transport-https \
       ca-certificates \
       curl \
       software-properties-common
     curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
       sudo apt-key fingerprint 0EBFCD88
       sudo add-apt-repository \
       "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
       $(lsb_release -cs) \
       stable"
     sudo apt-get update
     sudo apt-get -y install docker-ce
     docker run --rm                                   \
        -v /var/run/docker.sock:/var/run/docker.sock    \
        -v ${HOME}:/root                                \
        -e OVERRIDE_HOME=${HOME}                        \
        aerokube/cm:latest-release selenoid start       \
        --vnc --tmpfs 128 --last-versions 2
     docker run -d --name selenoid-ui  \
        --link selenoid                 \
        -p 8080:8080                    \
        aerokube/selenoid-ui --selenoid-uri=http://selenoid:4444
   SHELL
end
