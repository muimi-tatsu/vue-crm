package com.hp.service.impl;

import com.hp.entity.Employee;
import com.hp.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService service;

    @Test
    public void add() {
        Employee employee =
                new Employee(null, "test", "123", "123", 18, "男", "13533", "天河", null);
        employee.setCreateTime("2020-3-25");
        employee.setUpdateTime("2020-3-25");
        employee.setIsDel(0);
        service.save(employee);
    }

    @Test
    public void add1() {
        Employee employee =
                new Employee(null, "帅哥", "123", "123", 18, "男", "13533", "天河", null);
        service.add(employee, 1);

    }
}