package guesthouse;

import java.time.LocalDate;

/*
 * Tiny demo (like a console UI). Shows how the Facade makes the client code short.
 */
public class App {
    public static void main(String[] args) {
        // I use the helper to get a ready-to-use facade with all services
        BookingFacade facade = BookingFacade.createDemo();

        // create a test request (you can change values)
        BookingRequest req = new BookingRequest(
                "Aziza Zeinolla",         // guest name
                RoomType.DELUXE,           // room type
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(4),
                3                          // number of guests
        );

        // 1) ask for price (no side effects)
        int price = facade.quote(req);
        System.out.println("Quote: " + price + " KZT");

        // 2) confirm booking (here facade calls all subsystems for me)
        Booking booking = facade.bookStay(req, "5555-1111-2222-3333");
        System.out.println("Booked: " + booking);

        // 3) cancel example (to show the other flow)
        boolean cancelled = facade.cancelBooking(booking.getId());
        System.out.println("Cancelled: " + cancelled);
    }
}
