package com.hp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dept_role")
public class DepartmentRole extends BaseEntity {

    private Integer deptId;
    private Integer roleId;

}
