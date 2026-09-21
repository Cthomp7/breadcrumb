package com.breadcrumb.backend.event;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AnalyticsEventRepository extends JpaRepository<AnalyticsEvent, Long> {
        
}
