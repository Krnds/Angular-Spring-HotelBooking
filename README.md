# Hotel Booking App

RESTful application made for reservations of hotels with clients. Made with a MySQL database, a Spring backend (Spring security, Spring data JPA and Spring Boot) and an Angular frontend (Angular 12, Bootstrap 5 and material icons).

## API routes

- authentification `api/login`  redirects to welcome-page
- hotels :  `api/hotel`
- clients : `api/client`
- reservations : `api/resa`

each route contains these HTTP requests :
```
get
get/{id}
post
put/{id}
delete/{id}
```

- search by hotel or by client with the route `/resa/search?hotel(or client)={id}`

## Requirements

- MySQL
- Java JDK 16
- Angular CLI >= 12.2.8

## Deployemement

- run `npm install` to install all dependencies
- open app with Angular CLI `ng serve --open`

## Login into app
When the app is running, connect to app using credentials : `admin` for ID and `1234` for password.

## Screenshots

### Login page

![Login](/screenshots/login.png?raw=true)

### Welcome page

![Welcome](/screenshots/welcome.png?raw=true)

### Add a new client
![New client](/screenshots/add-client.png?raw=true)

### Reservation route
![Search By Hotel](/screenshots/resa-filterby.png?raw=true)

### Error handling
![Error hotel](/screenshots/error-handling_1.png?raw=true)
![Error Reservation](/screenshots/error-handling_2.png?raw=true)

## TODO

- [ ] Add SpringBoot database auto initialization
- [ ] Add timeout for Bootstrap alerts and messages
- [ ] Add some new JUnit5 tests