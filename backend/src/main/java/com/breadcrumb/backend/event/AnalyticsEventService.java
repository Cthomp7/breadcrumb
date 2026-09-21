package com.breadcrumb.backend.event;

import java.time.Instant;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.breadcrumb.backend.event.dto.CreateEventRequest;
import com.breadcrumb.backend.event.dto.CreateEventResponse;

@Service 
public class AnalyticsEventService {
    
    private final AnalyticsEventRepository analyticsEventRepository;

    public AnalyticsEventService(
        AnalyticsEventRepository analyticsEventRepository
    ) {
        this.analyticsEventRepository = analyticsEventRepository;
    }

    @Transactional 
    public CreateEventResponse createEvent(
        CreateEventRequest request
    ) {
        AnalyticsEvent event = AnalyticsEvent.builder()
            .eventType(normalizeEventType(request.eventType()))
            .source(normalizeSource(request.source()))
            .anonymousUserId(
                normalizeOptional(request.anonymousUserId())
            )
            .occurredAt(
                request.occurredAt() != null
                    ? request.occurredAt()
                    : Instant.now()
            )
            .build();

        AnalyticsEvent savedEvent = analyticsEventRepository.save(event);

        return new CreateEventResponse(savedEvent.getId());
    }

    private String normalizeEventType(String eventType) {
        return eventType
            .trim()
            .toUpperCase()
            .replace(" ", "_");
    }

    private String normalizeSource(String source) {
        return source
            .trim()
            .toLowerCase(Locale.ROOT);
    }

    private String normalizeOptional(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
} 
