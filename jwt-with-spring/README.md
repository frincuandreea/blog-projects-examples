This is a small project which generates a jwt token in a spring application using the jjwt-impl library.

#### How to run this application?

1. In the project folder run mvn clean install to build the project.
2. After the point 1 is done you can run the script runApp.sh
3. To test your application run first the retrieving of the token using the command below in a terminal or call the link from browser:

curl --location --request GET 'http://localhost:8080/api/jwt/generateToken' --header 'Content-Type: text/plain'
To test the decription of the code you can do a POST call to the service http://localhost:8080/api/jwt/decodeToken with the token. 
For example a curl command would look like below:
curl --location --request POST 'http://localhost:8080/api/jwt/decodeToken' --header 'Content-Type: text/plain' 
--data-raw 'eyJhbGciOiJIUzUxMiJ9.eyJ0b2tlbkRhdGEiOnsiaWQiOiJ0ZXN0SWQiLCJ0eXBlVXNlciI6InByaXZhdGVfdXNlciJ9LCJpYXQiOjE2ODUxODg4NjIsImV4cCI6MTY4NTE5MjQ2Mn0.Ki9bXZ9bSU3bNRef5NaLJIi82TlVs8DmmfDv2hf-KXIyWyG6yGoqpTZBC0N2WDklec4NkmqfF_nOof6D76oLOA'

For calling the serviceses you can use any tool you want.

Hope this is helpful!
