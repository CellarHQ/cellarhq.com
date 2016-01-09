FROM java:8
MAINTAINER Kyle Boon<kyle@cellarhq.com>

WORKDIR /app/build/libs

USER daemon

CMD ["java", "-jar", "/app/build/libs/cellarhq.com-all.jar", "-Xmx1G -Xms1G -XX:NewSize=512m -XX:MaxNewSize=512m"]

EXPOSE 5050

# this file is copied to build/docker along with the cellarhq.com-all.jar.
# if you run docker build . from the command line, you need to either copy
# the jar to the current directory after running gradle shadowJar
# or update this to build/libs/cellarhq.com-all.jar
ADD web-all.jar /app/build/libs/cellarhq.com-all.jar
ADD production.properties /app/build/libs/app.properties
ADD .ebextensions .ebextensions
