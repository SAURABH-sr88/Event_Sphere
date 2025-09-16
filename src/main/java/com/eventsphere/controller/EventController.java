package com.eventsphere.controller;

import com.eventsphere.model.Event;
import com.eventsphere.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping
    public List<Event> all() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable String id) {
        return eventRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> create(@RequestBody Event event) {
        event.setSeatsAvailable(event.getTotalSeats());
        if (event.getStartTime() == null) event.setStartTime(Instant.now());
        return ResponseEntity.ok(eventRepository.save(event));
    }
}
