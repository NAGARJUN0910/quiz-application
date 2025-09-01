# quiz-application
Interactive Quiz Application built with Java, Spring Boot, Thymeleaf, and MySQL. Test your knowledge with multiple-choice quizzes, track your scores, and enjoy a responsive and user-friendly interface. Includes admin panel for managing quizzes and questions.

## Features
- **User Functionality:**
  - User registration and login
  - Take multiple-choice quizzes
  - View quiz results with score, percentage, and performance feedback
  - Responsive and modern interface with animations

- **Admin Functionality:**
  - Create, edit, and delete quizzes
  - Add, edit, and delete questions for each quiz
  - View all users and their quiz scores
  - Admin dashboard for quick actions and stats

---

## Technologies Used
- **Backend:** Java, Spring Boot, Spring Security  
- **Frontend:** Thymeleaf, Bootstrap 5, HTML, CSS, JavaScript  
- **Database:** MySQL  
- **Build Tool:** Maven  

---

## Screenshots
_Add screenshots of your application here to showcase the interface_  

---
## Installation & Setup

Follow these steps to run the Quiz Application locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/<YOUR_USERNAME>/quiz-application.git

2.**Navigate to the project directory:**
   cd quiz-application

3.**Open in IDE:**
   -Open the project as a Maven project in IntelliJ IDEA, Eclipse, or any Java IDE of your choice.

4.**Configure the database:**
   -Make sure you have MySQL installed.
   -Create a new database:
   - CREATE DATABASE quiz_app_db;
   - Update src/main/resources/application.properties with your database credentials:
         #Application.Properties
         spring.datasource.url=jdbc:mysql://localhost:3306/quiz_app_db
         spring.datasource.username=YOUR_DB_USERNAME
         spring.datasource.password=YOUR_DB_PASSWORD
         spring.jpa.hibernate.ddl-auto=update
         spring.jpa.show-sql=true
         spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
         server.port=8080

  5.**Build the project:**   
      -mvn clean install

  6.**Run the application:** 
      -mvn spring-boot:run
      or
      -Or run the main class (QuizApplication.java) directly from your IDE.

  7.**Access the application:**   
       - Open your web browser and go to:
       - http://localhost:8080
       - You should see the Quiz Application login page.

  8.**Optional – Seed initial data (Admin account):**  
       - You can add an admin user directly in the database to access the admin panel:
       -  INSERT INTO users (username, password, role) VALUES ('admin', '<bcrypt_password>', 'ADMIN');
       - Replace <bcrypt_password> with a bcrypt-encrypted password. You can generate one using an online bcrypt generator.

Usage
      **User:**
       - Sign up or log in
       - Browse available quizzes
       - Take quizzes and track scores
      **Admin:**
       - Log in with admin credentials
       - Create and manage quizzes
       - Add, edit, or delete questions
       - View all users’ scores







