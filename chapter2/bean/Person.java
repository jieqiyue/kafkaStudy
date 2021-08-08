package kafkaStudy.chapter2.bean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2021/8/6 22:56
 * @Created by Jieqiyue
 */
public class Person {
    private String name;
    private Integer age;
    private String extra;

    public Person(String name, int age, String extra) {
        this.name = name;
        this.age = age;
        this.extra = extra;
    }

    public byte[] toByteArr() throws UnsupportedEncodingException {
        ByteBuffer allocate = ByteBuffer.allocate(4 + 4 + 4 + name.length() + extra.length());
        allocate.putInt(name.length());
        allocate.put(name.getBytes("UTF-8"));
        allocate.putInt(age);
        allocate.putInt(extra.length());
        allocate.put(extra.getBytes("UTF-8"));

        return allocate.array();
    }
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
