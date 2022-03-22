package com.lophita.tobaskincare.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("topic1").partitions(10).build();
    }

    @Bean
    public NewTopic topic2Abc() {
        return TopicBuilder.name("topic2-abc").partitions(1).build();
    }

    @Bean NewTopic topic2Partition3() {
        return TopicBuilder.name("topic2-partition3").partitions(3).build();
    }

    @Bean NewTopic topicLain() {
        return TopicBuilder.name("topic-lain").build();
    }

}
