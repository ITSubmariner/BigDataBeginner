package com.pet.bigdatabegginer.service;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.pet.bigdatabegginer.dao.LimitsDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SparkService {
    @Autowired
    private LimitsDao limitsDao;

    public void kafkaStream() {
        SparkConf conf = new SparkConf().setAppName("KafkaConsumer").setMaster("local[*]");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, Durations.seconds(5));

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        Collection<String> topics = Collections.singletonList("data");

        JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaUtils.createDirectStream(
                ssc,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topics, kafkaParams)
        );
        stream.foreachRDD(consumerRecordJavaRDD -> {
            byte[] bytes = consumerRecordJavaRDD.collect().toArray().toString().getBytes();
            Packet packet = EthernetPacket.newPacket(bytes, 0, bytes.length);
        });

    }

}
