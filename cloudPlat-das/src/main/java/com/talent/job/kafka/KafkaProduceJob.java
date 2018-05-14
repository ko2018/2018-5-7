package com.talent.job.kafka;import java.util.Properties;import org.apache.kafka.clients.producer.Callback;import org.apache.kafka.clients.producer.KafkaProducer;import org.apache.kafka.clients.producer.ProducerRecord;import org.apache.kafka.clients.producer.RecordMetadata;import com.talent.core.kafka.KafkaPipe;/** kafka������ */public class KafkaProduceJob extends Thread {    private KafkaProducer<String, String> producer;    private String topic;    public KafkaProduceJob(String topic) {        Properties props = new Properties();        props.put("bootstrap.servers", KafkaPipe.KAFKA_SERVER_URL);        props.put("client.id", "DemoProducer");        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");        producer = new KafkaProducer<String, String>(props);        this.topic = topic;    }    public void run() {        int messageNo = 1;        while (true) {            String messageStr = "Message_" + messageNo;            String startTime = "" + System.currentTimeMillis();            System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");            try {                producer.send(new ProducerRecord<>(topic, "" + messageNo, messageStr)).get();                System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");            } catch (Exception e) {                e.printStackTrace();            }            ++messageNo;            if (messageNo > 1000)                return;        }    }    public static void main(String[] args) {        new KafkaProduceJob(KafkaPipe.TOPIC_SOURCE).start();    }}class DemoCallBack implements Callback {    private final String startTime;    private final String key;    private final String message;    public DemoCallBack(String startTime, String key, String message) {        this.startTime = startTime;        this.key = "" + key;        this.message = message;    }    public void onCompletion(RecordMetadata metadata, Exception exception) {        if (metadata != null) {            System.out.println("message(" + key + ", " + message + ") sent to partition(" + metadata.partition() + "), "                    + "offset(" + metadata.offset() + ") in " + 0 + " ms");        } else {            exception.printStackTrace();        }    }}