package com.eventsphere.controller;

import com.eventsphere.model.*;
import com.eventsphere.repository.BookingRepository;
import com.eventsphere.repository.EventRepository;
import com.eventsphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public record CreateBookingReq(String eventId, int seatsRequested, List<String> seatIds) {}

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails principal, @RequestBody CreateBookingReq rq) {
        var username = principal.getUsername();
        var user = userRepository.findByUsername(username).orElseThrow();
        var event = eventRepository.findById(rq.eventId()).orElse(null);
        if(event == null) return ResponseEntity.badRequest().body("Event not found");
        synchronized (event.getId().intern()) {
            if(event.getSeatsAvailable() < rq.seatsRequested()) {
                return ResponseEntity.badRequest().body("Not enough seats");
            }
            event.setSeatsAvailable(event.getSeatsAvailable() - rq.seatsRequested());
            eventRepository.save(event);
        }
        var booking = Booking.builder()
                .userId(user.getId())
                .eventId(event.getId())
                .seats(rq.seatIds())
                .amount(100.0 * rq.seatsRequested())
                .bookedAt(Instant.now())
                .status(BookingStatus.CONFIRMED)
                .build();
        bookingRepository.save(booking);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/me")
    public List<Booking> myBookings(@AuthenticationPrincipal UserDetails principal) {
        var user = userRepository.findByUsername(principal.getUsername()).orElseThrow();
        return bookingRepository.findByUserId(user.getId());
    }
}
