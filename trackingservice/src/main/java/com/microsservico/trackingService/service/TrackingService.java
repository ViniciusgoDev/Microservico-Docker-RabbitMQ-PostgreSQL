package com.microsservico.trackingService.service;

import com.microsservico.trackingService.apiExternal.HireTansport;
import com.microsservico.trackingService.exception.TrackingServiceException;
import com.microsservico.trackingService.model.OrderRequestDTO;
import com.microsservico.trackingService.model.TrackingEvent;
import com.microsservico.trackingService.model.Status;
import com.microsservico.trackingService.producer.TrackingEventPublisher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Service
public class TrackingService {

    private final HireTansport hireTansport;

    private final TrackingEventPublisher trackingEventPublisher;

    public void initiateTracking(@Valid OrderRequestDTO order) {
        try {

            TrackingEvent trackingEvent = createTracking(order);
            log.info("Tracking started successfully - Request: {}, Code: {}",
                    order.id(),
                    trackingEvent.getTrackingCode());
            trackingEventPublisher.sendTrackingUpdate(trackingEvent);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    private TrackingEvent createTracking(OrderRequestDTO order) {
        try {
            log.debug(
                    "Obtaining tracking code for order: {}", order.id());

            Long trackingCode = hireTansport.getTrackingCode();

            return TrackingEvent.builder()
                    .trackingCode(trackingCode)
                    .status(Status.PROCESSING.toString())
                    .orderId(order.id())
                    .updatedAt(Instant.now())
                    .build();

        } catch (Exception e) {
            log.error(
                    "Error creating tracking for order: {}", order.id(), e);
            throw new TrackingServiceException("Failed to create tracking");
        }
    }
}
