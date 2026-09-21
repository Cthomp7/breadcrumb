package com.breadcrumb.backend.event;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "analytics_events")
@Getter 
@Setter
@Builder 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnalyticsEvent {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;

    @Column(nullable = false, length = 100)
    private String source;

    @Column(name = "anonymous_user_id")
    private String anonymousUserId;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Column(
        name = "created_at",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private Instant createdAt;

    @Builder 
    private AnalyticsEvent(
        String eventType,
        String source,
        String anonymousUserId,
        Instant occurredAt
    ) {
        this.eventType = Objects.requireNonNull(eventType);
        this.source = Objects.requireNonNull(source);
        this.anonymousUserId = anonymousUserId;
        this.occurredAt = Objects.requireNonNull(occurredAt);
    }

}
