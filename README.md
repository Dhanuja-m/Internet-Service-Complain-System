# Internet Service Complaint System

A simple **Java-based Internet Service Complaint Management System** that helps users register and manage internet service complaints.

## Features

* User Registration
* User Login
* Add Complaint
* View Complaints
* Search Complaint
* Update Complaint Status
* Delete Complaint

## Technologies Used

* Java
* MySQL
* JDBC
* Data Structures (ArrayList)
* VS Code

## Database

The project uses MySQL with two main tables:

* `users` – Stores user details
* `complaints` – Stores complaint details

## Project Structure

```text
InternetComplaintSystem/
├── Main.java
├── User.java
├── Complaint.java
├── UserDAO.java
├── ComplaintDAO.java
├── DBConnection.java
└── complaint.sql
```

## How to Run

1. Create the MySQL database using `complaint.sql`.
2. Update the MySQL username and password in `DBConnection.java`.
3. Add MySQL Connector/J to the Java project.
4. Compile and run `Main.java`.

## Objective

The main objective of this project is to reduce manual complaint management and provide a simple system for registering, tracking, and managing internet service complaints.

## Future Enhancements

* GUI/Web interface
* Admin and Customer dashboards
* Priority-based complaint handling
* Email notifications
* Complaint history and reports
