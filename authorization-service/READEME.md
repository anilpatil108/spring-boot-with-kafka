# Spring Boot with Kafka

This Project covers how to use Spring Boot based microservices with Spring Kafka.

## 1. Start kafka
    ## Start Zookeeper
    - `bin/zookeeper-server-start.sh config/zookeeper.properties`
    
    ## Start Kafka Server
    - `bin/kafka-server-start.sh config/server.properties`
## 2. Run authorization-service
        mvn spring-boot:run 
## 3. Run profile-service
        mvn spring-boot:run
  
####4. Login to get JWT token

    URL 	                                     Method
    http://localhost:9090/api/login	               POST
    
    Request Payload:
    {
        "username":"admin",
        "password":"password"
    }
    
    Response payload:
    {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNTg4MDEzOTc5LCJleHAiOjE1ODgwMTU3Nzl9.KUhkCoSZP06DbmLc9zFiS5dcePkbTqpLtXwuIwRHneU"
    }
    
##### Use above generated token in Bearer token authorization for all below /profile rest API 


####5. Adding Profile

    URL 	                                     Method
    http://localhost:9090/profile	               POST
    
    Request Payload:
    {
 	   "username":"Anil",
 	   "address":"Pune",
 	   "phoneNumber":"7799811108"
     }
 
####6. Update Profile
  
    URL 	                                     Method
     http://localhost:9090/profile	              PUT
   
     Request Payload:
     {
  	    "username":"Anil",
      	"address":"Pune1",
      	"phoneNumber":"9867710857"
     }
  
 
 
####7. Delete Profile

     URL 	                                       Method
     http://localhost:9090/profile/Anil            DELETE 
