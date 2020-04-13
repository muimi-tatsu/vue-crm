package com.hp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Employee;
import com.hp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * 查询一页员工信息
     *
     * @param page  当前页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/emp")
    public Map getList(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();

        //设置mybatisPlus分页
        Page<Employee> p = new Page<Employee>();
        p.setSize(limit);       //设置每页记录数
        p.setCurrent(page);     //设置当前页码

        IPage<Employee> iPage = service.selectList(p);

        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);
        return map;
    }

    /**
     * 新增用户
     */
    @PostMapping("/emp")
    public Map add(Employee employee, Integer roleId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", service.add(employee, roleId));
        return result;
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/emp/del/{empId}")
    public Map del(@PathVariable Integer empId) {
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setIsDel(1);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", service.updateById(employee));
        return result;
    }

    /**
     * 删除用户
     */
    @PutMapping("/emp")
    public Map edit(Employee employee, Integer roleId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", service.update(employee, roleId));
        return result;
    }
}