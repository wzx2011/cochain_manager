package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.SysRole;

/**
 *
 * SysRole 表数据服务层接口
 *
 */
public interface IRoleService extends IService<SysRole> {

    void selectDataGrid(PageInfo pageInfo);

    Object selectTree(SysRole role);

    Object selectTree1(SysRole role);

    List<Long> selectResourceIdListByRoleId(Long id);

    void updateRoleResource(Long id, String resourceIds);

    Map<String, Set<String>> selectResourceMapByUserId(Long userId);

    /**
     * @Author zhangfeng
     * @Description //TODO 查询角色类型为系统管理员的角色信息
     * @Date 2019/3/4 11:17
     * @Param []
     * @return java.util.List<com.wangzhixuan.model.SysRole>
     **/
    List<SysRole> getRoleTypeId();
}