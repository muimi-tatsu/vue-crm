package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.entity.Employee;
import com.hp.entity.EmployeeRole;
import com.hp.mapper.EmployeeMapper;
import com.hp.mapper.EmployeeRoleMapper;
import com.hp.service.EmployeeService;
import com.hp.utils.ShiroUtils;
import com.hp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

    @Override
    public IPage<Employee> selectList(Page<Employee> page) {
        return employeeMapper.selectList(page);
    }

    @Override
    public Employee selectByName(String empName) {
        return employeeMapper.selectByName(empName);
    }

    @Override
    public Integer add(Employee employee, Integer roleId) {
        //从ShiroUtils类中随机生成盐
        employee.setSalt(ShiroUtils.randomSalt());
        //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
        employee.setPwd(ShiroUtils.encryptPassword(employee.getPwd(), employee.getCredentialsSalt()));
        employee.setCreateTime(StringUtils.getNowTime());
        employee.setUpdateTime(StringUtils.getNowTime());
        employee.setIsDel(0);
        int result = employeeMapper.insert(employee);

        //将empId和roleId一同插入到  员工与角色关系表
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmpId(employee.getEmpId());
        employeeRole.setRoleId(roleId);
        employeeRole.setCreateTime(StringUtils.getNowTime());
        employeeRole.setUpdateTime(StringUtils.getNowTime());
        employeeRole.setIsDel(0);
        employeeRoleMapper.insert(employeeRole);

        return result;
    }

    @Override
    public Integer update(Employee employee, Integer roleId) {
        //判断用户是否输入密码，如果没有，获取的就是空字符串 ("")  就不修改密码
        if (!"".equals(employee.getPwd())) {
            //从ShiroUtils类中随机生成盐
            employee.setSalt(ShiroUtils.randomSalt());
            //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
            employee.setPwd(ShiroUtils.encryptPassword(employee.getPwd(), employee.getCredentialsSalt()));
        } else {
            employee.setPwd(null);
        }
        //设置更新时间
        employee.setUpdateTime(StringUtils.getNowTime());
        //将信息更新到数据库中（空的属性不修改）
        int result = employeeMapper.updateById(employee);

        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmpId(employee.getEmpId());
        employeeRole.setRoleId(roleId);
        employeeRole.setUpdateTime(StringUtils.getNowTime());
        AbstractWrapper abstractWrapper = new QueryWrapper();
        abstractWrapper.eq("emp_id", employee.getEmpId());
        employeeRoleMapper.update(employeeRole, abstractWrapper);

        return result;
    }

}
