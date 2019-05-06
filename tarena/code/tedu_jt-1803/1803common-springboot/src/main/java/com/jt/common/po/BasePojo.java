package com.jt.common.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jt.common.serializer.DateJsonDeserializer;
import com.jt.common.serializer.DateJsonSerializer;

//pojo基类，完成2个任务，2个日期，实现序列化
public class BasePojo implements Serializable{
    private static final long serialVersionUID = 8291058891687026421L;
    
    private Date created;
	private Date updated;

	@JsonSerialize(using=DateJsonSerializer.class)
	public Date getCreated() {
		return created;
	}
	@JsonDeserialize(using=DateJsonDeserializer.class)
	public void setCreated(Date created) {
		this.created = created;
	}
	@JsonSerialize(using=DateJsonSerializer.class)
	public Date getUpdated() {
		return updated;
	}
	@JsonDeserialize(using=DateJsonDeserializer.class)
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
