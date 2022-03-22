package com.lophita.tobaskincare.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenersExample {

    @KafkaListener(topics = "topic1", groupId = "belajar")
    void listener(String data) {
        log.info(data);
    }

//    @KafkaListener(topics = "topic1, topic3-partition3", groupId = "belajar")
//    void commonListenerForMultipleTopics(String data) {
//        log.info("MultipleTopicListener - {}", data);
//    }

}
