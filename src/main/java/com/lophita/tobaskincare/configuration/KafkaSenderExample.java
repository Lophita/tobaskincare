package com.lophita.tobaskincare.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaSenderExample {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

//    public void sendMessageWithCallback(String topicName, String message) {
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                log.warn("Unable to deliver message [{}]. {}", message, throwable.getMessage());
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                log.info("Message[{}] delivered with offset {}", message, result.getRecordMetadata().offset());
//            }
//        });
//    }

}
