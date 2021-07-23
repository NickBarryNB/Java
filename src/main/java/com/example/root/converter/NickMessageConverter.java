package com.example.root.converter;

import com.example.root.bean.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author Nick
 * @Classname NickMessageConverter
 * @Date 2021/7/23 10:02
 * @Description     自定义的Converter
 */
public class NickMessageConverter implements HttpMessageConverter<Person> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {

        return aClass.isAssignableFrom(Person.class);
    }

    /**
     *  服务器要统计出所有的MessageConverter都能写出哪些内容类型
     *
     *  application/nick
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/nick");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return HttpMessageConverter.super.getSupportedMediaTypes(clazz);
    }

    @Override
    public Person read(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Person person, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        //自定义类型内容的写出
        String data = person.getUserName() + "~~~" + person.getAge() + "///" +person.getPet();

        //写出去
        OutputStream body = httpOutputMessage.getBody();
        body.write(data.getBytes(StandardCharsets.UTF_8));
    }
}
