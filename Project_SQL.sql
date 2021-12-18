# ENSF608 2021 - FINAL PROJECT
# TEAM MEMBERS
#     - Zachary Frena, Marko Mijovic, Meet Pandya

DROP DATABASE IF EXISTS VETAPPLICATION;
CREATE DATABASE VETAPPLICATION;
USE VETAPPLICATION;

## ******************USER RELATED TABLES******************
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
	userID integer not null AUTO_INCREMENT,
    username varchar(15) not null,
    email varchar(30) not null,
    uPassword varchar(15) not null,
    uRole varchar(30) not null,
    accessLevel integer not null,
    primary key (userID)
);
ALTER TABLE USERS AUTO_INCREMENT=1001;

INSERT INTO USERS( username, email, uPassword, uRole, accessLevel)
VALUES
( "zachfrena", "zachary@gmail.com", "zach123", "student", 1),
( "pandyama", "pandyama@gmail.com", "meet123", "admin", 5),
( "markom", "marko@gmail.com", "marko123", "teaching_technician", 4),
( "uname4", "uname4@gmail.com", "uname4123", "care_attendant", 3),
( "uname5", "uname5@gmail.com", "uname5123", "health_technician", 2),
( "uname6", "uname6@gmail.com", "uname6123", "student", 1),
( "Instructor_1", "instructor@ucalgary.ca", "pt@123", "teaching_technician", 2),
( "Admin_1", "admin@ucalgary.ca", "pa", "admin", 5),
( "Technician", "technician@ucalgary.ca", "pe", "health_technician", 2);

DROP TABLE IF EXISTS STUDENT;
CREATE TABLE STUDENT(
	studentID integer not null,
    primary key (studentID),
	foreign key (studentID) references USERS(userID)
);
INSERT INTO STUDENT(studentID)
VALUES
(1001),
(1006);

DROP TABLE IF EXISTS TEACHING_TECHNICIAN;
CREATE TABLE TEACHING_TECHNICIAN(
	teachingTechnicianID integer not null,
    primary key (teachingTechnicianID),
	foreign key (teachingTechnicianID) references USERS(userID)
);
INSERT INTO TEACHING_TECHNICIAN(teachingTechnicianID)
VALUES
(1003);

DROP TABLE IF EXISTS HEALTH_TECHNICIAN;
CREATE TABLE HEALTH_TECHNICIAN(
	healthTechnicianID integer not null,
    primary key (healthTechnicianID),
	foreign key (healthTechnicianID) references USERS(userID)
);
INSERT INTO HEALTH_TECHNICIAN(healthTechnicianID)
VALUES
(1005);

DROP TABLE IF EXISTS CARE_ATTENDANT;
CREATE TABLE CARE_ATTENDANT(
	careAttendantID integer not null,
    primary key (careAttendantID),
	foreign key (careAttendantID) references USERS(userID)
);
INSERT INTO CARE_ATTENDANT(careAttendantID)
VALUES
(1004);

DROP TABLE IF EXISTS ADMINS;
CREATE TABLE ADMINS(
	adminID integer not null,
    primary key (adminID),
	foreign key (adminID) references USERS(userID)
);
INSERT INTO ADMINS(adminID)
VALUES
(1002);
## ******************END - USER RELATED TABLES******************


