# mathapp-java
There are 4 java Applications in MathApp:
- MathClient - web client
- MathProxy - Jersey RestFul API proxy
- MathSimpleBackend - Axis2 SOAP Backend
- MathComplexBackend - Axis2 SOAP Backend

MathClient calls MathProxy
MathProxy calls MathSimpleBackend
MathProxy calls MathComplexBackend
MathProxy also calls a .Net backend if using the "mathdotnet" RestFul API.

MathApp Port Configuration:
- MathClient --> MathProxy, MathProxy host/port configuration is in math.properties found in the "MathClient/WEB-INF/classes"
- MathProxy --> MathSimpleBackend, MathComplexBackend, .NetBackend. The host/port configuration is in math.properties found in the "MathProxy/WEB-INF/classes"

Useful URLs:
MathClient: http://host:<port>/MathClient

MathProxy RestAPI's:
http://localhost:8080/MathProxy/rest/hello
http://localhost:8080/Jersey-REST-Client/restClient
http://localhost:8080/Jersey-REST-Client/JerseyApacheClientServlet
http://localhost:8080/MathProxy/rest/hello/math?operation=add&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathcomplex?operation=flush&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathcomplex?operation=mean&values=5,10,15
http://localhost:8080/MathProxy/rest/hello/mathdotnet?operation=add&value1=5&value2=10
http://localhost:8080/MathProxy/rest/hello/mathdotnet?operation=multiply&value1=5&value2=10

dotNet API's:
http://localhost/MathWebService/MathWebService.asmx?WSDL
http://localhost/MathWebService/MathWebService.asmx

MathSimpleBackend & MathComplexBackend Axis2 URL:
http://localhost:8080/MathComplexBackend/axis2-web/
WSDL's for MathSimpleBackend, MathComplexBackend and mathdotnetcan be found in the MathProxy project directory
