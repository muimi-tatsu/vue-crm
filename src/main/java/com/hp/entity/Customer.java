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
@TableName("customer")
public class Customer extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer customerId;
    private String customerName;
    private String sex;
    private String telephone;
    private String company;
    private String address;
    private Integer isOrders;
    @TableField(exist = false)
    private List<Employee> employeeList;
    @TableField(exist = false)
    private List<Contact> contactList;

}
