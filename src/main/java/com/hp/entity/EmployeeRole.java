package com.hp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emp_role")
public class EmployeeRole extends BaseEntity {

    private Integer empId;
    private Integer roleId;

}
