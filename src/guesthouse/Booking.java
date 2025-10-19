package guesthouse;

/*
 * Entity that represents a confirmed booking.
 * I keep it small and only with fields I actually use.
 */
public class Booking {
    private final String id;
    private final BookingRequest request;
    private final int totalPrice;
    private final String paymentId;

    public Booking(String id, BookingRequest request, int totalPrice, String paymentId) {
        this.id = id;
        this.request = request;
        this.totalPrice = totalPrice;
        this.paymentId = paymentId;
    }

    public String getId() { return id; }
    public BookingRequest getRequest() { return request; }
    public int getTotalPrice() { return totalPrice; }
    public String getPaymentId() { return paymentId; }

    @Override
    public String toString() {
        return "Booking{id='" + id + "', guest='" + request.getGuestName() +
                "', room=" + request.getRoomType() + ", total=" + totalPrice + "}";
    }
}
