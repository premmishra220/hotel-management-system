# Hotel Management System

A full-stack hotel management application built with **React.js** and **Spring Boot**. The project provides features for managing hotel rooms, users, bookings, authentication, and room images.

The application has separate frontend and backend parts. The frontend is responsible for the user interface, while the Spring Boot backend handles the REST APIs, authentication, database operations, and image uploads.

## Features

### User Features

* User registration
* User login
* JWT-based authentication
* Browse available hotel rooms
* Search for rooms
* View room details
* Book rooms
* View booking information
* Find existing bookings
* View and update user profile
* Protected user routes

### Admin Features

* Admin login
* Add new rooms
* Update room information
* Delete rooms
* View all rooms
* Manage hotel bookings
* Update booking information
* View registered users
* Protected admin routes

### Room Management

* Add room details
* Set room type
* Set room price
* Add room description
* Upload room images
* Edit room information
* Delete rooms
* Display available rooms

### Booking Management

* Create a room booking
* Store booking details
* View booking information
* Search for bookings
* Manage bookings from the admin section

### Authentication & Security

* User registration and login
* JWT authentication
* Spring Security
* Protected API endpoints
* Role-based access for admin functionality
* Password handling through the backend

### Image Upload

Hotel room images are uploaded using **Cloudinary**. The backend handles the upload process and the frontend displays the stored image URLs.

---

## Tech Stack

### Frontend

* React.js
* JavaScript
* React Router
* Axios
* CSS
* Bootstrap

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT
* REST APIs
* Maven

### Database

* MySQL
* Hibernate / JPA

### Other Tools & Services

* Cloudinary
* Git
* GitHub
* Postman
* VS Code

---

## Project Structure

```text
hotel-management-system/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/hotel/
│   │   │   │       │
│   │   │   │       ├── controller/
│   │   │   │       │   ├── AuthController.java
│   │   │   │       │   ├── BookingController.java
│   │   │   │       │   ├── RoomController.java
│   │   │   │       │   └── UserController.java
│   │   │   │       │
│   │   │   │       ├── dto/
│   │   │   │       ├── entity/
│   │   │   │       ├── exception/
│   │   │   │       ├── repo/
│   │   │   │       ├── security/
│   │   │   │       ├── service/
│   │   │   │       └── utils/
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
└── frontend/
    ├── public/
    ├── src/
    │   ├── components/
    │   │   ├── admin/
    │   │   ├── auth/
    │   │   ├── BookingRooms/
    │   │   ├── common/
    │   │   ├── Home/
    │   │   └── profile/
    │   │
    │   ├── service/
    │   ├── App.js
    │   ├── App.css
    │   └── index.js
    │
    ├── package.json
    └── package-lock.json
```

---

## Application Architecture

The application follows a simple frontend-backend architecture.

```text
                    User
                     |
                     v
              React Frontend
                     |
                 REST APIs
                     |
                     v
              Spring Boot API
                     |
        +------------+------------+
        |            |            |
        v            v            v
   Spring       Service       Repository
   Security       Layer          Layer
        |            |            |
        +------------+------------+
                     |
                     v
                MySQL Database

                     |
                     v
                 Cloudinary
                Room Images
```

---

## How the Application Works

### Registration

1. User opens the registration page.
2. User enters the required details.
3. Frontend sends the registration request to the backend.
4. Spring Boot processes the request.
5. User information is stored in MySQL.

### Login

1. User enters login credentials.
2. Frontend sends the credentials to the backend.
3. Backend verifies the user.
4. JWT token is generated after successful authentication.
5. Frontend uses the token for protected requests.

### Room Booking

1. User views the available rooms.
2. User opens the room details.
3. User selects the required booking details.
4. Frontend sends the booking request.
5. Backend validates and saves the booking.
6. Booking information is stored in MySQL.

### Admin Room Management

The admin can manage rooms from the admin section.

The admin can:

* Add rooms
* Upload room images
* Update room details
* Delete rooms
* View existing rooms

### Booking Management

Admin users can view and manage bookings through the admin section.

---

## Backend

The backend is developed using **Spring Boot**.

It provides REST APIs for:

* Authentication
* Users
* Rooms
* Bookings

The backend is organized into controllers, services, repositories, entities, DTOs, security configuration, and utility classes.

### Controllers

The controller layer receives HTTP requests from the frontend and returns the required response.

Main controllers:

```text
AuthController
BookingController
RoomController
UserController
```

### Service Layer

The service layer contains the main application logic.

Services include:

```text
BookingService
RoomService
UserService
CustomUserDetailsService
CloudinaryService
```

### Repository Layer

Spring Data JPA repositories are used for database operations.

Repositories include:

