package com.hp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Employee;
import com.hp.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    IPage<Permission> selectList(Page<Permission> page);


}
