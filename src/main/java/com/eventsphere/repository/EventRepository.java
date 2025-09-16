package com.eventsphere.repository;

import com.eventsphere.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByTitleContainingIgnoreCase(String title);
}
