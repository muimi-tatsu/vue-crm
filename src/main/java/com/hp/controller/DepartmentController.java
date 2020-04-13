package com.hp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Department;
import com.hp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public List<Department> departments() {
        return departmentService.list();
    }

    /**
     * 查询一页部门信息
     *
     * @param page  当前页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/departments")
    public Map getList(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //设置mybatisPlus分页
        Page<Department> p = new Page<Department>();
        //设置每页记录数
        p.setSize(limit);
        //设置当前页码
        p.setCurrent(page);
        IPage<Department> iPage = departmentService.selectList(p);
        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);
        return map;
    }

    /**
     * 新增部门
     */
    @PostMapping("/department")
    public Map add(Department department) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", departmentService.add(department));
        return result;
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/department/del/{deptId}")
    public Map del(@PathVariable Integer deptId) {
        Department department = new Department();
        department.setDeptId(deptId);
        department.setDeptName(" ");
        department.setIsDel(1);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", departmentService.updateById(department));
        return result;
    }

    /**
     * 修改部门
     */
    @PutMapping("/department")
    public Map edit(Department department) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", departmentService.update(department));
        return result;
    }

}