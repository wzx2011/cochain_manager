package com.wangzhixuan.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysRoleResource;

/**
 *
 * SysRoleResource 表数据库控制层接口
 *
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    @Select("SELECT e.id AS id FROM sys_role r LEFT JOIN role_resource e ON r.id = e.role_id WHERE r.id = #{id}")
    Long selectIdListByRoleId(@Param("id") Long id);

    @Delete("DELETE FROM sys_role_resource WHERE resource_id = #{resourceId}")
    int deleteByResourceId(@Param("resourceId") Serializable resourceId);

}