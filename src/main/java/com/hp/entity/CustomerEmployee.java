package com.hp.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer_emp")
public class CustomerEmployee extends BaseEntity {

    private Integer customerId;
    private Integer empId;

}
