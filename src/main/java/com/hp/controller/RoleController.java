package com.hp.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Role;
import com.hp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-03-24
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public List<Role> roles() {
        return roleService.list();
    }

    /**
     * 查询一页职位信息
     *
     * @param page  当前页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/roles")
    public Map getList(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //设置mybatisPlus分页
        Page<Role> p = new Page<Role>();
        //设置每页记录数
        p.setSize(limit);
        //设置当前页码
        p.setCurrent(page);
        IPage<Role> iPage = roleService.selectList(p);
        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);
        return map;
    }

    /**
     * 新增职位
     */
    @PostMapping("/role")
    public Map add(Role role, Integer deptId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", roleService.add(role, deptId));
        return result;
    }

    /**
     * 删除职位
     */
    @DeleteMapping("/role/del/{roleId}")
    public Map del(@PathVariable Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(" ");
        role.setIsDel(1);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", roleService.updateById(role));
        return result;
    }

    /**
     * 修改用户
     */
    @PutMapping("/role")
    public Map edit(Role role, Integer deptId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", roleService.update(role, deptId));
        return result;
    }

}
