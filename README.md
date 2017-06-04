# Get My Parking: Back-end Assignment

This project is a back-end assignment as a part of the recruitment process for getmyparking.com

It is a parking lot management system handling basic parking sessions, where a vehicle's check-in and check-out is being tracked by the system with different scenarios of parking lots, these scenarios are explained in later sections  

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. Read the project conceptualization below carefully before proceeding with the setup:


**Project concept Details**:
This project is divided in two parts. The first one is a static cost calculation API which takes in check-in time, check-out time and parking type as input and calculates the cost as per the type, i.e. the given scenarios in the problem statement.

The second part is a dynamic approach which needs connection to the database (Read the details below for configuring the database connection). This part has two APIs in place namely check-in and check-out.
In the check-in API the operator has to provide only the vehicle details, for record keeping purpose, and the parking lot type. The API will insert the vehicle and lot details along with the current timestamp as in time of the vehicle. It will generate the parking ticket ID and send it as a response along with other flags/parameters.
In the check-out API, the operator needs to simply provide the ticketId as an input. The system will fetch vehicle/lot details and according to the lot type, i.e. the scenario it will calculate the total cost that the vehicle owner has to bear. 

**Note that the night charges will be calculated, if applicable, in all the cases**

### Prerequisites

Following are the tools and software needed to setup the project:


1. Java Development Kit (JDK) Download the latest JDK [here](https://www.java.com/en/download/)
1. Java Development IDE, e.g. Eclipse, Netbeans, etc. It is advised to use Eclipse Mars2, as the project was developed using the same. Older versions may have upward compatibility issues, so install Eclipse Mars 2 to be on a safer side
1. Database Server: Oracle, MySQL or any other DBMS of your preference is needed to establish a connection to handle data storage and management
1. Git client like git bash or other GUI based client like TortoiseGIT to clone the project from the github repository


### Installing

1. Use Git to make a clone of the project in your system.
1. Unzip the apache-tomcat-8.0.43.zip, which can be found in the project cloned from Git.
1. Open eclipse with a new workspace
1. Go to File -> Import -> General -> Existing Project Into Workspace
1. Next we need to attach a local server to deploy this project. If you are using Eclipse, follow the steps below, otherwise follow the similar steps in your respective IDE:

* Click on Window menu -> Show View -> Servers
* Right Click on Servers Tab and then click on 'New' -> Server. 
* Under Apache select Tomcatv8.0 Server, then click next
* Click on browse next to the Tomcat installation Directory and give the path of the root directory of the unzipped apache-tomcat-8.0.43 folder and click finish and finish creating the New Server.

After adding the Server, we shall start the server:
1. Clean and Build both the projects in the workspace,then Right Click on Server instance created in the server tab and Click on start.
1. This step is optional.If you also want to use the Dynamic Part of the project (i.e Checking In at run time and Checking out later), you will need to provide a connection to the database. For this you will need to first setup a database server on your machine , create a user in it and then do the following on Eclipse or similar IDE:

* To add a Connection Driver Jar, right click on Servers (project created next to myParking) . Click on run -> run configurations, double click on Apache Tomcat present in the menu, Click on classpath and then on UserEntries Click On Add JAR, add the ojdbc14 or mysql-connector jar depending on your choice of Database present in /myParking/src/main/resources folder.
* Update the /myParking/src/main/resources/hibernate.cfg.xml with your database's connection parameters and driver's classname etc.



### Running the Project

**Running Through IDE/browser:**

1. Right click on myParking Project, click on Run as -> Run on Server
1. Enter the details: Vehicle number, Check in Time, Check out time, and Parking Lot type. Date time should be in this format "yyyy-MM-dd hh:mm:ss" and parking lot can be 0,1 and 2.

* Type 0: Basic type where rate is flat Rs 20 per hour for the duration of the parking session. 

* Type 1: Flat rate for first x hours, then incremental Rs y for every z hours. a. Example Rs 10 for first 2 hours then Rs 5 every hour.

* Type 2: Flat rate for x hours repetitively, then incremental Rs y for every z hours. a. Example Rs 20 for first 2 hours then Rs 10 for next 3 hours, then Rs 5 every hour.
 
* Night Charges:Special Night charges in addition to the fixed prices described above. This will be a flat rate for a given time period. The time period can extend between dates. Example 10 pm to 5 am. 

*-Note that the night charges will be calculated, if applicable, in all the cases*

**Running Through PostMan, or any other client as a REST API:**

1. Add the project to the server and Restart the server
2. Use the following End Points:

* Url: http://localhost:8080/myParking/costCalculationAPI
* Body -> raw -> JSON(application/json): Give the input JSON object in Key-Value Pair form

For the Cost Calculation API:
1. URL:http://localhost:8080/myParking/costCalculationAPI
2. Json Inputs:

* LotNo 
* inTime
* outTime

Example:

```
{
"LotNo": "0", 
"inTime": "2017-06-01 10:30:00",
"outTime": "2017-06-02 10:30:00"
}
```

For the Dynamic approach Check In:
1. URL:http://localhost:8080/myParking/costCalculationAPI
2. Json Inputs:

* VehNo 
* LotNo

```
{
"VehNo": "DL-9C KT 1213",
"LotNo": "0"
}
```

For the Dynamic approach Check Out:

After check In
1. URL:http://localhost:8080/myParking/costCalculationAPI
2. Json Inputs:

* tokenId 

```
{
"tokenId": "145"
}
```



## Running the tests

Tests are written in */myParking/src/main/java/com/dao/ParkingDaoImplTest.java* File.
Right click on the file and Run as Junit Tests.




