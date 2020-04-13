package com.hp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.entity.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMapper extends BaseMapper<Contact> {
}
