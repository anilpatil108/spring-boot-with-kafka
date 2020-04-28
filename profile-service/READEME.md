 1. Clone the Git repository.
Open Command Prompt and Change directory (cd) to folder containing pom.xml
Open Eclipse

    File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
    Select the project

We have to run assigment project using 
mvn spring-boot:run

2. Run authorization-service and profile-service
3. Start kafka 
4. To monitor and manage your application we are using bellow login url and json

URL 	                                     Method
http://localhost:9090/profile	               POST

{
	"username":"admin",
	"password":"password"
}
5. Generated token We have to use have to use for every GET/POST/PUT/DELETE request 

6. Post method url and json 

 URL 	                                     Method
 http://localhost:9090/profile	               POST
 
 {
 	"username":"Anil",
 	"address":"Pune",
 	"phoneNumber":"779981118"
 }
 6. PUT method url and json 
 
 URL 	                                     Method
  http://localhost:9090/profile	               GET
  
 {
 	        "username":"Anil",
        	"address":"Pune",
        	"phoneNumber":"779981118"
 }
  7. PUT method url and json 
  
  URL 	                                     Method
   http://localhost:9090/profile	           PUT
   
  {
  	   "username":"Anil",
      	"address":"Pune1",
      	"phoneNumber":"9867710857"
  }
 
 8. DELETE method url
  URL 	                                        Method
  http://localhost:9090/profile/Anil            DELETE 
 
