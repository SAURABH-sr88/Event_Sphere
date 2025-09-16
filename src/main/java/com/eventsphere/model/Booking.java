package com.eventsphere.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "bookings")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Booking {
    @Id
    private String id;
    private String userId;
    private String eventId;
    private List<String> seats;
    private double amount;
    private Instant bookedAt;
    private BookingStatus status;
}
