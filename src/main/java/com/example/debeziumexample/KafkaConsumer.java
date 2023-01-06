package com.example.debeziumexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(groupId = "test_group_id", topics = "dbserver1.inventory.customers")
    public void consumeMessage(String msg) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(msg);
        String prettyJsonString = gson.toJson(je);
        System.out.printf("Kafka message consumed: %s%n", prettyJsonString);
    }
}
