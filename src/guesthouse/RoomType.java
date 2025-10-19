package guesthouse;

/*
 * Simple enum for room categories.
 * I keep base price here to avoid magic numbers in other classes.
 */
public enum RoomType {
    STANDARD(30000),
    DELUXE(50000),
    VIP(90000);

    private final int basePrice;

    RoomType(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
