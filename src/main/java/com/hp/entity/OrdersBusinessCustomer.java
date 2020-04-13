package com.hp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders_busi_cust")
public class OrdersBusinessCustomer extends BaseEntity {

    private Integer ordersId;
    private Integer businessId;
    private Integer customerId;

}