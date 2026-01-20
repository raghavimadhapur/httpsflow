package com.practise.httpsflow.scheduler;

import com.practise.httpsflow.kafka.PaymentEventPublisher;
import com.practise.httpsflow.model.OutboxEvent;
import com.practise.httpsflow.repository.OutboxRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/***OutboxScheduler periodically scans the outbox table for unpublished events
 * and publishes them to Kafka. Failed events are retried automatically in subsequent runs,
 * ensuring reliable, eventually consistent event delivery
 *
 */
@Component
public class OutboxScheduler {

    private final OutboxRepository outboxRepo;
    private final PaymentEventPublisher publisher;

    public OutboxScheduler(OutboxRepository outboxRepo, PaymentEventPublisher publisher) {
        this.outboxRepo = outboxRepo;
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void publishPendingEvents(){
        List<OutboxEvent> events = outboxRepo.findByPublishedFalse();
        try {
            for (OutboxEvent event: events){
                publisher.publish(event);
            }
        } catch (Exception e){

        }

    }

}
