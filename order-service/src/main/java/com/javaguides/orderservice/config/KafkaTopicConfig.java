package com.javaguides.orderservice.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.TopicBuilder;

@Configuration // Spring base java configuration within this class we can define spring bean
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name}") // To retrieve application.properties kafka topic key
    private String topicName;

    // create a topic using spring bean
    @Bean // Spring will recognize this method as spring bean
    public NewTopic topic(){

        return TopicBuilder.name(topicName).build(); // This will create instance of new topic
    }
}
