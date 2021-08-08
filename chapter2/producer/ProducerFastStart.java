package kafkaStudy.chapter2.producer;

import kafkaStudy.chapter2.bean.Person;
import kafkaStudy.chapter2.interceptor.ProducerinterceptorPrefix;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;


import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname ProducerFastStart
 * @Description TODO
 * @Date 2021/8/6 22:50
 * @Created by Jieqiyue
 */
public class ProducerFastStart {
    public static final String brokerList = "127.0.0.1:9092";
    public static final String topic = "testjie";
    public static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerList);
        properties.put(ProducerConfig.ACKS_CONFIG,"1");    // 可重试异常重试10次
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                PersonSerializer.class.getName()); // 自定义value序列化
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerinterceptorPrefix.class.getName());// 自定义拦截器

        KafkaProducer<String, Person> producer = new KafkaProducer<>(properties);

        for (int i = 0 ;i < 200;i++){
            ProducerRecord<String, Person> record = new ProducerRecord<>(topic, new Person());
            try {
                Future<RecordMetadata> send = producer.send(record);
                Thread.sleep(2000);
                System.out.println("send-over");
//            producer.send(record).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}