## ******************ANIMAL RELATED TABLES******************
DROP TABLE IF EXISTS ANIMAL;
CREATE TABLE ANIMAL(
   animalID integer not null AUTO_INCREMENT,
   tattooNumber integer not null,
   cityTattoo varchar(30) not null,
   aName varchar(30) not null,
   age integer not null,
   weight integer not null,
   sex varchar(1) not null,
   birthDate varchar(10) not null,
   breed varchar(15) not null,
   coatColor varchar(20) not null,
   species varchar(10) not null,
   purpose varchar(30) not null,
   borrowStatus integer,
   permanentLocation varchar(20) not null,
   profileImage varchar(20) not null,
   activeID integer not null,
   rfid integer not null,
   microchip integer not null,
   primary key (animalID)
);
ALTER TABLE ANIMAL AUTO_INCREMENT=100;
INSERT INTO ANIMAL (tattooNumber, cityTattoo, aName,
age, weight, sex, birthDate, breed, coatColor, species, purpose, borrowStatus, 
permanentLocation, profileImage, activeID, rfid, microchip)
VALUES
(1001,"CALGARY1001","Buddy",2,30.5,"M","12/01/2019","Husky","White",
"Dog","Training",0,"Building A","image1.png",900,18001,240001),
(1002,"CALGARY1002","Cece",1,10.5,"F","12/01/2020","Frenchie","Brown",
"Dog","Support",1,"Building A","image2.png",901,18002,240002),
(1003,"VANCOUVER1003","Milo",3,40,"F","12/10/2018","Pitbull","Brown",
"Dog","Support",0,"Building B","image2.png",902,18034,240012),
(1004,"VANCOUVER1004","Rocco",7,100,"F","09/01/2014","Alaskan Mal","Brown",
"Dog","Medical",1,"Building C","image4A.png",910,18144,241112),
(1005,"SEATTLE1003","Rocky",3,50,"M","12/01/2018","Doberman","Black",
"Dog","Training",1,"Building C2","image4.png",912,18134,250012),
(1006,"AUSTIN1003","Beast",6,400,"F","12/11/2015","Mustang","Brown",
"Horse","Support",0,"Building C","image5.png",922,18104,250013);

DROP TABLE IF EXISTS ANIMAL_HEALTH;
CREATE TABLE ANIMAL_HEALTH(
	healthID integer not null AUTO_INCREMENT,
    animalID integer not null,
    healthStatus varchar(15) not null,
    continuousMedication varchar(50),
    specialInstruction varchar(50),
    specialDiet varchar(50),
	primary key (healthID),
	foreign key (animalID) references ANIMAL(animalID)
);
ALTER TABLE ANIMAL_HEALTH AUTO_INCREMENT=10001;
INSERT INTO ANIMAL_HEALTH (animalID, healthStatus, continuousMedication, specialInstruction, specialDiet)
VALUES
(100,'Healthy',null, 'walk everday','dry food'),
(102,'Sick','pills', '1 meal per day','wet food'),
(103,'Sick',null, 'walk everday',null),
(104,'Healthy','shots', 'no contact',null);

DROP TABLE IF EXISTS ANIMAL_BORROW;
CREATE TABLE ANIMAL_BORROW(
	animalID integer not null,
    teachingTechnicianID integer not null,
    borrowDate date not null,
    returnDate date not null,
    purpose varchar(50) not null,
	primary key (animalID,teachingTechnicianID,borrowDate),
	foreign key (animalID) references ANIMAL(animalID),
    foreign key (teachingTechnicianID) references TEACHING_TECHNICIAN(teachingTechnicianID)
);

INSERT INTO ANIMAL_BORROW(animalID, teachingTechnicianID, borrowDate, returnDate, purpose)
VALUES
(102,1003,'2021-12-05','2021-12-22',"Lab"),
(104,1003,'2021-11-12','2021-11-30',"Teaching"),
(102,1003,'2021-11-23','2021-12-30',"Medical");

DROP TABLE IF EXISTS ANIMAL_IMAGES;
CREATE TABLE ANIMAL_IMAGES(
	imageID integer not null AUTO_INCREMENT,
    injuryImage varchar(15) not null,
    injuryType varchar(50) not null,
    injuryDate varchar(10) not null,
    animalID integer not null,
    careAttendantID integer not null,
	primary key (imageID),
	foreign key (animalID) references ANIMAL(animalID),
    foreign key (careAttendantID) references CARE_ATTENDANT(careAttendantID)
);
ALTER TABLE ANIMAL_IMAGES AUTO_INCREMENT=2000;
INSERT INTO ANIMAL_IMAGES(injuryImage,injuryType,injuryDate,animalID,careAttendantID)
VALUES
("injuryImage.png","Left foot bleeding","11/20/2021",102,1004),
("injuryImage.png","Ear bleeding","11/10/2021",103,1004);

