# Project Tracker 

This is a simple Android application that helps students register their projects and upload GitHub links.
It also allows admin users to manage and view users, projects, and uploads.

# Features

- User Registration and Login
- Role-based access (Admin or Student)
- Submit project information (project name, group name, and description)
- Upload GitHub project link
- Admin can view:
    - All users
    - All uploaded GitHub links
    - All submitted projects

# Technologies Used

- Android (Kotlin)
- Retrofit (for HTTP connection to backend)
- SQLite (for local database support)
- Spring Boot (backend API)

# How to Run

1. Get the project or download the ZIP.
2. Open the project in Android Studio.
3. Make sure your backend server (Spring Boot) is running.
4. Change the base URL in `RetrofitClient.kt` to match your backend IP.
5. Run the app on your emulator or Android device.

# Backend API Endpoints (Spring Boot)

- POST `/api/users/register` – Register new user
- POST `/api/users/login` – Login user
- POST `/api/projects` – Submit project
- GET `/api/projects` – Get all projects
- POST `/api/uploads` – Upload GitHub link
- GET `/api/uploads` – View all uploads
- GET `/api/users` – View all users

# Local Database (SQLite)

SQLite is used to store user registration and login data locally for offline access.

# Note

- You must be connected to the internet to use the Retrofit features.
- The app supports both online (Retrofit) and offline (SQLite) login/registration.

# Developer

- This app was developed as part of a university project for learning purposes.

