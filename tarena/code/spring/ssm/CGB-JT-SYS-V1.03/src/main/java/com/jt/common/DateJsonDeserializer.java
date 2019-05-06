package com.jt.common;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 反序列化: 将一个字符串类型的时间转换成一个Date 对象
 * 用法: @JsonSerializer(using=DateJsonSerializer.class)
 * @author suyh
 *
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH::mm:ss");
        String strDate = jp.getText();

        ParsePosition pos = new ParsePosition(0);
        return format.parse(strDate, pos);
    }
}