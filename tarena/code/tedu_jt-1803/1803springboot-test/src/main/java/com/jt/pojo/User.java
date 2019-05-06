package com.jt.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_user")
public class User {
    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)    // 自增
    private Integer id;
    @Column(name="name")    // 对应表中的字段名称
    private String name;

    private Integer age;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
    
    
}
