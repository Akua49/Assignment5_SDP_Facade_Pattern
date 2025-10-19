package guesthouse;

import java.util.HashMap;
import java.util.Map;

/*
 * Super small in-memory storage for bookings.
 * Map is fine because I don't need a real database for the pattern demo.
 */
public class BookingRepository {
    private final Map<String, Booking> store = new HashMap<>();

    public void save(Booking booking) {
        store.put(booking.getId(), booking);
    }

    public Booking find(String id) {
        return store.get(id);
    }

    public void remove(String id) {
        store.remove(id);
    }
}
