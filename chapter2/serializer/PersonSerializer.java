package kafkaStudy.chapter2.serializer;

import kafkaStudy.chapter2.bean.Person;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @Classname PersonSerializer
 * @Description TODO
 * @Date 2021/8/6 22:57
 * @Created by Jieqiyue
 */
public class PersonSerializer implements Serializer<Person> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Person data) {
        if (data == null){
            return null;
        }

        try {
            return data.toByteArr();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
