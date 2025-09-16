package com.eventsphere.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "events")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    private String venue;
    private Instant startTime;
    private Instant endTime;
    private int totalSeats;
    private int seatsAvailable;
    private Map<String, String> metadata;
}
