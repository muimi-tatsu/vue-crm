package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.entity.Permission;
import com.hp.entity.RolePermission;
import com.hp.mapper.PermissionMapper;
import com.hp.mapper.RolePermissionMapper;
import com.hp.service.PermissionService;
import com.hp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public IPage<Permission> selectList(Page<Permission> page) {
        return permissionMapper.selectList(page);
    }

    @Override
    public Integer add(Permission permission, Integer roleId) {
        permission.setCreateTime(StringUtils.getNowTime());
        permission.setUpdateTime(StringUtils.getNowTime());
        permission.setIsDel(0);
        int result = permissionMapper.insert(permission);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermId(permission.getPermId());
        rolePermission.setRoleId(roleId);
        rolePermission.setCreateTime(StringUtils.getNowTime());
        rolePermission.setUpdateTime(StringUtils.getNowTime());
        rolePermission.setIsDel(0);
        rolePermissionMapper.insert(rolePermission);
        return result;
    }

    @Override
    public Integer update(Permission permission, Integer roleId) {
        permission.setUpdateTime(StringUtils.getNowTime());
        int result = permissionMapper.updateById(permission);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermId(permission.getPermId());
        rolePermission.setRoleId(roleId);
        rolePermission.setUpdateTime(StringUtils.getNowTime());
        AbstractWrapper abstractWrapper = new QueryWrapper();
        abstractWrapper.eq("perm_id", permission.getPermId());
        rolePermissionMapper.update(rolePermission, abstractWrapper);
        return result;
    }
}
