#SET-UP THE PROJECT
Download the Application:

You can download the Spring Boot application from the following link: [Link to Application Download]

Setting Up the Application:

After downloading the application, follow these steps to set it up:

Extract the downloaded zip file to your desired location.
Open your preferred IDE (Integrated Development Environment) such as Eclipse or IntelliJ IDEA.
Import the extracted project as a Maven project.
Once the project is imported, let the Maven dependencies be resolved automatically.
Configure your application.properties file ,Set the desired port.                                                                             		
Running the Application:

Run the main class of the application (FileProcessorApplication).
The application will start and be accessible via the specified port (default port for Spring Boot applications is 8099).


Using the Application:



Once the application is up and running, you can perform the following tasks:

Reading Excel or CSV File:

Open Postman set the Action to POST.

Below that go to body and then select form-data.

Below that, set the name of the key to file and change the action to file(default: Text) then upload the file you want to but the extension should be .xlsx, .xls and .csv

Below that, set the name of the key to startrow and leave the action to text then select a number (e.g 2)

SEND.



Reading XML File:

Open Postman set the Action to POST.

Below that go to body and then select form-data.

Below that, set the name of the key to file and change the action to file(default: Text) then upload the file you want to but the extension should be .xml.

SEND.
