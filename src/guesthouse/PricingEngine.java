package guesthouse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * Calculates price. I keep the rules simple:
 *  - base price from room type
 *  - weekend is 20% more
 *  - more than 2 guests -> extra fee per night
 */
public class PricingEngine {
    // constants (so they are easy to find/change)
    public static final double WEEKEND_MULTIPLIER = 1.20;
    public static final int EXTRA_GUEST_FEE_PER_NIGHT = 5000;

    public int calculate(RoomType type, LocalDate in, LocalDate out, int guests) {
        long nights = ChronoUnit.DAYS.between(in, out);
        int base = type.getBasePrice();
        int total = 0;

        LocalDate d = in;
        for (int i = 0; i < nights; i++) {
            int dayPrice = base;
            DayOfWeek dow = d.getDayOfWeek();
            if (dow == DayOfWeek.FRIDAY || dow == DayOfWeek.SATURDAY) {
                dayPrice = (int) Math.round(dayPrice * WEEKEND_MULTIPLIER);
            }
            total += dayPrice;
            d = d.plusDays(1);
        }

        if (guests > 2) {
            total += (guests - 2) * EXTRA_GUEST_FEE_PER_NIGHT * (int) nights;
        }
        return total;
    }
}
