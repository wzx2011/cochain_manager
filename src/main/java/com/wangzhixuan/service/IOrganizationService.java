package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.model.SysOrganization;

/**
 *
 * SysOrganization 表数据服务层接口
 *
 */
public interface IOrganizationService extends IService<SysOrganization> {

    List<Tree> selectTree(Map<String,Object> map);

    List<SysOrganization> selectTreeGrid(Map<String,Object> map);

    /**
     * @Author zhangfeng
     * @Description //TODO 查询部门管理列表
     * @Date 2019/3/4 15:43
     * @Param [page, params]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    void selectOrganizationPage(PageInfo pageInfo);

}