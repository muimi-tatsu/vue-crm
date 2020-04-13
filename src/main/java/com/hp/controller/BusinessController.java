package com.hp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Business;
import com.hp.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 查询一页业务信息
     *
     * @param page  当前页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/business")
    public Map getList(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Business> p = new Page<Business>();
        p.setSize(limit);
        p.setCurrent(page);
        IPage<Business> iPage = businessService.selectList(p);
        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);
        return map;
    }

    /**
     * 新增业务
     */
    @PostMapping("/business")
    public Map add(Business business, String totalPrice, Integer customerId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", businessService.add(business, totalPrice, customerId));
        return result;
    }

    /**
     * 修改部门
     */
    @PutMapping("/business")
    public Map edit(Business business, Integer ordersId, String totalPrice, Integer customerId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", businessService.update(business, ordersId, totalPrice, customerId));
        return result;
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/business/del/{businessId}")
    public Map del(@PathVariable Integer businessId) {
        Business business = new Business();
        business.setBusinessId(businessId);
        business.setIsDel(1);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", businessService.updateById(business));
        return result;
    }

}
