# 🐾 PawsLink

> Empowering NGOs and volunteers to protect and care for stray animals through open-source technology.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java: 21](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blueviolet.svg)](https://reactjs.org/)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

## 📖 About The Project

Turkey is home to hundreds of thousands of stray animals. While there are incredible grassroots movements, local NGOs, and volunteers working tirelessly to feed, rescue, and provide medical care for them, they often lack the technical infrastructure to coordinate effectively.

**PawsLink** is an open-source platform designed to bridge this gap. It provides a robust backend and an intuitive frontend for:
- 🗺️ **Location-Based Rescues:** Tracking stray animals in need of urgent medical care using geocoding.
- 🏥 **Veterinary & Shelter Coordination:** Allowing NGOs to manage shelter capacities and veterinary records.
- 🤝 **Volunteer Matching:** Connecting local volunteers with urgent tasks (e.g., transportation, feeding routes).
- 🧠 **AI-Powered Triage:** (Upcoming) Utilizing LLMs like Claude to categorize and prioritize emergency reports submitted by the public.

## 🏗️ Architecture & Tech Stack

This project uses a modern, scalable, and fully open-source-friendly stack:

### Backend
- **Language/Framework:** Java 21, Spring Boot 3
- **Database:** PostgreSQL (with PostGIS for spatial data & location querying)
- **Caching:** Redis (for session management and high-frequency queries)
- **Security:** Spring Security with JWT
- **Storage:** MinIO / AWS S3 for animal photos and medical documents

### Frontend
- **Framework:** React with TypeScript
- **Styling:** Tailwind CSS
- **State Management:** React Query / Redux Toolkit
- **Maps:** Leaflet / React-Leaflet for interactive animal tracking

### DevOps
- Docker & Docker Compose for easy local setup
- GitHub Actions for CI/CD pipelines

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites
- Docker & Docker Compose
- Java 21 (if running locally outside Docker)
- Node.js (v18+)

### Installation

1. **Clone the repo**
   ```sh
   git clone https://github.com/sserhatacarr/PawsLink.git
   ```

2. **Start the infrastructure (Database, Redis, MinIO, etc.)**
   ```sh
   cd PawsLink
   docker-compose up -d
   ```

3. **Run the Backend (Spring Boot)**
   ```sh
   cd backend
   ./mvnw spring-boot:run
   ```

4. **Run the Frontend (React)**
   ```sh
   cd frontend
   npm install
   npm start
   ```

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

Whether you are a developer, designer, or just an animal lover who wants to help with documentation, we welcome you!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

## 📫 Contact

Project Link: [https://github.com/sserhatacarr/PawsLink](https://github.com/sserhatacarr/PawsLink)