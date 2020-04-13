package com.hp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends BaseMapper<Department> {

    IPage<Department> selectList(Page<Department> page);

}
