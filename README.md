# expenses-api
A simple Spring-Boot restful application.

Install MYSQL database or apache xampp:
* https://www.apachefriends.org/xampp-files/7.2.4/xampp-win32-7.2.4-0-VC15-installer.exe

To run: 

mvn clean install

mvn spring-boot:run

### # Endpoints Expenses

* http://localhost:9090/santander/api/v1/expenses/{id} (GET) find expenses by userCode 5 seconds ago
* http://localhost:9090/santander/api/v1/expenses?userCode=1&date=2018-04-30T00:00:00 (GET) find expenses by filter
* http://localhost:9090/santander/api/v1/expenses (POST) insert. json= {"value": 100, "userCode": 1, "date": "2018-04-30T03:04:00"}
* http://localhost:9090/santander/api/v1/expenses (PUT) update. json= {"code": 1, "value": 100, "userCode": 1, "date": "2018-04-30T03:04:00", "description": "category 1", "version":0}

### # Endpoint Category
* http://localhost:9090/santander/api/v1/categories/{description} (GET) find categories by description

#### TODO
* update database properties in application.properties (main and test)
