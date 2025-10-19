## UML

### Class Diagram
```mermaid
classDiagram
    direction LR
    class App
    class BookingFacade {
      +quote(req): int
      +bookStay(req, card): Booking
      +cancelBooking(id): boolean
    }
    class RoomInventory {
      +isAvailable(type): boolean
      +reserve(type): void
      +release(type): void
    }
    class PricingEngine {
      +calculate(type, in, out, guests): int
    }
    class PaymentGateway {
      +charge(card, amount): String
      +refund(paymentId, amount): void
    }
    class CleaningScheduler {
      +schedule(date, type): void
      +cancel(date, type): void
    }
    class NotificationService {
      +sendBookingConfirmation(booking): void
      +sendCancellation(id): void
    }
    class BookingRepository {
      +save(booking): void
      +find(id): Booking
      +remove(id): void
    }
    class Booking {
      -id: String
      -request: BookingRequest
      -totalPrice: int
      -paymentId: String
    }
    class BookingRequest {
      -guestName: String
      -roomType: RoomType
      -checkIn: LocalDate
      -checkOut: LocalDate
      -guests: int
    }
    class RoomType { <<enumeration>> }

    App --> BookingFacade : uses
    BookingFacade --> RoomInventory
    BookingFacade --> PricingEngine
    BookingFacade --> PaymentGateway
    BookingFacade --> CleaningScheduler
    BookingFacade --> NotificationService
    BookingFacade --> BookingRepository
    Booking o-- BookingRequest
    BookingRequest --> RoomType
