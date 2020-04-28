# Spring Boot with Kafka

This Project covers how to use Spring Boot based microservices with Spring Kafka.

## 1. Start kafka
    ## Start Zookeeper
    - `bin/zookeeper-server-start.sh config/zookeeper.properties`
    
    ## Start Kafka Server
    - `bin/kafka-server-start.sh config/server.properties` 
## 2. Run profile-service
        mvn spring-boot:run

####3. Adding  Profile

    URL 	                                     Method
    http://localhost:7070/profile	               POST
    
    Request Payload:
    {
 	   "username":"Anil",
 	   "address":"Pune",
 	   "phoneNumber":"7799811108"
     }
 
####4. Update  Profile
  
    URL 	                                     Method
     http://localhost:7070/profile	              PUT
   
     Request Payload:
     {
  	    "username":"Anil",
      	"address":"Pune1",
      	"phoneNumber":"9867710857"
     }
  
 
 
####5. Delete  Profile
    URL 	                                     Method
    http://localhost:7070/profile/Anil            DELETE 
 
