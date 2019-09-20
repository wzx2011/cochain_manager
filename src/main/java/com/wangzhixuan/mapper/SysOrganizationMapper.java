package com.wangzhixuan.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.SysOrganization;

import java.util.List;
import java.util.Map;

/**
 *
 * SysOrganization 表数据库控制层接口
 *
 */
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {

    /**
     * @Author zhangfeng
     * @Description //TODO 新增部门
     * @Date 2019/1/10 9:59
     * @Param [organization]
     * @return int
     **/
    int insertOrganizationId(SysOrganization organization);

    /**
     * @Author zhangfeng
     * @Description //TODO 查询部门管理列表
     * @Date 2019/3/4 15:43
     * @Param [page, params]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>> selectOrganizationPage(Pagination page, Map<String, Object> params);
    /**
     * @Author zhangfeng
     * @Description //TODO 查询部门管理集合
     * @Date 2019/3/7 14:59
     * @Param [params]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<SysOrganization> selectOrganizationList(Map<String, Object> params);
}