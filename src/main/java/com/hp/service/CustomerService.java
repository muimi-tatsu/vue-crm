package com.hp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hp.entity.Customer;

public interface CustomerService extends IService<Customer> {

    IPage<Customer> selectList(Page<Customer> page);

    Integer add(Customer customer, Integer empId, String way);

    Integer update(Customer customer, Integer empId);

}
