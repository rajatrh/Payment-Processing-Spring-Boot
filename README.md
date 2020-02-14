# Payment-Processing-Spring-Boot
Payment Processing Application using Spring Boot

#### Assumptions
 - User/ User Role management are not implemented in this project. Hence security features of Spring are not actualized.
 - Card Number/ Date validation is not implemented. Regex can be used to perform the same.
 - The checks performed to verify the transaction in the sample code are replicated in my implementation as well.

#### Architecture
- Through Spring Boot, I have used the following frameworks to further enrich the application:
 - Spring Boot JPA - Improvements in implementation of data access layers.
 - Spring Boot Rest - Used to easily expose HATEOAS RESTful APIs
 - Lombok - Auto generation of boilerplate methods such as Getters, Setters, Constructors, Equal, Hash Code etc.
 - MapStruct - Java Bean Mappings
 - Slf4j - For Logging
 - Controller level exception handling

#### Screenshots
<img width="707" alt="Img 1" src="https://user-images.githubusercontent.com/17957548/73528376-cd0be480-43e2-11ea-8307-4f1b25539a7d.png">
<br>
<img width="647" alt="Img 2" src="https://user-images.githubusercontent.com/17957548/73528333-b1a0d980-43e2-11ea-9f99-6bf27f8b2a7b.png">
<br>

#### Further Enhancements
 - Integration with a payment gateway such as Stripe, J2Pay etc.
 - Enhance the security as it plays a major role in everything, especially when it involves payment.
 - Interfaces for different payment methods.
 - Deployment exercise to AWS/GCP 

#### Steps to deploy locally
 - Install Java 8 and IntelliJ.
 - Extract the attached zip and open the project in IntelliJ.
 - Make sure that MySQL is installed and server is up.
 - Change the username, password of MySQL accordingly in application.yml

