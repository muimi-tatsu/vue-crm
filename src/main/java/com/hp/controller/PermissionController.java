package com.hp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Permission;
import com.hp.service.PermissionService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService service;

    @GetMapping("/per")
    public Map getList(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();

        Page<Permission> p = new Page<Permission>();
        p.setSize(limit);
        p.setCurrent(page);

        IPage<Permission> iPage = service.selectList(p);

        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);
        return map;
    }

    @PostMapping("/per")
    public Map add(Permission permission, Integer permId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", service.add(permission, permId));
        return result;
    }

    @DeleteMapping("/per/del/{permId}")
    public Map del(@PathVariable Integer permId) {
        Permission permission = new Permission();
        permission.setPermId(permId);
        permission.setIsDel(1);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", service.updateById(permission));
        return result;

    }

    @PutMapping("/per")
    public Map edit(Permission permission, Integer permId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", service.update(permission, permId));
        return result;
    }

}
