package guesthouse;

import java.util.UUID;

/*
 * Fake payment service. In real life we call a real API.
 * Here I just generate a random id to display the idea.
 */
public class PaymentGateway {

    public String charge(String cardNumber, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be > 0");
        // pretend this card was charged successfully
        return "pay_" + UUID.randomUUID();
    }

    public void refund(String paymentId, int amount) {
        // pretend we did a refund; nothing to do in the demo
    }
}
