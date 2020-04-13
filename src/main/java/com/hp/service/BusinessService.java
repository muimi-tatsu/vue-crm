package com.hp.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hp.entity.Business;
import org.springframework.stereotype.Service;


public interface BusinessService extends IService<Business> {

    IPage<Business> selectList(Page<Business> page);

    Integer update(Business business, Integer ordersId, String totalPrice, Integer customerId);

    Integer add(Business business, String totalPrice, Integer customerId);

}
