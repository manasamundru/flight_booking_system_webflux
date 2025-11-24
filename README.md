Requirments: 
1.User can search for a Flight based on date/time, from place/to place, one way or round trip 
2. Each Search result need to display Flight Date/time, Airline Name/Logo, Price(to & round trip – TBD) 
3. From Search results, User should be able to select a specific Flight and go ahead and complete 
Ticket Booking by providing below details 
• Name and Email ID 
• Number of seats to book. 
• Details of each passenger (NAME:GENDER:AGE) 
• Opt for Meal(Veg/Non veg) 
• Select Seat Number(s) 
4. On successful Ticket Booking, PNR number need to be generated, it should be possible to download TicketBooking can be done a Logged in User only 
5. With email id user should be able to 
• view History of Ticket Bookings, 
• Cancel a Ticket only prior to a day(24 hrs) before journey date. 6. With PNR number view the booked ticket details



 Features Implemented: 
 1. Airline Management 
    Add a new airline 
2. Flight Inventory Management 
    Add flights under an airline 
    Auto-set available seats based on total seats 
    Search flights by: 
    From place,To place,Journey date 
3. Booking System 
    Book tickets for a flight 
    Accept multiple passengers 
    PNR generation 
    Validate seats: Check available seats 
    Check seat already booked 
    Prevent duplicate seats in the same request 
    Auto-decrease available seats after booking 
4. Booking Cancellation 
    Cancel booking using PNR 
    Auto-restore seats after cancellation 
    cancel before 24 hours 
5. Booking History 
    Fetch all bookings made by a user using email 
6. Exception Handling 
    Global exception handler 
    Returns proper messages like 404 Not Found, 400 Bad Request 



APIs implemented: 
 Airline APIs: 
 1.POST /api/flight/airline/add Add new airline 
 Flight Inventory APIs: 
 1.POST /api/flight/inventory Add flight inventory 
 2.POST /api/flight/search Search flights 
 Booking APIs:
 1.POST /api/flight/booking/{flightId} Book ticket for flight 
 2.GET /api/flight/ticket/{pnr} Get booking details using PNR 
 3.GET /api/flight/booking/history/{email} Get booking history by email 
 4.DELETE /api/flight/booking/cancel/{pnr} Cancel booking