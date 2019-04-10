package com.jt.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateJsonSerializer extends JsonSerializer<Date> {

	/***
	 * 1. 这个方法何时调用？
	 * 将对象转换为JSON 串时
	 * 假如在对象的对应的GET 方法上使用了 
	 * @JsonSerializer(using=DataJsonSerializer.class)
	 * 2. 这个方法中的参数都是什么含义？
	 * 2.1 value: 要转换的对象
	 * 2.2 gen: json 数据转换器
	 * 2.3 serializers 序列化提供者
	 */
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		// 日期格式转换器
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		// 转换日期对象
		String dateStr = sdf.format(value);
		// 将此字符串写到json 串中
		gen.writeString(dateStr);
	}
}
