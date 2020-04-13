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
@TableName("department")
public class Department extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer deptId;
    private String deptName;
    @TableField(exist = false)
    private List<Role> roles1;


}
