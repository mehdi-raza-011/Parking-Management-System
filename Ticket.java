import java.time.LocalDateTime;

public class Ticket {

    String ticketId;
    String plate;
    String spotId;
    LocalDateTime entryTime;

    public Ticket(String plate, String spotId) {
        this.plate = plate;
        this.spotId = spotId;
        this.entryTime = LocalDateTime.now();


        this.ticketId = "T-" + plate + "-" + System.currentTimeMillis();
    }
}
