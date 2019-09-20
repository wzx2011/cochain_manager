package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.SysResource;
import com.wangzhixuan.model.SysRole;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 *
 * SysRole 表数据库控制层接口
 *
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<Long> selectResourceIdListByRoleId(@Param("id") Long id);

    List<SysResource> selectResourceListByRoleIdList(@Param("list") List<Long> list);

    List<SysResource> selectResourceListByResourceList(Map<String,Object> paramMap);

    List<Map<String, String>> selectResourceListByRoleId(@Param("id") Long id);

}