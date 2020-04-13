package com.hp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Customer;
import com.hp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> customers() {
        return customerService.list();
    }

    /**
     * 查询一页客户信息
     *
     * @param page  当前页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/customer")
    public Map getList(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //设置mybatisPlus分页
        Page<Customer> p = new Page<Customer>();
        //设置每页记录数
        p.setSize(limit);
        //设置当前页码
        p.setCurrent(page);
        IPage<Customer> iPage = customerService.selectList(p);
        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);
        return map;
    }

    /**
     * 新增客户
     */
    @PostMapping("/customer")
    public Map add(Customer customer, Integer empId, String way1, String way2) {
        String way = way1 + way2;
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", customerService.add(customer, empId, way));
        return result;
    }

    /**
     * 删除客户
     */
    @DeleteMapping("/customer/del/{customerId}")
    public Map del(@PathVariable Integer customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setIsDel(1);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", customerService.updateById(customer));
        return result;
    }

    /**
     * 修改客户
     */
    @PutMapping("/customer")
    public Map edit(Customer customer, Integer empId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", customerService.update(customer, empId));
        return result;
    }
}
