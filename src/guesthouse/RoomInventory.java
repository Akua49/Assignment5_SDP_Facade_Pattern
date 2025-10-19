package guesthouse;

import java.util.EnumMap;
import java.util.Map;

/*
 * Very tiny "database" of available rooms.
 * Map is enough for the demo.
 */
public class RoomInventory {
    private final Map<RoomType, Integer> stock = new EnumMap<>(RoomType.class);

    public RoomInventory() {
        stock.put(RoomType.STANDARD, 5);
        stock.put(RoomType.DELUXE, 3);
        stock.put(RoomType.VIP, 1);
    }

    public boolean isAvailable(RoomType type) {
        return stock.getOrDefault(type, 0) > 0;
    }

    public void reserve(RoomType type) {
        int left = stock.getOrDefault(type, 0);
        if (left <= 0) throw new IllegalStateException("no rooms of type " + type);
        stock.put(type, left - 1);
    }

    public void release(RoomType type) {
        stock.put(type, stock.getOrDefault(type, 0) + 1);
    }
}
