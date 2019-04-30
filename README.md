# Springboot + Eureka + Zuul + Feign

API REST for Expense Management!

###Enviroment Configuration
<b>Redis: </b>  https://redis.io/download
<b>Lombok: </b> https://projectlombok.org/setup/eclipse
<i>Lombok Tutorial: </i> https://projectlombok.org/setup/eclipse
<b>MySQL: </b> https://dev.mysql.com/downloads/
<b>Postman: </b> https://www.getpostman.com/downloads/

Order to run the applications: 1. eurekaserver / 2. category-management / 3. expense-management / 4. zuul

<b>Note:</b> Before you use all endpoints, I personally suggest to add some expenses first and use the same as well to search for what you need.
I didn't populated the base and all of the values below are merely illustrative. 

How to use cURL code:
```
1. Open postman.exe
2. CTRL + O
3. Choose: Past Raw Text
4. Paste the cURL code below.
5. Click on button send to run the endpoint.
```

Endpoints:
Expense:
```
* Add a new expense
POST http:localhost:8762/expense-management/expenses
cURL:
curl -X POST \
  http://http:localhost:8762/expense-management/expenses \
  -H 'Postman-Token: c79ad816-5fe0-4ea2-8647-cb4251931543' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'description=O homem mais rico da babilonia' \
  -F cost=30.4 \
  -F category=Leitura

* Update a new expense
PUT http:localhost:8762/expense-management/expenses/1
cURL:
curl -X POST \
  http://http:localhost:8762/expense-management/expenses/1 \
  -H 'Postman-Token: de311b6c-9101-4133-ae76-05f9efc6578c' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F codUser=1 \
  -F 'description=O homem mais rico da babilonia' \
  -F cost=30.4 \
  -F category=Leitura

* Search for expenses by user
GET http:localhost:8762/expense-management/expenses/1

* Search for expenses by user in a specific date
http:localhost:8762/expense-management/expenses/1/2019-04-06
```



Category:
```
* Searching for similar name: Autocomplete usability
GET http:localhost:8762/category-management/categories/suggest?name=Roup
* Search by exact name  
GET http:localhost:8762/category-management/category/Comida
* Add Category
POST http:localhost:8762/category-management/categories
cURL: 
curl -X POST \
  http://http:localhost:8762/category-management/categories \
  -H 'Postman-Token: 87f38883-4711-4d4a-a185-87425fa7b2ac' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F name=Saques

*  Update category
http:localhost:8762/category-management/categories/1
cURL:
curl -X POST \
  http://http:localhost:8762/category-management/categories/1 \
  -H 'Postman-Token: 86de2127-2c50-4e35-9094-af4c07bbf4e8' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F name=Saques
```

