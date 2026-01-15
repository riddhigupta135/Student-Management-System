# Teacher Assistant Web Application

A Java + JSP + MySQL web application designed for a mathematics tutor to manage student information, lesson plans, batches, schedules, and fee reminders.

---

## Overview

This tool centralizes multiple administrative tasks that were previously done manually on paper. The application allows the user to:

- Manage batches (class groups)
- Store student information
- Track attendance and fee payment cycles (every 8 classes)
- Write and view lesson plans
- View schedules on a calendar
- Navigate between calendar, student, and batch views

---

## Features

**Student Management**
- Add / edit / delete students
- Store name, email, phone, address, and assigned batch
- Client-side validation for email and phone format

**Batch Management**
- Add / edit / delete batches
- Track batch timing, level, and lesson plans
- Navigate directly to related student lists

**Attendance and Fee Tracking**
- Fee payment reminder every 8 completed classes
- Automatic reset after payment cycle

**Calendar View**
- Displays weekly batch schedule
- Click-through links to batch pages

---

## Tech Stack

- Java (Servlets)
- JSP (Java Server Pages)
- MySQL + JDBC
- HTML/CSS/JavaScript
- Hosted on AWS EC2

---

## Concepts Demonstrated

This project demonstrates several core CS, OOP, web development, and software engineering concepts:

- **MVC Architecture** (Model–View–Controller)
- **DAO Pattern** for database access abstraction
- **Relational Database Design** and SQL CRUD operations
- **Encapsulation + OOP Principles**
- **Collections (HashMap, ArrayList)** for in-memory data handling
- **Sorting and Comparators**
- **User Input Validation (regex)**
- **Exception Handling (try/catch)**
- **Client–Server Communication**
- **State Persistence via MySQL**
- **Basic UI/UX for navigation workflows**
- **Full deployment and hosting workflow**

---

## Architecture

```
src/
    main/
        java/
            studentmanagement/
                dao/
                    BatchDAO.java
                    StudentDAO.java
                model/
                    Batch.java
                    Student.java
                sevice/
                    BatchDAOInterface.java
                    StudentDAOInterface.java
                web/
                    TeacherAssistantServlet.java
            webapp/
                batchList.jsp
                calendarList.jsp
                editBatchForm.jsp
                editStudentForm.jsp
                newBatchForm.jsp
                newStudentForm.jsp
                previousBatchList.jsp
                previousStudentList.jsp
                studentList.jsp
                studentsWithFeeDue.jsp
```

- MVC pattern for separation of concerns
- DAO pattern for database access and CRUD operations
- MySQL for relational data storage
- Two-table schema (Students ↔ Batches)

---

## Database Model

**Entities**
- Student
- Batch

**Relations**
- Many students → One batch

---

## Development Notes

- Used client-side validation for data entry (regex for email/phone)
- Attendance increments update fee status automatically
- DAO layer executes SQL queries via prepared statements
- System tested against common validation and workflow cases

---

## Potential Improvements

Future enhancements could include:

- Authentication (login screen)
- Server-side validation
- Error pages instead of console logs
- Improved UI/UX with modern frontend framework
- Email/SMS fee reminders
- Spring Boot backend rewrite
- Docker deployment

---

## Status

Project originally built as part of the IB Computer Science IA.
Functional and hosted for the client on AWS during evaluation.

---

## Author

Riddhi G.
