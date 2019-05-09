

## Json 序列化数据`JsonSerializer`

- 如何将从数据库中的`Date` 数据格式化
    > 添加一个类`DateJsonSerializer` 继承自`JsonSerializer<?>`
    
        import com.fasterxml.jackson.core.JsonGenerator;
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.JsonSerializer;
        import com.fasterxml.jackson.databind.SerializerProvider;

        public class DateJsonSerializer extends JsonSerializer<Date> { }

    > 重写`serialize` 方法<br>
    > 说明: 
    >>  1. 这个方法何时调用？
	>>>  将对象转换为JSON 串时, 假如在对象的对应的GET 方法上使用了注解 `@JsonSerializer(using=DataJsonSerializer.class)`
	>>  2. 这个方法中的参数都是什么含义？
	>>>  1. value: 要转换的对象
	>>>  2. gen: json 数据转换器
	>>>  3. serializers 序列化提供者

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            gen.writeString(sdf.format(value));
        }

    > 使用时: 
    >> 在一个`entity` 类中的Date 对象的`get`方法上添加注解`@JsonSerialize(using=DateJsonSerializer.class)`

        public class SysRole implements Serializable {
            private static final long serialVersionUID = -5225339701513043662L;
            private Date createdTime;
            @JsonSerialize(using=DateJsonSerializer.class)
            public Date getCreatedTime() {
                return createdTime;
            }
        }


## Json 反序列化数据`JsonDeserializer`

- 它的使用
    > - 与getDate 相对，使用在setDate 的方法上面

package com.jt.common.serializer;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateJsonDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH::mm:ss");
        String strDate = jp.getText();

        // 这一行没加，我这里出现了异常搞了好久才搞定的
        ParsePosition pos = new ParsePosition(0);
        return format.parse(strDate, pos);
    }

}

