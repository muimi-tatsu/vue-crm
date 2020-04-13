package com.hp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("business")
public class Business extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer businessId;
    private String businessName;
    private String head;
    private String telephone;
    private String description;
    @TableField(exist = false)
    private List<Orders> ordersList;
    @TableField(exist = false)
    private List<Customer> customerList;

}
