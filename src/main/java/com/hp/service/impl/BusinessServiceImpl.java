package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.entity.Business;
import com.hp.entity.Orders;
import com.hp.entity.OrdersBusinessCustomer;
import com.hp.mapper.BusinessMapper;
import com.hp.mapper.OrdersBusinessCustomerMapper;
import com.hp.mapper.OrdersMapper;
import com.hp.service.BusinessService;
import com.hp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersBusinessCustomerMapper ordersBusinessCustomerMapper;

    @Override
    public IPage<Business> selectList(Page<Business> page) {
        return businessMapper.selectList(page);
    }

    @Override
    public Integer add(Business business, String totalPrice, Integer customerId) {
        business.setCreateTime(StringUtils.getNowTime());
        business.setUpdateTime(StringUtils.getNowTime());
        business.setIsDel(0);
        int result = businessMapper.insert(business);

        Orders orders = new Orders();
        orders.setTotalPrice(totalPrice);
        orders.setCreateTime(StringUtils.getNowTime());
        orders.setUpdateTime(StringUtils.getNowTime());
        orders.setIsDel(0);
        ordersMapper.insert(orders);

        OrdersBusinessCustomer ordersBusinessCustomer = new OrdersBusinessCustomer();
        ordersBusinessCustomer.setOrdersId(orders.getOrdersId());
        ordersBusinessCustomer.setCustomerId(customerId);
        ordersBusinessCustomer.setBusinessId(business.getBusinessId());
        ordersBusinessCustomer.setCreateTime(StringUtils.getNowTime());
        ordersBusinessCustomer.setUpdateTime(StringUtils.getNowTime());
        ordersBusinessCustomer.setIsDel(0);
        ordersBusinessCustomerMapper.insert(ordersBusinessCustomer);

        return result;
    }

    @Override
    public Integer update(Business business, Integer ordersId, String totalPrice, Integer customerId) {
        business.setUpdateTime(StringUtils.getNowTime());
        int result = businessMapper.updateById(business);

        Orders orders = new Orders();
        orders.setTotalPrice(totalPrice);
        orders.setUpdateTime(StringUtils.getNowTime());
        AbstractWrapper abstractWrapper = new QueryWrapper();
        abstractWrapper.eq("orders_id", ordersId);
        ordersMapper.update(orders, abstractWrapper);

        OrdersBusinessCustomer ordersBusinessCustomer = new OrdersBusinessCustomer();
        ordersBusinessCustomer.setBusinessId(business.getBusinessId());
        ordersBusinessCustomer.setCustomerId(customerId);
        ordersBusinessCustomer.setUpdateTime(StringUtils.getNowTime());
        AbstractWrapper abstractWrapper2 = new QueryWrapper();
        abstractWrapper2.eq("orders_id", ordersId);
        ordersBusinessCustomerMapper.update(ordersBusinessCustomer, abstractWrapper2);

        return result;
    }

}

