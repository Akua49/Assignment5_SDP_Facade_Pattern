package guesthouse;

import java.util.UUID;

/*
 * FACADE
 * This class hides many small subsystems behind 3 simple methods.
 * Client will only use: quote(), bookStay(), cancelBooking().
 * This reduces coupling and keeps main() very clean.
 */
public class BookingFacade {
    private final RoomInventory inventory;
    private final PricingEngine pricing;
    private final PaymentGateway payments;
    private final CleaningScheduler cleaning;
    private final NotificationService notifications;
    private final BookingRepository repo;

    public BookingFacade(RoomInventory inventory,
                         PricingEngine pricing,
                         PaymentGateway payments,
                         CleaningScheduler cleaning,
                         NotificationService notifications,
                         BookingRepository repo) {
        this.inventory = inventory;
        this.pricing = pricing;
        this.payments = payments;
        this.cleaning = cleaning;
        this.notifications = notifications;
        this.repo = repo;
    }

    // simple read-only method for UI or pre-check
    public int quote(BookingRequest req) {
        return pricing.calculate(
                req.getRoomType(),
                req.getCheckIn(),
                req.getCheckOut(),
                req.getGuests()
        );
    }

    // the main action: it coordinates all subsystems in the correct order
    public Booking bookStay(BookingRequest req, String cardNumber) {
        if (!inventory.isAvailable(req.getRoomType())) {
            throw new IllegalStateException("room not available: " + req.getRoomType());
        }

        int price = quote(req);
        String paymentId = payments.charge(cardNumber, price);
        inventory.reserve(req.getRoomType());

        String id = "bkg_" + UUID.randomUUID();
        Booking booking = new Booking(id, req, price, paymentId);
        repo.save(booking);

        cleaning.schedule(req.getCheckOut(), req.getRoomType());
        notifications.sendBookingConfirmation(booking);
        return booking;
    }

    // cancellation also becomes one simple call for the client
    public boolean cancelBooking(String bookingId) {
        Booking b = repo.find(bookingId);
        if (b == null) return false;

        payments.refund(b.getPaymentId(), b.getTotalPrice());
        inventory.release(b.getRequest().getRoomType());
        cleaning.cancel(b.getRequest().getCheckOut(), b.getRequest().getRoomType());
        notifications.sendCancellation(bookingId);
        repo.remove(bookingId);
        return true;
    }

    // convenience factory for demo so I don't wire everything in main()
    public static BookingFacade createDemo() {
        return new BookingFacade(
                new RoomInventory(),
                new PricingEngine(),
                new PaymentGateway(),
                new CleaningScheduler(),
                new NotificationService(),
                new BookingRepository()
        );
    }
}
