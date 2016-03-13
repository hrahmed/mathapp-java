#### mathapp-java
There are 4 java Applications in MathApp:
- MathClient - web client
- MathProxy - Jersey RestFul API proxy
- MathSimpleBackend - Axis2 SOAP Backend
- MathComplexBackend - Axis2 SOAP Backend

MathClient calls MathProxy
MathProxy calls MathSimpleBackend
MathProxy calls MathComplexBackend
MathProxy also calls a .Net backend if using the "mathdotnet" RestFul API.

### Tested Environments
Tomcat 6 & 7

### Configuration:
## MathApp Port Configuration:
- MathClient --> MathProxy, MathProxy host/port configuration is in math.properties found in the "MathClient/WEB-INF/classes"
- MathProxy --> MathSimpleBackend, MathComplexBackend, .NetBackend. The host/port configuration is in math.properties found in the "MathProxy/WEB-INF/classes"

## To download project:
git clone https://github.com/hrahmed/mathapp-java.git

## Build all projects:
In main directory
mvn clean install

## Build for each project:
Example:
cd MathClient
mvn clean install

cd MathProxy
mvn clean install

cd MathSimpleBackend
mvn clean install

cd MathComplexBackend
mvn clean install

## Deploy to Tomcat
Copy MathClient.war, MathProxy.war, MathSimpleBackend.war and MathComplexBackend.war files found in the "%project%/target" directory to the Tomcat webapp directory.  

Restart Tomcat and goto: http://%host%:%port%/MathClient

## Update Tomcat User Login Information
Update the tomcat-users.xml file with details in "config/tomcat-users.xml.toAddtoTomcatConfig"

### Useful URLs:
MathClient WebApp: http://%host%:%port%/MathClient
# Login with:
Login: user
Password: math

MathProxy RestAPI's:
http://localhost:8080/MathProxy/rest/hello
http://localhost:8080/Jersey-REST-Client/restClient
http://localhost:8080/Jersey-REST-Client/JerseyApacheClientServlet
http://localhost:8080/MathProxy/rest/hello/math?operation=add&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathcomplex?operation=flush&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathcomplex?operation=mean&values=5,10,15
http://localhost:8080/MathProxy/rest/hello/mathdotnet?operation=add&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathdotnet?operation=multiply&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathnode?operation=add&value1=5&value2=10

dotNet API's:
http://localhost/MathWebService/MathWebService.asmx?WSDL
http://localhost/MathWebService/MathWebService.asmx

MathSimpleBackend & MathComplexBackend Axis2 URL:
http://localhost:8080/MathComplexBackend/axis2-web/
WSDL's for MathSimpleBackend, MathComplexBackend and mathdotnetcan be found in the MathProxy project directory
