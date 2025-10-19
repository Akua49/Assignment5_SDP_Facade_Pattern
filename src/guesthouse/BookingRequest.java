package guesthouse;

import java.time.LocalDate;

/*
 * Just a small data holder (DTO) for a user's request.
 * I use final fields to make it immutable and safe.
 */
public class BookingRequest {
    private final String guestName;
    private final RoomType roomType;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final int guests;

    public BookingRequest(String guestName, RoomType roomType,
                          LocalDate checkIn, LocalDate checkOut, int guests) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;

        // small validation so bad dates do not break the app
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("checkOut must be after checkIn");
        }
        if (guests <= 0) {
            throw new IllegalArgumentException("guests must be positive");
        }
    }

    public String getGuestName() { return guestName; }
    public RoomType getRoomType() { return roomType; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public int getGuests() { return guests; }
}
