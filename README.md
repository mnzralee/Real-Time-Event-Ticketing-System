# Real-Time Event Ticketing System

## Introduction
The **Real-Time Event Ticketing System** is a multi-event ticket management platform designed to streamline ticket booking processes for both vendors and customers. It incorporates a Command-Line Interface (CLI) for backend simulation and a dynamic web-based frontend powered by Angular and Spring Boot. The system employs advanced producer-consumer implementation to manage ticket releases and purchases efficiently.

---

## Setup Instructions

### Prerequisites
Before setting up the system, ensure the following tools and dependencies are installed on your machine:

- **Java Development Kit (JDK)**: Version 17 or later.
- **Node.js**: Version 16.x or later.
- **Angular CLI**: Version 14 or later.
- **Spring Boot**: Compatible with JDK 17.
- **MySQL Database**: Version 8.x or later.
- **Gson Library**: For JSON serialization in Java.

### Backend Setup (Spring Boot)
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ticketing-system.git
   cd ticketing-system/backend
   ```
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The backend API will be accessible at `http://localhost:8080`.

### Frontend Setup (Angular)
1. Navigate to the frontend directory:
   ```bash
   cd ticketing-system/frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```
4. Access the frontend at `http://localhost:4200`.

---

## Usage Instructions

### CLI Interface
1. **Launch the CLI**:
   ```bash
   java -jar ticketing-system-cli.jar
   ```
2. **Configure the System**:
   - Select **Create new Configurations** to set up ticketing parameters.
   - Select **Load existing Configurations** to load previously saved settings.
3. **Simulation Commands**:
   - `start`: Begin the simulation.
   - `stop`: Stop the simulation.
   - `menu`: Return to the main menu.
   - `quit`: Exit the application.

### Web Interface
1. **Navigate through the Sidebar**:
   - **Home**: Overview of the system.
   - **Configuration**: Set or view system configurations.
   - **Vendor**: Manage ticket releases and vendor details.
   - **Customer**: Monitor ticket purchases.
2. **Real-Time Updates**:
   - View real-time changes in ticket availability through WebSocket integration.

---
