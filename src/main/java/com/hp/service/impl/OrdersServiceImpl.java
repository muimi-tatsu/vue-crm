package com.hp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.entity.Orders;
import com.hp.mapper.OrdersMapper;
import com.hp.service.OrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
