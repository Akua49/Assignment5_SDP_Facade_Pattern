package guesthouse;

/*
 * Simple "email" notifications. Console is enough for demo.
 */
public class NotificationService {
    public void sendBookingConfirmation(Booking booking) {
        System.out.println("Confirmation sent: " + booking);
    }
    public void sendCancellation(String bookingId) {
        System.out.println("Cancellation sent for booking " + bookingId);
    }
}
