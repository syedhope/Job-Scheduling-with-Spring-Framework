# Spring Boost Application

Spring Boot makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss. It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need. It is used to create stand-alone Java applications that can be started using java -jar or more traditional WAR deployments.

##Classes Included

### Scheduler
Task Scheduler helps to schedule automated tasks that perform actions at a specific time or when a certain event occurs. In this case, its sends messages via HTTP POST requests to /messages once every 3 seconds, with a JSON object in the request body comprising of a unique identifier called missionId and a positive integer in range 1000-20000 called seed.
### Controller
A Controller determines how requested paths get directed. Here, it provides a response to very request. If the missionId has already been received, it responds with an HTTP 409 status code else with the sum of all the amicable numbers under seed.
### Application
The Application class provides static convenience methods that make it easy to write a stand-alone Spring Application. Its sole job is to create and refresh an appropriate Spring ApplicationContex.

### Environment 
[JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

[Apache Maven 3.3.9](https://maven.apache.org/download.cgi)

[Spring Tool Suite 3.7.3](https://spring.io/tools)

### Usage : 

Preferred:
* Download Spring Tool Suite
* Import the <code>'.project'</code> file on the root directory of this folder
* Click run and select <code>'Spring Boot Application'</code>
        
Alternative:
* Download and install Apache Maven - [Instructions](https://maven.apache.org/install.html)
* Locate to the root directory and type <code>mvn spring-boot:run</code> in command prompt and hit enter
* Now open your web browser and visit "http://localhost:8080/"