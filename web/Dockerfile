FROM ubuntu:14.04
MAINTAINER Kyle Boon<kyle@cellarhq.com>

# Prerequisites
run apt-get update
run apt-get install -y software-properties-common

# Install Java 8
run add-apt-repository -y ppa:webupd8team/java
run apt-get update
run echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
run apt-get install -y oracle-java8-installer

WORKDIR /app

USER daemon

# this file is copied to build/docker along with the cellarhq.com-all.jar. 
# if you run docker build . from the command line, you need to either copy
# the jar to the current directory after running gradle shadowJar
# or update this to build/libs/cellarhq.com-all.jar
ADD cellarhq.com-all.jar /app/build/libs/cellarhq.com-all.jar

CMD ["java", "-jar", "/app/build/libs/cellarhq.com-all.jar"]

EXPOSE 5050