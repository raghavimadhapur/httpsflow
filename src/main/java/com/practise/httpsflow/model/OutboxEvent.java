package com.practise.httpsflow.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Instant;

/***| Field           | Purpose                                 |
 | --------------- | --------------------------------------- |
 | `aggregateType` | Domain entity type (`PAYMENT`, `ORDER`) |
 | `aggregateId`   | Business ID (`paymentId`, `orderId`)    |
 | `eventType`     | What happened (`PAYMENT_CREATED`)       |
 *Usually JSON
 * Marked as @Lob because payloads can be large
 */
@Entity
@Table(name ="outbox_event")
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String aggregateType;
    private String aggregateId;
    private String eventType;
    @Lob
    private String payload;

    private boolean published =false;
    private Instant createdAt = Instant.now();

    public long getId() {
        return id;
    }

    public String getAggregateId() {
        return aggregateId;
    }


    public String getPayload() {
        return payload;
    }

    public boolean isPublished() {
        return published;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
