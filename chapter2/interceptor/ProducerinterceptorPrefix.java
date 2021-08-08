package kafkaStudy.chapter2.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import kafkaStudy.chapter2.bean.Person;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname ProducerinterceptorPrefix
 * @Description TODO
 * @Date 2021/8/6 22:51
 * @Created by Jieqiyue
 */
public class ProducerinterceptorPrefix implements ProducerInterceptor<String, Person> {

    public static AtomicInteger times = new AtomicInteger();
    @Override
    public ProducerRecord<String, Person> onSend(ProducerRecord<String, Person> record) {
        Person value = record.value();
        value.setExtra(String.valueOf(times.incrementAndGet()));
        return new ProducerRecord<>(record.topic(),record.key(),value);
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
