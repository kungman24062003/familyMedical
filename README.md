# familyMedical

how to run 

Frontend Setup (Vue)

Open another terminal and go to the frontend folder:

<pre> cd frontend </pre>


Install dependencies:

<pre> npm install </pre>


Run the development server:

<pre> npm run dev </pre>


 Backend Setup (Spring Boot)

1. Go to the backend folder:

<pre>cd backend</pre>
Update your src/main/resources/application.properties file with your database credentials:

<pre> 

spring.datasource.url=jdbc:mysql://localhost:3306/medical_app
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
</pre>

Run the Spring Boot application:


<pre> mvn spring-boot:run </pre>



Cách để xem api documentation 

Chạy backend 

Truy cập vào http://localhost:8080/v3/api-docs

Copy và paste JSON vào https://editor.swagger.io để xem