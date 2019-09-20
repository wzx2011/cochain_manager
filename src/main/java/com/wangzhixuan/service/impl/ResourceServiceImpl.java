package com.wangzhixuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.mapper.SysResourceMapper;
import com.wangzhixuan.mapper.SysRoleMapper;
import com.wangzhixuan.mapper.SysRoleResourceMapper;
import com.wangzhixuan.mapper.SysUserRoleMapper;
import com.wangzhixuan.model.SysResource;
import com.wangzhixuan.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 *
 * SysResource 表数据服务层接口实现类
 *
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements IResourceService {
    private static final int RESOURCE_MENU = 0; // 菜单

    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    @Override
    public List<SysResource> selectAll() {
        return selectAllByStatus(null);
    }

    private List<SysResource> selectAllByStatus(Integer status) {
        SysResource resource = new SysResource();
        if (status != null) {
            resource.setStatus(status);
        }
        EntityWrapper<SysResource> wrapper = new EntityWrapper<SysResource>(resource);
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }

    private List<SysResource> selectByType(Integer type) {
        SysResource resource = new SysResource();
        resource.setResourceType(type);
        resource.setStatus(0);
        EntityWrapper<SysResource> wrapper = new EntityWrapper<SysResource>(resource);
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    @Override
    public List<Tree> selectAllMenu() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有菜单
        List<SysResource> resources = this.selectByType(RESOURCE_MENU);
        if (resources == null) {
            return trees;
        }
        trees =  publicList(resources);
        return trees;
    }
    /**
     * @Author zhangfeng
     * @Description //TODO 根据 角色查询相应的菜单树
     * @Date 2019/1/9 16:20
     * @Param [shiroUser]
     * @return java.util.List<com.wangzhixuan.commons.result.Tree>
     **/
    @Override
    public List<Tree> selectAllShiroTree(ShiroUser shiroUser) {
        // 获取所有的资源 tree形式，展示
        List<Tree> trees = new ArrayList<Tree>();
        // shiro中缓存的用户角色
        Set<String> roles = shiroUser.getRoles();
        if (roles == null) {
            return trees;
        }
        // 如果有超级管理员权限
        if (roles.contains("admin")) {
            List<SysResource> resourceList = this.selectAllByStatus(0);
            if (resourceList == null) {
                return trees;
            }
            trees = publicList(resourceList);
            return trees;
        }
        Map<String,Object> parmaMap = new HashMap<>();
        // 普通用户 查询出相关角色信息
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        if (roleIdList == null) {
            return trees;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",roleIdList);
        // 根据角色查询资源信息
        List<SysResource> resourceLists = roleMapper.selectResourceListByResourceList(map);
        if (resourceLists == null) {
            return trees;
        }
        trees = publicList(resourceLists);
        return trees;
    }

    public List<Tree> publicList(List<SysResource> resourceList){
        // 获取所有的资源 tree形式，展示
        List<Tree> trees = new ArrayList<Tree>();
        for (SysResource resource : resourceList) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            tree.setOpenMode(resource.getOpenMode());
            tree.setState(resource.getOpened());
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<Tree> selectAllTree() {
        // 获取所有的资源 tree形式，展示
        List<Tree> trees = new ArrayList<Tree>();
        List<SysResource> resources = this.selectAllByStatus(0);
        if (resources == null) {
            return trees;
        }
        trees =  publicList(resources);
        return trees;
    }
    
    @Override
    public List<Tree> selectTree(ShiroUser shiroUser) {
        List<Tree> trees = new ArrayList<Tree>();
        // shiro中缓存的用户角色
        Set<String> roles = shiroUser.getRoles();
        if (roles == null) {
            return trees;
        }
        // 如果有超级管理员权限
        if (roles.contains("admin")) {
            List<SysResource> resourceList = this.selectByType(RESOURCE_MENU);
            if (resourceList == null) {
                return trees;
            }
            trees =  publicList(resourceList);
            return trees;
        }
        // 普通用户
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        if (roleIdList == null) {
            return trees;
        }
        List<SysResource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
        if (resourceLists == null) {
            return trees;
        }
        trees =  publicList(resourceLists);
        return trees;
    }

	@Override
	public boolean deleteById(Serializable resourceId) {
		roleResourceMapper.deleteByResourceId(resourceId);
		return super.deleteById(resourceId);
	}

    /**
     * 首页 根据角色查询菜单信息
     *
     * @param shiroUser
     * @return
     */
    @Override
    public List<SysResource> selectResourcelListMain(ShiroUser shiroUser) {
        List<SysResource> resourceList = new ArrayList<>();
        // 普通用户 查询出相关角色信息
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        if (roleIdList == null) {
            return resourceList;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",roleIdList);
        map.put("userId",shiroUser.getId());
        resourceList = resourceMapper.selectResourcelListMain(map);
        return resourceList;
    }
}