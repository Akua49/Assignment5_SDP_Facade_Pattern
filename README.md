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



sequenceDiagram
    participant Client as App
    participant Facade as BookingFacade
    participant Inv as RoomInventory
    participant Price as PricingEngine
    participant Pay as PaymentGateway
    participant Repo as BookingRepository
    participant Clean as CleaningScheduler
    participant Notify as NotificationService

    Client->>Facade: bookStay(req, card)
    Facade->>Inv: isAvailable(req.roomType)?
    Inv-->>Facade: true/false

    alt unavailable
        Note over Facade: throws IllegalStateException
    else available
        Facade->>Price: calculate(req)
        Price-->>Facade: total
        Facade->>Pay: charge(card, total)
        Pay-->>Facade: paymentId
        Facade->>Inv: reserve(req.roomType)
        Facade->>Repo: save(booking)
        Facade->>Clean: schedule(req.checkOut, req.roomType)
        Facade->>Notify: sendBookingConfirmation(booking)
        Facade-->>Client: booking
    end


