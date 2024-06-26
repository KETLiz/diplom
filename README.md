Тема проекта: разработка веб-приложения «Библиотека» на языке Java с помощью фреймворка Spring. Проект был создан с помощью Spring Initializer, сборщик проектов Maven.

Цель проекта: изучение работы клиент-серверного взаимодействия. В качестве клиента использовались браузер и Postman, в качестве сервера – мой ноутбук. 
Приложение работает по адресу http://localhost:8081, а по адресу http://localhost:8080 работает сервис авторизации.
Инструменты: Spring Boot, IntelliJ IDEA, Git, GitHub, Postman, H2.

В качестве примера я создала проект «Библиотека», который хранит в себе несколько книг и несколько читателей. Книги и читатели хранятся в базе данных. 
При старте проекта создаются книги и читатели, заданные в коде программы, благодаря аннотации @EventListener(ContextRefreshedEvent.class). 
При работе программы, можно создавать новые  и удалять имеющиеся книги и читателей. Работа с репозиториями реализована с помощью интерфейсов, имплементирующих JpaRepository и CrudRepository.

В базе данных хранятся книги. Эти книги можно взять почитать и вернуть обратно. У каждого читателя есть свой лимит не возвращённых книг, больше которого, новые книги ему не выдаются. Этот лимит я зафиксировала в файле настроек application.yml. 
Там же, в этом файле указаны и другие настройки приложения: порт, на котором работает Spring Security, порт, на котором работает приложение, название приложения и настройки подключения к базе данных.

Для обработки входящих запросов были реализованы классы контроллеров, помеченные аннотациями @Controller и @RestController. 
Классы, помеченные аннотацией @Controller, возвращают html страницу с ответом на соответствующий запрос, а классы, помеченные @RestController, возвращают ответ в формате JSON. 
Методы, описывающие логику получения определённых данных или страниц в зависимости от адреса в адресной строке, помечаются аннотацией @GetMapping. 
Для отправки новых данных на сервер или удаления данных, используются аннотации @PostMapping и @DeleteMapping соответственно. 
Если GET запросы можно делать из браузера, то для отправки POST и DELETE запросов, было использовано приложение Postman

Я подключила зависимости Spring Security и Oauth2. Модули безопасности и аутентификации.
На порту 8080 работает сервер авторизации KeyCloak, который запускается из контейнера Docker. Из Postman надо сделать POST запрос на адрес localhost:8080/realms/master/protocol/openid-connect/token, прописать данные клиента – имя, пароль, 
client_id (postman), grant_type (password) если мы успешно авторизуемся, то придёт токен и с этим токеном идём на наш ресурс сервер. В токене содержится публичная информация о пользователе. Access token длится 60 сек, с помощью refresh token (длится 1000 сек) можно получить снова access token на сервере авторизации. Для этого надо поменять grant_type на refresh token и добавить refresh token. Если вё ок, должны выдать access token. Password повторно отправлять не нужно.
С этим токеном можно отправлять запросы серверу (через Postman) и получать ответы в зависимости от прав доступа.

Html страницы строились благодаря шаблонизатору Thymelife. Подключила его, добавив зависимость в файл pom.xml. html страницы с кодом лежат в папке ресурсы. 
Структура страниц написана на языке разметки html. Логика описана в классах контроллерах. Методы контроллера на вход принимают объект класса Модель. 
В объект класса Модель с помощью метода addAttribute() передаются данные, которые потом передаются в файлы html и отображаются на веб-странице по соответствующему запросу.

Данный проект является учебным и отражает ту часть знаний, полученных на курсе, которые я хочу в дальнейшем развивать и совершенствовать и, где я хочу стать профессионалом. 
Данный проект будет совершенствоваться и расти вместе со мной и моими знаниями.
