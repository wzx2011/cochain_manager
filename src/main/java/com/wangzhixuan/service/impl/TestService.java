package com.wangzhixuan.service.impl;

import java.io.Serializable;

import com.wangzhixuan.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wangzhixuan.mapper.SysUserMapper;

@Service
public class TestService {
    @Autowired
    private SysUserMapper userMapper;
	
    @Cacheable(value = "hour", key = "#id")
	public SysUser selectById(Serializable id) {
		return userMapper.selectById(id);
	}
}
