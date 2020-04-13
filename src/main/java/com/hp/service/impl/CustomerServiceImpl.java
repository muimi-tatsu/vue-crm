package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.entity.Contact;
import com.hp.entity.ContactCustomerEmployee;
import com.hp.entity.Customer;
import com.hp.entity.CustomerEmployee;
import com.hp.mapper.ContactCustomerEmployeeMapper;
import com.hp.mapper.ContactMapper;
import com.hp.mapper.CustomerEmployeeMapper;
import com.hp.mapper.CustomerMapper;
import com.hp.service.CustomerService;
import com.hp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerEmployeeMapper customerEmployeeMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ContactCustomerEmployeeMapper contactCustomerEmployeeMapper;

    @Override
    public IPage<Customer> selectList(Page<Customer> page) {
        return customerMapper.selectList(page);
    }

    @Override
    public Integer add(Customer customer, Integer empId, String way) {
        customer.setIsOrders(0);
        customer.setCreateTime(StringUtils.getNowTime());
        customer.setUpdateTime(StringUtils.getNowTime());
        customer.setIsDel(0);
        int result = customerMapper.insert(customer);

        CustomerEmployee customerEmployee = new CustomerEmployee();
        customerEmployee.setCustomerId(customer.getCustomerId());
        customerEmployee.setEmpId(empId);
        customerEmployee.setCreateTime(StringUtils.getNowTime());
        customerEmployee.setUpdateTime(StringUtils.getNowTime());
        customerEmployee.setIsDel(0);
        customerEmployeeMapper.insert(customerEmployee);

        Contact contact = new Contact();
        contact.setWay(way);
        contact.setCreateTime(StringUtils.getNowTime());
        contact.setUpdateTime(StringUtils.getNowTime());
        contact.setIsDel(0);
        contactMapper.insert(contact);

        ContactCustomerEmployee contactCustomerEmployee = new ContactCustomerEmployee();
        contactCustomerEmployee.setContactId(contact.getContactId());
        contactCustomerEmployee.setCustomerId(customer.getCustomerId());
        contactCustomerEmployee.setEmpId(empId);
        contactCustomerEmployee.setCreateTime(StringUtils.getNowTime());
        contactCustomerEmployee.setUpdateTime(StringUtils.getNowTime());
        contactCustomerEmployee.setIsDel(0);
        contactCustomerEmployeeMapper.insert(contactCustomerEmployee);

        return result;
    }

    @Override
    public Integer update(Customer customer, Integer empId) {
        customer.setUpdateTime(StringUtils.getNowTime());
        int result = customerMapper.updateById(customer);

        CustomerEmployee customerEmployee = new CustomerEmployee();
        customerEmployee.setCustomerId(customer.getCustomerId());
        customerEmployee.setEmpId(empId);
        customerEmployee.setUpdateTime(StringUtils.getNowTime());
        AbstractWrapper abstractWrapper = new QueryWrapper();
        abstractWrapper.eq("customer_id", customer.getCustomerId());
        customerEmployeeMapper.update(customerEmployee, abstractWrapper);

        ContactCustomerEmployee contactCustomerEmployee = new ContactCustomerEmployee();
        contactCustomerEmployee.setEmpId(empId);
        contactCustomerEmployee.setUpdateTime(StringUtils.getNowTime());
        AbstractWrapper abstractWrapper2 = new QueryWrapper();
        abstractWrapper2.eq("customer_id", customer.getCustomerId());
        contactCustomerEmployeeMapper.update(contactCustomerEmployee, abstractWrapper2);

        return result;
    }

}
