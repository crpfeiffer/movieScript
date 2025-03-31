# 🎬 MovieScript

## 📚 Overview
**MovieScript** is a Java-based application designed to manage and manipulate movie-related data efficiently. The project includes features to:
- Register, update, and delete movies, people, and their roles.
- Manage relationships between movies and the people involved (such as actors, directors, etc.).
- Handle CRUD (Create, Read, Update, Delete) operations via JDBC.
- Provide an intuitive front-end for interacting with the system.

---

## 📂 Project Structure
```
├── director_reg.java           # Java class for registering directors
├── goback.JPG                  # Image for UI navigation
├── index.html                  # Main HTML page
├── movieJdbc.java               # JDBC operations for movies
├── movieScript.sql              # SQL script for database setup
├── movie_del.html               # HTML page to delete movies
├── movie_del.java               # Java class for deleting movies
├── movie_person_role/           # Directory for managing movie-person-role relationships
│   ├── movie_person_role_del.java
│   ├── movie_person_role_jdbc.java
│   ├── movie_person_role_reg.java
│   ├── movie_person_role_upd.java
├── movie_reg.html               # HTML page for movie registration
├── movie_reg.java               # Java class for movie registration
├── movie_upd.html               # HTML page for updating movie details
├── movie_upd.java               # Java class for updating movies
├── personJdbc.java              # JDBC operations for people
├── person_del.java              # Java class for deleting people
├── person_reg.java              # Java class for registering people
├── person_upd.java              # Java class for updating people
├── reviewJdbc.java              # JDBC operations for movie reviews
└── wall-e-dancing-robot-plays-mp3s.jpg  # Fun UI element (optional)
```

---

## ⚙️ Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/crpfeiffer/movieScript.git
cd movieScript
```

### 2. Database Setup
- Create a database using the SQL script provided:
```bash
mysql -u <username> -p < movieScript.sql
```
- Update database connection details in `movieJdbc.java` and other JDBC files:
```java
String url = "jdbc:mysql://localhost:3306/movies";
String user = "your_username";
String password = "your_password";
```

### 3. Compile and Run
- Compile the Java files:
```bash
javac *.java
```
- Run the main Java class:
```bash
java Main
```

---

## 🌟 Features
- **Movie Management:** Register, update, and delete movies.
- **Person Management:** Add, modify, or remove directors, actors, and other participants.
- **Role Assignment:** Assign and manage roles between people and movies.
- **JDBC Integration:** Seamlessly connect with a MySQL database.
- **User Interface:** HTML-based pages for movie and person operations.

---

## 📄 SQL Schema
The SQL schema (`movieScript.sql`) includes:
- Tables for movies, people, and roles.
- Relationship tables to connect movies with associated persons and their roles.

---

## 📸 Screenshots
1. **Home Page:** Overview of movie management options.
2. **Movie Registration:** Add a new movie with relevant details.
3. **Person Role Management:** Assign and modify movie-person relationships.

---

## 📜 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 🚀 Future Enhancements
- Add REST API endpoints for seamless integration.
- Implement user authentication and role-based access.
- Expand UI functionality for a more dynamic experience.

---

Happy Coding! 🎥😊
