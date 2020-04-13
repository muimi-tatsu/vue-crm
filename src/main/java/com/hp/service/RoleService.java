package com.hp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;


public interface RoleService extends IService<Role> {

    IPage<Role> selectList(Page<Role> page);

    Integer add(Role role, Integer deptId);

    Integer update(Role role, Integer deptId);


}
