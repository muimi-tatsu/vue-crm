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
@TableName("permission")
public class Permission extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer permId;
    private String permName;
    private String url;
    private String permission;
    @TableField(exist = false)
    private List<Role> roles;

}