DROP TABLE IF EXISTS ANIMAL_TREATMENT;
CREATE TABLE ANIMAL_TREATMENT(
	treatmentID integer not null AUTO_INCREMENT,
    treatmentDate varchar(10) not null,
    treatmentStatus varchar(20) not null,
    animalID integer not null,
    careAttendantID integer not null,
	primary key (treatmentID),
	foreign key (animalID) references ANIMAL(animalID),
    foreign key (careAttendantID) references CARE_ATTENDANT(careAttendantID)
);
ALTER TABLE ANIMAL_TREATMENT AUTO_INCREMENT=20001;
INSERT INTO ANIMAL_TREATMENT ( treatmentDate, treatmentStatus, animalID,careAttendantID)
VALUES
('01/03/15','in progress', 101,1004),
('02/03/15','pending', 102,1004),
('06/24/15','pending', 103,1004),
('11/13/15','complete', 104,1004);

DROP TABLE IF EXISTS ANIMAL_DIAGNOSIS;
CREATE TABLE ANIMAL_DIAGNOSIS(
   diagnoseID integer not null AUTO_INCREMENT,
   animalID integer not null,
   diagnosisDetail varchar(50) not null,
   submissionDate varchar(30) not null,
   healthTechnicianID integer not null,
   primary key (diagnoseID),
   foreign key (animalID) references ANIMAL(animalID),
   foreign key (healthTechnicianID) references HEALTH_TECHNICIAN(healthTechnicianID)
);
ALTER TABLE ANIMAL_DIAGNOSIS AUTO_INCREMENT=30001;
INSERT INTO ANIMAL_DIAGNOSIS (animalID, diagnosisDetail, submissionDate, healthTechnicianID)
VALUES
(101,"Ear infection detected","11/01/2021",1005),
(104,"Stomach ache detected","09/16/2021",1005),
(102,"Broken Foot","10/16/2021",1005);

DROP TABLE IF EXISTS ANIMAL_PRESCRIPTION;
CREATE TABLE ANIMAL_PRESCRIPTION(
	prescriptionID integer not null AUTO_INCREMENT,
	diagnoseID integer not null,
    submissionDate varchar(30) not null,
    primary key (prescriptionID),
    foreign key (diagnoseID) references ANIMAL_DIAGNOSIS(diagnoseID)
);
ALTER TABLE ANIMAL_PRESCRIPTION AUTO_INCREMENT=40001;
INSERT INTO ANIMAL_PRESCRIPTION (diagnoseID, submissionDate)
VALUES
(30001,"11/02/2021"),
(30002,"09/18/2021"),
(30003,"10/20/2021");

## ******************END - ANIMAL TABLES******************


## **********************OTHER TABLES*********************
DROP TABLE IF EXISTS DISEASE_ALERTS;
CREATE TABLE DISEASE_ALERTS(
	alertID integer not null AUTO_INCREMENT,
	careAttendantID integer not null,
    reportedDate varchar(30) not null,
    alertDetails varchar(50) not null,
    locationAffected varchar(15) not null,
    outbreakStatus varchar(10) not null,
    primary key (alertID),
    foreign key(careAttendantID) references CARE_ATTENDANT(careAttendantID)
);
ALTER TABLE DISEASE_ALERTS AUTO_INCREMENT=50001;
INSERT INTO DISEASE_ALERTS(ReportedDate, AlertDetails, LocationAffected, OutbreakStatus, careAttendantID)
VALUES
("05-12-2020", "noticed skin graft", "Building A", "Resolved", 1004),
("03-05-2021", "noticed heavy breathing", "Building B", "Active", 1004),
("03-07-2021", "noticed rash", "Barn F", "Active", 1004),
("02-05-2020", "noticed cough", "Building A", "Resolved", 1004);

