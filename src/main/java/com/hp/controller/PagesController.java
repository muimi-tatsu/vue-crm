package com.hp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hp.entity.Customer;
import com.hp.entity.Department;
import com.hp.entity.Employee;
import com.hp.entity.Role;
import com.hp.service.CustomerService;
import com.hp.service.DepartmentService;
import com.hp.service.EmployeeService;
import com.hp.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employee", SecurityUtils.getSubject().getPrincipal());
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        QueryWrapper<Employee> queryWrapper1 = new QueryWrapper<>();
        model.addAttribute("empNum", employeeService.count(queryWrapper1.eq("is_del", 0)));
        QueryWrapper<Department> queryWrapper2 = new QueryWrapper<>();
        model.addAttribute("deptNum", departmentService.count(queryWrapper2.eq("is_del", 0)));
        QueryWrapper<Role> queryWrapper3 = new QueryWrapper<>();
        model.addAttribute("roleNum", roleService.count(queryWrapper3.eq("is_del", 0)));
        QueryWrapper<Customer> queryWrapper4 = new QueryWrapper<>();
        model.addAttribute("custNum", customerService.count(queryWrapper4.eq("is_del", 0)));
        return "welcome";
    }

    @GetMapping("/403")
    public String un() {
        return "403";
    }
}
