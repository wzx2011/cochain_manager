package com.wangzhixuan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysResource;

/**
 *
 * SysResource 表数据服务层接口
 *
 */
public interface IResourceService extends IService<SysResource> {

    List<SysResource> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectAllShiroTree(ShiroUser shiroUser);

    List<Tree> selectTree(ShiroUser shiroUser);

    /**
     * 首页 根据角色查询菜单信息
     * @param shiroUser
     * @return
     */
    List<SysResource> selectResourcelListMain(ShiroUser shiroUser);

}