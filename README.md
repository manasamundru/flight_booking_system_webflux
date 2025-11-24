# Flight Booking System – Spring WebFlux + MongoDB

# Flight Booking System – Requirements

## Requirements

1. **User can search for a Flight** based on:
   - Date / Time
   - From place / To place
   - One way or Round trip

2. **Each Search Result should display:**
   - Flight Date / Time
   - Airline Name / Logo
   - Price (One-way & Round-Trip)

3. **User should be able to select a flight and complete Ticket Booking** by providing:
   - Name and Email ID
   - Number of seats to book
   - Passenger details (NAME : GENDER : AGE)
   - Meal option (Veg / Non-Veg)
   - Seat Numbers selected

4. **On successful Ticket Booking:**
   - A unique PNR number must be generated
   - User should be able to download the ticket
   - Booking allowed only for Logged-In Users

5. **Using Email ID, user should be able to:**
   - View booking history
   - Cancel a ticket (only if cancellation is done 24 hours before journey)

6. **Using PNR number:**
   - User should be able to view booked ticket details


   
## Features Implemented

### 1. Airline Management
- Add a new airline

### 2. Flight Inventory Management
- Add flights under an airline
- Auto-set available seats based on total seats
- Search flights by:
  - From place
  - To place
  - Journey date

### 3. Booking System
- Book tickets for a flight
- Accept multiple passengers
- Generate PNR
- Seat validation (availability, duplicate seats, already booked seats)
- Auto-decrease available seats on booking

### 4. Booking Cancellation
- Cancel booking using PNR
- Auto-restore seats after cancellation
- Cancellation allowed only before 24 hours of journey

### 5. Booking History
- Fetch all bookings made by a user email

### 6. Exception Handling
- Global exception handler
- Returns 404 Not Found, 400 Bad Request

## 📡 APIs Implemented

### Airline APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/flight/airline/add` | Add new airline |

### Flight Inventory APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/flight/inventory` | Add flight inventory |
| POST | `/api/flight/search` | Search flights |

### Booking APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/flight/booking/{flightId}` | Book ticket |
| GET | `/api/flight/ticket/{pnr}` | Get booking details by PNR |
| GET | `/api/flight/booking/history/{email}` | Get booking history |
| DELETE | `/api/flight/booking/cancel/{pnr}` | Cancel booking |


## Tech Stack Used
- **Java 17**
- **Spring Boot 3+**
- **Spring WebFlux**
- **Reactive MongoDB (Spring Data MongoDB)**
- **Maven**
- **Lombok**
- **MongoDB Atlas**
