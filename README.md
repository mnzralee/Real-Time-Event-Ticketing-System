# Real-Time Event Ticketing System with Producer-Consumer Pattern

## Introduction
The Real-Time Event Ticketing System is an event ticket management platform designed to streamline ticket booking processes for both vendors and customers. It incorporates a Command-Line Interface (CLI) for backend simulation and a dynamic web-based frontend powered by Angular and Spring Boot. The system employs advanced producer-consumer pattern implementation to manage simultaneous ticket releases and purchases efficiently.

---

## Class Diagram
Below is the class diagram for the system. It shows the primary classes and their relationships:


<p align="center">
  <img src="UML%20class.png" width="500px"/>
</p>



### Explanation
1. **Customer**: Represents the end-user purchasing tickets. It interacts with the `TicketPool` to retrieve tickets.
2. **Vendor**: Represents the ticket issuer, releasing tickets to the `TicketPool`.
3. **TicketPool**: Centralized storage and management for tickets, handling concurrent access.
4. **TicketingLog**: Records all ticket-related activities, such as releases and retrievals.
5. **Ticket**: Represents an individual ticket, with attributes like ID, name, and price.

### Key Relationships
- `Vendor` releases tickets to the `TicketPool`.
- `Customer` retrieves tickets from the `TicketPool`.
- `TicketPool` manages `Ticket` objects and logs activities using the `TicketingLog`.

---

## Sequence Diagram
The sequence diagram illustrates the interaction flow between the components:

<p align="center">
   <img src="Sequence%20diagram.png" width="500px"/>
</p>


### Key Steps
1. **Vendor releases tickets**:
   - Calls `releaseTickets()` on `TicketPool`.
   - `TicketPool` logs the release in `TicketingLog`.
2. **Customer retrieves tickets**:
   - Calls `retrieveTickets()` on `TicketPool`.
   - `TicketPool` checks availability and either provides tickets or notifies the customer if unavailable.
   - Logs the retrieval attempt and successful retrieval (if applicable).

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
   - **Home**: Simulation control panel and Log Display.
   - **Configuration**: Set or load system configurations.
   - **Vendor**: Manage vendors.
   - **Customer**: Manage customer.
2. **Real-Time Updates**:
   - View real-time logs in ticket release/purchase through WebSocket integration.

---

## API Endpoints
| Endpoint               | Method | Description                  |
|------------------------|--------|------------------------------|
| `/api/vendors`         | POST   | Add a new vendor             |
| `/api/customers`       | POST   | Add a new customer           |
| `/api/config`          | POST   | Add a new configuration      |
| `/api/config`          | GET    | Retrieve configuration       |
| `/api/logs`            | GET    | Retrieve all logs            |
| `/api/logs`            | DELETE | Clear all logs               |

---


---

## Conclusion
By following the steps outlined in this guide, you can implement a fully functional Real-Time Event Ticketing System that adheres to OOP principles and effectively demonstrates the Producer-Consumer pattern. The provided diagrams and explanations aim to streamline the implementation process and clarify system interactions.

