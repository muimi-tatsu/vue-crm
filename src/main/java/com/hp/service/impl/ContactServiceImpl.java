package com.hp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.entity.Contact;
import com.hp.mapper.ContactMapper;
import com.hp.service.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {
}
