package com.hp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("contact")
public class Contact extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer contactId;
    private String way;
    private String content;

}
