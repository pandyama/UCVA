# Fullstack Web App using React & Java with Spring
- Final Project for ENSF607 - M.Eng in Software Engineering at University of Calgary
- Project_SQL.sql file contains all the CREATE and INSERT statements to setup a dummy database

## TECH
- Frontend: React, Bulma CSS Framework
- Backend: Java Spring Framework with MySQL Database, following MVC pattern
- Authentication: JWT using Auth0 Library

## WHO - Developers
- Zachary Frena
- Marko Mijovic
- Meet Pandya
------
## WHAT - Project Structure
All of the backend code is located in **Backend** folder
The API code is split into 4 packages - **Controller**, **Exception**, **Helper**, **Model**
### Controller Package
- Contains 6 classes that are Spring Framework's **@RestController** classes to handle request at different endpoints
- The **TestController** class as the name suggests is only for testing dummy endpoints to see how the request and response looks
### Exception Package
- Contains 3 classes responsible for handling Exceptions in a custom to make sure client receives meaningful responses
### Helper Package
- Contains 3 classes with different purposes to act as a Helper Classes throughout the code
- In future, this package could have additional classes created as the project comes to completion
### Model Package
- Contains all the classes related to all the Model objects required for this project
### BackendApplication.java
- This class contains the **Main** method where Spring Application is initialized and the server is started
### Postman Folder
- This folder contains Postman json files that have a bunch of GET & POST requests for the API

### Frontend Folder
- This folder contains all the Frontend code related to React part of this application

| React Endpoint   | Endpoint Details                  |
|------------------|-----------------------------------|
| /                | Login Page                        |
| /home            | Home Page with Search Animal view |
| /addAnimal       | Add Animal Page View              |
| /diagnosis       | Add Animal Diagnosis              |
| /uploadPhoto     | Upload Animal Photo               |
| /viewAlerts      | View Existing Disease Alerts      |
| /updateAnimal    | Update Animal's personal Info     |
| /treatment       | Treatment Request Page            |
| /prescription    | Prescription Page                 |
| /updateHealth    | Update Animal Health              |
| /borrowAnimal    | Request to Borrow Animal          |
| /requestsPending | Requests Pending Page             |
| /animalProfile   | Animal Profile Page               |
| /manageUsers     | Manage Users in the System        |

--------------------------------------------------------
## HOW
- In order to run the code, you just need to run the **BackendApplication.java** and it will start the spring server
- If you are using an IDE like IntelliJ or Eclipse, after cloning the repo, make sure to create a Configuration that's pointed to **BackendApplication.java**
## WHY
- This is the Final Project for ENSF607 course at University of Calgary
- The project is designed for the Vet Department at University of Calgary

## Few Screenshots of the App can be seen below

![1](https://github.com/pandyama/UCVA/blob/main/c1.PNG)

![2](https://github.com/pandyama/UCVA/blob/main/c2.PNG)

![3](https://github.com/pandyama/UCVA/blob/main/c3.PNG)

![3](https://github.com/pandyama/UCVA/blob/main/c4.PNG)
