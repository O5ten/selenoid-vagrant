# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  config.vm.box_download_insecure = true
  config.vm.box = "ubuntu/xenial64"

  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 4444, host: 4444

  config.vm.hostname = "selenoid"
  config.vm.post_up_message = "Selenoid is now started, go ahead and run your te                                                                                                                                  sts against http://localhost:4444/wd/hub and watch them using http://localhost:8                                                                                                                                  080"

  config.vm.provider :virtualbox do |vb|
      vb.memory = "2048"
  end

  config.vm.provision "shell", inline: <<-SHELL
     sudo apt-get remove docker docker-engine docker.io
     sudo apt-get update
     sudo apt-get -y install \
         linux-image-extra-$(uname -r) \
         linux-image-extra-virtual
     sudo apt-get install \
         apt-transport-https \
         ca-certificates \
         curl \
         software-properties-common
     curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add                                                                                                                                   -
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
     docker run --rm                                   \
        -v /var/run/docker.sock:/var/run/docker.sock    \
        -v ${HOME}:/root                                \
        -e OVERRIDE_HOME=${HOME}                        \
        aerokube/cm:latest-release selenoid-ui start    \
        --port 8080
   SHELL
end
