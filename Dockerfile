#FROM gradle:7.3.3-jdk17-alpine AS build
#COPY  . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle assemble

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY build/libs/jibber-jabber-followers-0.0.1-SNAPSHOT.jar /app/JibberJabberFollowersApplication.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production","/app/JibberJabberFollowersApplication.jar"]