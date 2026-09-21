CREATE TABLE analytics_events (
    id BIGSERIAL PRIMARY KEY,

    event_type VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    anonymous_user_id VARCHAR(255),

    occurred_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
        DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_analytics_events_occurred_at
    ON analytics_events (occurred_at DESC);

CREATE INDEX idx_analytics_events_type_occurred_at
    ON analytics_events (event_type, occurred_at DESC);

CREATE INDEX idx_analytics_events_source_occurred_at
    ON analytics_events (source, occurred_at DESC);