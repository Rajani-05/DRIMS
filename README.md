# 🔬 DRIMS — Data Research Information Management System

**DRIMS** is an enterprise-grade Data Research Information Management System developed with **Java, Spring Boot, Hibernate ORM, MySQL, React.js, and REST APIs**. It is designed to optimize database operations, streamline research data cataloging, and deliver high-speed data retrieval.

---

## 🚀 Key Highlights & Features

- **Scalable Spring Boot Backend**: Built with Spring Boot 3.x, REST APIs, and Jackson JSON serialization.
- **Optimized Hibernate ORM & MySQL**: Includes index mapping (`@Index`), batching (`batch_size=30`), custom JPQL/Criteria queries, and zero-N+1 query tuning.
- **Zero-Dependency Quick Run**: Configured for MySQL 8 with an automatic fallback to H2 embedded database for instant zero-config testing.
- **Modern React.js Interface**: High-performance single-page web app built with React, Lucide icons, glassmorphism UI, metric dashboards, project creation modal, and instant search/filtering.
- **RESTful Endpoints**: Full CRUD for research projects, datasets catalog with download counters, researcher rosters, and analytics metrics.

---

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot 3.2.3, Spring Data JPA, Hibernate ORM, MySQL Connector, H2 Database, Maven
- **Frontend**: React.js 18, Vite 5, JavaScript (ES6+), Vanilla CSS (Custom Design Tokens), Lucide React
- **Database**: MySQL 8.x (Production) / H2 In-Memory (Development/Testing)

---

## 📂 Project Directory Structure

```
DRIMS/
├── backend/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/drims/
│       │   │   ├── DrimsApplication.java
│       │   │   ├── config/          (CorsConfig, DataInitializer)
│       │   │   ├── controller/      (ProjectController, DatasetController, ResearcherController, AnalyticsController)
│       │   │   ├── model/           (ResearchProject, Dataset, Researcher, Publication)
│       │   │   └── repository/      (ProjectRepository, DatasetRepository, ResearcherRepository, PublicationRepository)
│       │   └── resources/
│       │       └── application.properties
├── frontend/
│   ├── package.json
│   ├── index.html
│   ├── vite.config.js
│   └── src/
│       ├── main.jsx
│       ├── App.jsx
│       └── index.css
└── README.md
```

---

## ⚡ Execution Instructions

### 1. Launching the Backend (Spring Boot)

```bash
cd DRIMS/backend
mvn spring-boot:run
```
> The API will start at **`http://localhost:8080/api`**. Seed demo data is automatically populated into the database on first run.

### 2. Launching the Frontend (React.js)

```bash
cd DRIMS/frontend
npm install
npm run dev
```
> The React interface will launch at **`http://localhost:3000`**.

---

## 🌐 REST API Endpoints Overview

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/projects` | Fetch all projects or filter by search/status |
| `POST` | `/api/projects` | Create a new research project |
| `PUT` | `/api/projects/{id}` | Update existing project details |
| `DELETE`| `/api/projects/{id}` | Delete a project |
| `GET` | `/api/datasets` | Retrieve dataset catalog |
| `POST` | `/api/datasets/{id}/download` | Increment dataset download metric |
| `GET` | `/api/researchers` | List all research faculty & staff |
| `GET` | `/api/analytics/dashboard` | Fetch high-level system metrics & funding summary |

---

## 🐙 Push Code to GitHub (`https://github.com/Rajani-05/DRIMS`)

Run the following commands from inside the `DRIMS` folder to initialize and push the repository:

```bash
cd c:\Users\Rajani_21\OneDrive\Desktop\DRIMS

# 1. Initialize Git repository
git init

# 2. Add remote repository
git remote add origin https://github.com/Rajani-05/DRIMS.git

# 3. Stage all project files
git add .

# 4. Commit changes
git commit -m "feat: Add DRIMS full-stack system with Spring Boot backend, Hibernate ORM, and React.js frontend"

# 5. Rename branch to main & push
git branch -M main
git push -u origin main --force
```
