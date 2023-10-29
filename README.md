<!-- ABOUT THE PROJECT -->
## About The Project

The primary aim of this project is to enhance the learning experience by incorporating a PK element, 
thereby stimulating learners' interest and motivation through competition and confrontation. 
The team strive to transform brushing questions, known for their effectiveness in cultivating problem-solving skills, 
into a dynamic, engaging process. By introducing this pattern, our team aspire to improve learners' overall academic performance, 
foster a positive attitude towards education, and develop multifaceted skills applicable beyond the confines of a classroom.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

This project is built with Vue and Spring-Boot

* [![Vue][Vue.js]][Vue-url]
* [![Spring-Boot][Spring-Boot.io]][Spring-Boot-url]


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

Follow these steps to set up the project locally. This guide will walk you through setting up both the Vue frontend and the Spring Boot backend.

### Prerequisites

Ensure you have the following tools installed on your machine:

**1. Node.js & npm:**
   * Download and install [Node.js].
   * Verify the installation by running:
     ```sh
     node -v
     npm install
     ```
**2. Java JDK & Maven:**
   * Download and install [Java-JDK].
   * Download and install [Maven].
   * Verify the installations by running:
     ```sh
     java -version
     mvn -version
     ```

### Installation

**Setting up the Backend (Spring Boot)**

**1. Clone the repository:**
  ```sh
  git clone https://github.com/Qinzs/pkweb_backend.git
  ```

**2. Navigate to the backend directory:**
  ```sh
  cd pkweb_backend
  ```

**3. Build the project:**
  ```sh
  mvn clean install
  ```
  
**4. Run the Spring Boot application:**
  ```sh
  mvn spring-boot:run
  ```

The backend server should now be running on http://localhost:8080.

**Setting up the Frontend (Vue.js):**

**1. Clone the repository:**
  ```sh
  git clone https://github.com/Qinzs/coding_website.git
  ```

**2. Navigate to the frontend directory:**
  ```sh
  cd coding_website
  ```

**3. Install the required npm packages:**
  ```sh
  npm install
  ```
  
**4. Serve the Vue app:**
  ```sh
  npm run serve
  ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- Libraries and their versions: -->
## Libraries and their versions

**org.json:json:20210307**
   * Library: org.json
   * Version: 20210307
   * Description: This library is used for working with JSON data in Java. It provides functionality for creating, parsing, and serializing JSON objects.

**org.json:json:20210307**
   * Library: org.springframework.boot
   * Version: 3.1.2
   * Description: This is a Spring Boot starter dependency used for building web applications. It includes many common components and libraries for web application development.

**com.microsoft.sqlserver:mssql-jdbc:11.2.3.jre17**
   * Library: com.microsoft.sqlserver
   * Version: 11.2.3.jre17
   * Description: This is the Java JDBC driver for Microsoft SQL Server. It enables Java applications to communicate with and manage SQL Server databases.

**org.springframework.boot:spring-boot-starter-websocket:3.1.2**
   * Library: org.springframework.boot
   * Version: 3.1.2
   * Description: This Spring Boot starter dependency is used for WebSocket support, allowing real-time bidirectional communication in web applications.

**org.springframework.boot:spring-boot-starter-test:3.1.2 (test)**
   * Library: org.springframework.boot
   * Version: 3.1.2
   * Description: This is a testing library provided by Spring Boot. It is typically used for writing unit and integration tests for Spring Boot applications.

**org.springframework.boot:spring-boot-starter-data-jpa:3.1.2**
   * Library: org.springframework.boot
   * Version: 3.1.2
   * Description: This Spring Boot starter is used for simplifying the integration of Java Persistence API (JPA) data access into Spring Boot applications.

**mysql:mysql-connector-java:8.0.26**
   * Library: mysql
   * Version: 8.0.26
   * Description: This library provides the Java JDBC driver for MySQL databases, enabling Java applications to connect to, query, and manage MySQL databases.

**jakarta.persistence:jakarta.persistence-api:3.1.0**
   * Library: jakarta.persistence
   * Version: 3.1.0
   * Description: This library includes the Jakarta Persistence API, which is a specification for Java persistence. It is often used in conjunction with specific JPA implementations.

**org.springframework.boot:spring-boot-starter-jdbc:3.1.2**
   * Library: org.springframework.boot
   * Version: 3.1.2
   * Description: This Spring Boot starter provides support for Java Database Connectivity (JDBC) operations in Spring Boot applications.

**org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2**
   * Library: org.mybatis.spring.boot
   * Version: 3.0.2
   * Description: This is a Spring Boot starter for integrating MyBatis, a Java persistence framework, into Spring Boot applications for database access.

**org.springframework.boot:spring-boot-starter-mail:3.1.2**
   * Library: org.springframework.boot
   * Version: 3.1.2
   * Description: This Spring Boot starter is used for sending and receiving email in Spring Boot applications.

**com.fasterxml.jackson.core:jackson-databind:2.15.2**
   * Library: com.fasterxml.jackson.core
   * Version: 2.15.2
   * Description: This library is part of the Jackson library, which is used for serializing and deserializing JSON data in Java. 
   It is a popular choice for working with JSON data in Java applications.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- Functionalities -->
## Functionalities

**Sign up/in:**
1. Users can sign up for a new account by inputting email, username and password on the sign up page.
2. Users can input username and password to log in account on log in page
3. Users can reset their password through the verification of their email code and input a new password on the forget password page.

**Profile:**
1. Users can check private data on profile pages. 
2. Users can see recent three matches on profile page
3. Users can see three solutions to questions on the profile page.
4. Users can see three question posts on the profile page.

**Home:**
1. Users can see all the questions on community page.
2. Users can see details and comments of each question.
3. Users can make comments and answers in detail in the pop up window of each question.
4. Users can post questions or discussions on the community page.

**Match History:**
1. Users can check all history matches on the Match History page.
2. Users can see details of each match through Detail.

**Matching:**
1. Users can type code into the code editor during the match time. 
2. Users can submit code when the match ends.

**Chat:**
1. Users can chat with their friend through chatting page
2. Users can send messages in group chat on Chat page


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Spring-Boot.io]: https://images.app.goo.gl/76P7bNiaZTevDUM8A
[Spring-Boot-url]: https://spring.io/
[Node.js]: https://nodejs.org/en
[Java-JDK]: https://www.oracle.com/java/technologies/downloads/#java11
[Maven]: https://maven.apache.org/download.cgi