package com.wangzhixuan.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.mapper.SysUserRoleMapper;
import com.wangzhixuan.model.SysUserRole;
import com.wangzhixuan.service.IUserRoleService;

/**
 *
 * SysUserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements IUserRoleService {

}