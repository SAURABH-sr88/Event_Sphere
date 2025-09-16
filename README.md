📅 EventSphere – Event Booking Application

EventSphere is a full-stack event booking web application built with Java Spring Boot, MongoDB, React.js, and JWT authentication. 
It offers a seamless experience for users to browse events, register, log in, and book seats through a secure and responsive interface.
This project demonstrates practical skills in modern web development and showcases the ability to build end-to-end features with robust technologies.

✅ Key Highlights

✔ Complete Full-Stack Application
Built using industry-standard technologies with clearly separated backend and frontend layers.
✔ Secure Authentication with JWT
Implements token-based authentication ensuring safe and reliable user sessions.
✔ Role-Based Access Control
Includes admin and user roles with proper management and security rules.
✔ Real-Time Booking System
Users can view event details and book seats with live availability updates.
✔ RESTful API Design
Well-structured APIs for authentication, event browsing, and booking.
✔ Modern Java Practices
Utilizes Spring Boot, MongoDB integration, and Lombok to create maintainable and scalable backend code.
✔ Interactive and Responsive UI
Frontend built with React, using Axios for API communication, with smooth navigation and user-friendly forms.
✔ Environment-Based Configuration
Supports environment variables for database connection and security settings, making the app flexible for different deployment scenarios.
✔ Developer-Friendly Setup
Includes clear instructions and default admin account for testing and fast onboarding.


📂 Tech Stack

Backend: Java, Spring Boot, MongoDB, JWT, Lombok
Frontend: React.js, Axios, React Router
Build Tools: Maven (backend), npm/yarn (frontend)

📂 Project Structure

EventSphere/
├── backend/           # Java Spring Boot backend
│   ├── src/           # Source files, models, controllers, security
│   └── resources/    # Configuration files (application.yml)
├── frontend/          # React frontend
│   ├── src/           # Components, pages, utilities
├── README.md          # Project documentation

🔑 Setup Environment Variables

Create a .env file in the project root or set environment variables:

SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/eventsphere
EVENTSPHERE_JWT_SECRET=your_secret_key
EVENTSPHERE_JWT_EXPIRATION_MS=86400000

🚀 Running the Backend..
     cd backend
     mvn clean install
     mvn spring-boot:run
  Backend will run at http://localhost:8080.

🚀 Running the Frontend
     cd frontend
     npm install
     npm start
  Frontend will run at http://localhost:3000 and proxy requests to the backend.

✅ Default Admin Credentials
     Username: admin
     Password: Admin@123
  ⚠️ For production, change or remove these credentials immediately.


🚀 Getting Started

Setup MongoDB and Java environment.
Run the backend server with Maven.
Install dependencies and start the React frontend.
Test the application using the seeded admin account.

📚 Learning Outcomes

✔ Understanding of secure authentication workflows
✔ Building and consuming RESTful APIs
✔ Integrating MongoDB with backend services
✔ Managing state in React applications
✔ Environment-driven configurations for flexible deployments

📬 Contact

Feel free to explore, fork, and contribute.
Questions and suggestions are welcome!
