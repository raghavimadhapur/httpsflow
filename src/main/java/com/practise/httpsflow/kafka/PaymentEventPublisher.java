package com.practise.httpsflow.kafka;

import com.practise.httpsflow.model.OutboxEvent;
import com.practise.httpsflow.repository.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/*****PaymentEventPublisher is an infrastructure component that publishes
 * outbox events to Kafka and marks them as published,
 * completing the Outbox Pattern and enabling reliable,
 * eventually consistent event-driven communication.
 */
@Component
public class PaymentEventPublisher {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private  final OutboxRepository outboxRepo;

    public PaymentEventPublisher(KafkaTemplate<String, String> kafkaTemplate, OutboxRepository outboxRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.outboxRepo = outboxRepo;
    }
    //"payment.created"	Kafka topic
    //aggregateId	Message key
    //payload	JSON event data
    public void publish(OutboxEvent event){
        kafkaTemplate.send("payment.created",event.getAggregateId(),event.getPayload());
        event.setPublished(true);//idempotent
        outboxRepo.save(event);//Outbox event saved
    }
}
