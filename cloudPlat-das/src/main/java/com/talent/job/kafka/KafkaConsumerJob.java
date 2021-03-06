package com.talent.job.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.talent.common.HBConstant;
import com.talent.core.global.Constant;
import com.talent.core.kafka.KafkaPipe;
import com.talent.util.HBaseUtil;
import com.talent.util.JacksonUtil;
import com.talent.util.TimeUtil;

/** kafka������ */
public class KafkaConsumerJob extends Thread {

    private KafkaConsumer<String, String> consumer;

    private static ConcurrentMap<String, Map<String, String>> concurrentMap = new ConcurrentHashMap<String, Map<String, String>>();

    private String topic;

    public KafkaConsumerJob(String topic) {

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaPipe.KAFKA_SERVER_URL);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Kafka_Consumer_4");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        // props.put("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<String, String>(props);
        this.topic = topic;

    }

    @Override
    public void run() {

        consumer.subscribe(Arrays.asList(this.topic));
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                if (records.count() < 1) {
                    Thread.sleep(1000);
                    System.out.println("====sleep!!!");
                }
                for (ConsumerRecord<String, String> record : records) {

                    Map<String, String> dataMaps = JacksonUtil.readValue(record.value(), Map.class);
                    String usrCode = (String) dataMaps.get("userCode");
                    String checkCode = (String) dataMaps.get("checkCode");
                    String examMonth = String.valueOf(dataMaps.get("examMonth"));
                    concurrentMap.put(usrCode + ":" + checkCode + ":" + examMonth, dataMaps);
                }

                handleData();
                consumer.commitSync();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private static void handleData() {
        List<Put> puts = new ArrayList<Put>();
        for (Map<String, String> tMaps : concurrentMap.values()) {
            String userCode = tMaps.get("userCode");
            String checkCode = tMaps.get("checkCode");
            String examTime = String.valueOf(tMaps.get("examMonth"));
            String examYear = String.valueOf(tMaps.get("examYear"));
            String examTimeExt = examYear + "-" + examTime.replace(examYear, "");
            String institutionId = tMaps.get("institutionId");
            String rowKey = String.format(HBConstant.ROW_KEY_FORMAT, TimeUtil.getNumForID(examTimeExt, 201),
                    institutionId, examTimeExt, userCode, checkCode);
            Put p1 = new Put(Bytes.toBytes(rowKey));

            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_TRUE),
                    Bytes.toBytes(tMaps.get("diseasesTrue")));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_FALSE),
                    Bytes.toBytes(tMaps.get("diseasesFalse")));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_BLANK),
                    Bytes.toBytes(tMaps.get("diseasesBlank")));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_NULL),
                    Bytes.toBytes(tMaps.get("diseasesNull")));
            String crowd_list = tMaps.get("crowds");
            if (crowd_list == null)
                crowd_list = "";
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_CROWD_LIST),
                    Bytes.toBytes(crowd_list));

            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_TRUE),
                    Bytes.toBytes(tMaps.get("itemsTrue")));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_FALSE),
                    Bytes.toBytes(tMaps.get("itemsFalse")));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_NULL),
                    Bytes.toBytes(tMaps.get("itemsNull")));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_BLANK),
                    Bytes.toBytes(tMaps.get("itemsBlank")));

            Map<String, String> maps = new HashMap<>();
            for (String tmp : tMaps.keySet()) {
                if (Pattern.matches(Constant.REGEX_MATCH_KAFKA, tmp)) {
                    String val = String.valueOf(tMaps.get(tmp));
                    if (Pattern.matches(Constant.REGEX_MATCH_ZW, val)) {
                        maps.put(tmp, val);
                    }

                }

            }
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_JSON),
                    Bytes.toBytes(JacksonUtil.toJSon(maps)));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_STD_KEY_ID),
                    Bytes.toBytes(tMaps.get("datastdId")));

            String groups_list = tMaps.get("groups");
            if (groups_list == null)
                groups_list = "";
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_GROUP_LIST),
                    Bytes.toBytes(groups_list));

            puts.add(p1);
            if (puts.size() > 300) {
                HBaseUtil.puts(HBConstant.BASE_TABLE, puts);
                puts.clear();
            }
        }

        if (puts.size() > 0) {
            HBaseUtil.puts(HBConstant.BASE_TABLE, puts);
            puts.clear();
        }
        concurrentMap.clear();
    }

    public String name() {
        return null;
    }

    public boolean isInterruptible() {
        return false;
    }

    public static void main(String[] args) {
        new KafkaConsumerJob(KafkaPipe.TOPIC_SOURCE).start();
    }

}
