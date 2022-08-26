<!-- PROJECT LOGO -->
<br />
<a id="top"></a>
<div align="center">
<h3 align="center">Software Architecture Project</h3>

  <p align="center">
    Taxi Booking Main Service
    <br />
    <br />
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#dev-tools">Dev Tools</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
<a id="about-the-project"></a>

## About The Project

Taxi booking main service, this service is a web service that allows users to book a taxi;
Drivers can accept or reject a booking request;
Call center bookings for customers.

The service communicates with other services such as: 
**[socket-service](https://github.com/hcmus-clc-ktpm19/taxi-booking-backend/tree/main/socket-service)**, 
**[sms-service](https://github.com/hcmus-clc-ktpm19/taxi-booking-backend/tree/main/sms-service)** 
via RabbitMQ.

<a id="built-with"></a>
### Built With
- [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MongoDB](https://www.mongodb.com/)
- [Redis](https://redis.io/)
- [ELK Stack](https://www.elastic.co/)
- [RabbitMQ](https://www.rabbitmq.com/)

<a id="dev-tools"></a>
### Dev Tools
- [Git](https://git-scm.com/)
- [GitHub](https://github.com/)
- [IntelliJ IDEA Ultimate Edition](https://www.jetbrains.com/idea/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)
- [Docker](https://www.docker.com/)
- [Heroku](https://www.heroku.com/)
- [Postman](https://www.getpostman.com/)
- [Swagger](https://swagger.io/)
- [Trello](https://trello.com/)
- [Discord](https://discord.com/)

<!-- GETTING STARTED -->
<a id="getting-started"></a>

## Getting Started

Download the project from [GitHub](https://github.com/hcmus-clc-ktpm19/taxi-booking-backend.git) and run the project.

<a id="prerequisites"></a>

### Prerequisites

- Install all dependencies (IntelliJ IDEA will automatically install dependencies)
   ```text
  Java 11
  Maven
  Spring 2.6.9
  Intellij
  Docker
   ```

<a id="installation"></a>

### Installation

1. Clone the repo
   ```shell
   git clone https://github.com/hcmus-clc-ktpm19/taxi-booking-backend.git
   ```
2. Run docker compose in each sub-folder of wiber-back/etc/docker by typing:
   ```shell
   docker-compose up -d
   ```
3. Create a secret.yaml file in src/main/resources and add your configuration, e.g:
   ```yaml
   spring:
      data:
        mongodb:
          authentication-database: 'admin'
          username: 'admin'
          password: 'admin'
          database: 'wiber-db'
          host: 'localhost'
          port: '27017'
          auto-index-creation: 'true'
      rabbitmq:
        username: 'admin'
        password: 'admin'
   
   jwt:
      secret-key: 'secret''
   ```
<!-- CONTACT -->
<a id="contact"></a>

## Contact

Nguyễn Đức Nam - namworkmc@gmail.com<br>
Lê Ngọc Minh Nhật- lengocminhnhatntp@gmail.com<br>
Lâm Thịnh Phát - lamthinhphat2001@gmail.com<br>
Hoàng Hữu Giáp - hoanghuugiap241@gmail.com<br>

<p align="right">(<a href="#top">back to top</a>)</p>