DROP TABLE IF EXISTS PRESCRIPTION_ITEM;
CREATE TABLE PRESCRIPTION_ITEM(
	itemID integer not null AUTO_INCREMENT,
    prescriptionType varchar(20),
    prescriptionDetail varchar(50) not null,
    prescriptionID integer not null,
	primary key (itemID),
	foreign key (prescriptionID) references ANIMAL_PRESCRIPTION(prescriptionID)
);
ALTER TABLE PRESCRIPTION_ITEM AUTO_INCREMENT=50001;
INSERT INTO PRESCRIPTION_ITEM(prescriptionType, prescriptionDetail, prescriptionID)
VALUES
("Medicine", "Take pill twice a day", 40001),
("Exercise", "Exercise for 2 hours", 40002),
("Operation", "Operation to fix broken foot", 40003);

DROP TABLE IF EXISTS TREATMENT_TYPE;
CREATE TABLE TREATMENT_TYPE(
	typeID integer not null AUTO_INCREMENT,
    treatmentDescription varchar(10) not null,
    treatmentID integer not null,
	primary key (typeID),
	foreign key (treatmentID) references ANIMAL_TREATMENT(treatmentID)
);
ALTER TABLE TREATMENT_TYPE AUTO_INCREMENT=60001;
INSERT INTO TREATMENT_TYPE(treatmentDescription,treatmentID)
VALUES
('ice bath', 20001),
('tape',20002),
('sling',20003),
('stitches',20004);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(
	animalID integer not null,
	commentID integer not null AUTO_INCREMENT,
    commentText varchar(50) not null,
    userID integer not null,
	primary key (commentID),
	foreign key (userID) references USERS(userID),
    foreign key (animalID) references ANIMAL(animalID)
);
ALTER TABLE COMMENTS AUTO_INCREMENT=60001;
INSERT INTO COMMENTS (animalID, commentText, userID)
VALUES
(100, 'I love this dog', 1001),
(100, 'So fluffy',1002),
(101, 'reminds me of my old cat',1003),
(102, 'reminds me of my old pet',1004),
(103, 'This horse looks so cool',1005),
(104, 'This is a cool animal',1005),
(104, 'Coolest animal Ive ever seen',1005);

DROP TABLE IF EXISTS TEACHING_TECHNICIAN_LOG;
CREATE TABLE TEACHING_TECHNICIAN_LOG(
	logID integer not null AUTO_INCREMENT,
	teachingTechnicianID integer not null,
    studentID integer not null,
    actionTaken varchar(30) not null,
    dateOfAction varchar(10) not null,
	primary key (logID),
	foreign key (teachingTechnicianID) references TEACHING_TECHNICIAN(teachingTechnicianID),
    foreign key (studentID) references STUDENT(studentID)
);
ALTER TABLE TEACHING_TECHNICIAN_LOG AUTO_INCREMENT=70001;
INSERT INTO TEACHING_TECHNICIAN_LOG(teachingTechnicianID, studentID, actionTaken, dateOfAction)
VALUES
(1003,1001,"Access Granted","11/30/2021"),
(1003,1006,"Access Granted","11/20/2021"),
(1003,1006,"Access Blocked","11/29/2021");

DROP TABLE IF EXISTS ADMIN_LOG;
CREATE TABLE ADMIN_LOG(
	logID integer not null AUTO_INCREMENT,
	adminID integer not null,
    userID integer not null,
    actionTaken varchar(30) not null,
    dateOfAction varchar(10) not null,
	primary key (logID),
	foreign key (userID) references USERS(userID),
    foreign key (adminID) references ADMINS(adminID)
);
ALTER TABLE ADMIN_LOG AUTO_INCREMENT=80001;
INSERT INTO ADMIN_LOG(adminID, userID, actionTaken, dateOfAction)
VALUES
(1002, 1001, "added user", "01-01-2020"),
(1002, 1006, "edited user", "05-02-2020");
## **********************END*********************