```text
BookingRepository
RoomRepository
UserRepository
```

### Entities

The main database entities are:

```text
User
Room
Booking
```

---

## Authentication

Authentication is handled using **Spring Security and JWT**.

The login process generates a JWT token which is used for authenticated requests.

The backend checks the token before allowing access to protected endpoints.

Admin-related functionality is restricted according to the user's role.

---

## Database

The application uses **MySQL** as the database.

Spring Data JPA and Hibernate are used to communicate with MySQL.

Main entities:

```text
User
Room
Booking
```

The database stores user information, room information, and booking records.

---

## Cloudinary

Cloudinary is used to store hotel room images.

The backend contains a `CloudinaryService` which handles image upload operations.

Only the required image information is used by the application so that room images can be displayed on the frontend.

---

## Frontend

The frontend is developed using React.js.

Main sections include:

```text
Home
Authentication
Room Booking
Room Details
Bookings
Profile
Admin
```

The frontend communicates with the Spring Boot backend using REST API requests.

API-related code is maintained inside the service section.

---

## API Communication

The frontend sends requests to the Spring Boot backend for operations such as:

```text
User Registration
User Login
Room Listing
Room Details
Room Creation
Room Update
Room Deletion
Booking Creation
Booking Management
Profile Management
```

For production deployment, the frontend API URL should be configured using an environment variable instead of using a localhost URL.

---

## Local Setup

### Requirements

Install the following before running the project:

* Java
* Maven
* Node.js
* npm
* MySQL
* Git

---

## Clone the Repository

```bash
git clone https://github.com/premishra220/hotel-management-system.git
```

Move into the project directory:

```bash
cd hotel-management-system
```

---

# Backend Setup

Move to the backend folder:

```bash
cd backend
```

Configure the MySQL database and required environment variables.

### Run with Maven

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or if Maven is installed globally:

```bash
mvn spring-boot:run
```

The backend runs locally on:

```text
http://localhost:8081
```

---

# Frontend Setup

Open another terminal.

Move to the frontend folder:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the React development server:

```bash
npm start
```

The frontend normally runs on:

```text
http://localhost:3000
```

---

## Environment Variables

Sensitive configuration should not be committed to GitHub.

Backend configuration should be provided through environment variables in production.

Example:

```text
DATABASE_URL=
DATABASE_USERNAME=
DATABASE_PASSWORD=

CLOUDINARY_CLOUD_NAME=
CLOUDINARY_API_KEY=
CLOUDINARY_API_SECRET=
```

The frontend should use an environment variable for the backend API URL.

Example:

```text
REACT_APP_API_URL=
```

The actual variable name should match the configuration used by the frontend project.

---

## Security

The following information should never be committed to the repository:

* Database passwords
* JWT secrets
* Cloudinary API secrets
* API keys
* Production credentials
* Private configuration files

Use environment variables for sensitive information.

---

## Deployment

The React frontend can be deployed using **Vercel**.

The Spring Boot backend should be deployed separately on a service that supports Java/Spring Boot applications.

A typical production setup is:

```text
React Frontend
      |
      v
    Vercel
      |
      v
Spring Boot Backend
      |
      v
  Cloud MySQL
      |
      +---- Cloudinary
```

Before deployment:

* Configure production database credentials.
* Configure Cloudinary credentials.
* Set the production backend URL in the frontend.
* Configure CORS for the deployed frontend domain.
* Make sure no secrets are committed to GitHub.
* Test the production APIs before connecting the frontend.

---

## Build

### Frontend

To create a production build:

```bash
npm run build
```

### Backend

To build the Spring Boot application:

```bash
mvn clean package
```

On Windows:

```bash
mvnw.cmd clean package
```

---

## Testing

The backend APIs can be tested using tools such as:

* Postman
* Browser
* Frontend application

The main areas to test before deployment are:

* Registration
* Login
* JWT authentication
* Room listing
* Room details
* Room creation
* Room update
* Room deletion
* Booking creation
* Booking search
* Admin booking management
* Profile update
* Image upload

---

## Future Improvements

The project can be extended with features such as:

* Online payment
* Email booking confirmation
* Booking cancellation
* Room availability calendar
* Improved search and filtering
* Admin dashboard statistics
* Booking reports
* Customer reviews
* Email notifications

---

## Project Status

The project is currently being prepared for production deployment.

Frontend deployment: **Vercel**

Backend deployment: **To be configured**

Database: **MySQL**

Image storage: **Cloudinary**

---

## Author

**Prem Prakash Mishra**

B.E. Student

GitHub:
https://github.com/premishra220

LinkedIn:
https://www.linkedin.com/in/prem-prakash-mishra-20516a288/
