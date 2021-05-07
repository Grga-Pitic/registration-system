Registration system
====================
Task:

Please implement a simple web-based user registration system.

Functionality requirements:
1. The system shall provide a home page to the user
2. On the home page, user can login with existing credentials, or regsiter a new account
3. User account information shall include: first name, last name, email, password, and birthday
4. After logged in, user shall be presented with his/her account information, and user can edit the account information (including password)
5. Logged in user shall be able to logout

Technical requirements:
1. Springboot
2. Hibernate
3. MySQL
4. Classic three layers design - Controller, Service, and Dao
5. Spring MVC
6. If possible, build and delivery the application via Docker image

Please also provide a short instruction how to run the application/Docker container, and please provide the source code as well.


Предварительные условия
-----------------------

Для запуска проекта вам понадобятся:
- Docker
- Apache Maven

Быстрый старт
-------------

Выполните в консоли следующие команды:

Для ОС Windows:
```
mvnw.cmd package -DskipTests
docker-compose up
```

Для GNU/Linux:
```
mvnw package -DskipTests
docker-compose up
```

Открыть веб-интерфейс можно по адресу:
```
http://localhost:8080/
```