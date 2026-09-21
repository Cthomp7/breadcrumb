package com.breadcrumb.backend.event;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.breadcrumb.backend.event.dto.CreateEventRequest;
import com.breadcrumb.backend.event.dto.CreateEventResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController 
@RequestMapping("/api/v1/events")
public class AnalyticsEventController {
    private final AnalyticsEventService eventService;
    private final byte[] expectedApiKey;

    public AnalyticsEventController(
        AnalyticsEventService eventService,
        @Value("${breadcrumb.ingest-api-key}") String apiKey
    ) {
        this.eventService = eventService;
        this.expectedApiKey = apiKey.getBytes(StandardCharsets.UTF_8);
    }

    @PostMapping()
    public ResponseEntity<CreateEventResponse> createEvent(
        @RequestHeader(
            value = "X-API-Key",
            required = false
        ) String apiKey,
        @Valid @RequestBody CreateEventRequest request
    ) {
        if (!isValidApiKey(apiKey)) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        }

        CreateEventResponse response =
            eventService.createEvent(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }
    
    private boolean isValidApiKey(String candidate) {
        if (candidate == null) {
            return false;
        }

        return MessageDigest.isEqual(
            expectedApiKey,
            candidate.getBytes(StandardCharsets.UTF_8)
        );
    }
}
