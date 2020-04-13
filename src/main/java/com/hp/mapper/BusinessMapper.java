package com.hp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.entity.Business;

import org.springframework.stereotype.Repository;


@Repository
public interface BusinessMapper extends BaseMapper<Business> {

    IPage<Business> selectList(Page<Business> page);

}
