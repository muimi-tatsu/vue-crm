package com.hp.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cont_cust_emp")
public class ContactCustomerEmployee extends BaseEntity {


    private Integer contactId;
    private Integer customerId;
    private Integer empId;

}
