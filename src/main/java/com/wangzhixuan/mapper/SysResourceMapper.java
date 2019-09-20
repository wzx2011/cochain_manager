package com.wangzhixuan.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysResource;

import java.util.List;
import java.util.Map;

/**
 *
 * SysResource 表数据库控制层接口
 *
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
    /**
     * 首页 根据角色查询菜单信息
     * @param paramMap
     * @return
     */
    List<SysResource> selectResourcelListMain(Map<String,Object> paramMap);

}