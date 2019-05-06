package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.StudentMapper;
import com.jt.pojo.Student;

@Service
public class StudentService {
    @Autowired
    private StudentMapper mapper;

    public Student queryStudent(Integer id) {
        return mapper.queryById(id);
    }

}
