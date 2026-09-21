package com.breadcrumb.backend.event.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record CreateEventRequest(
    @NotBlank
    @Size(max = 100)
    String eventType,

    @NotBlank
    @Size(max = 100)
    String source,

    @Size(max = 255)
    String anonymousUserId,

    @PastOrPresent
    Instant occurredAt
) {}
