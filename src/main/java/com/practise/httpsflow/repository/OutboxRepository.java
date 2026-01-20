package com.practise.httpsflow.repository;
import com.practise.httpsflow.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long>{
    List<OutboxEvent> findByPublishedFalse();

}