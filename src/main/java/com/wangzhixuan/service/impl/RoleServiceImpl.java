package com.wangzhixuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.manage.util.StringUtil;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.SysRoleMapper;
import com.wangzhixuan.mapper.SysRoleResourceMapper;
import com.wangzhixuan.mapper.SysUserRoleMapper;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysRoleResource;
import com.wangzhixuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * SysRole 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements IRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    
    public List<SysRole> selectAll() {
        EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        wrapper.orderBy("seq");
        return roleMapper.selectList(wrapper);
    }
    public List<SysRole> selectAllByRole(SysRole roleParam) {
        EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        if(StringUtil.isNotBlank(roleParam.getName()) && "admin".equals(roleParam.getName())){
            wrapper.where("role_type in (0,1) ");
        }
        if(StringUtil.isNotBlank(roleParam.getEnterpriseId()) && StringUtil.isNotBlank(roleParam.getName())){
            wrapper.where("(enterprise_id = {0} or name = {1})",roleParam.getEnterpriseId(),roleParam.getName());
        }
        wrapper.orderBy("seq");
        return roleMapper.selectList(wrapper);
    }
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<SysRole> page = new Page<SysRole>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        if(StringUtil.isNotBlank(condition.get("enterpriseId"))){
            wrapper.where("enterprise_id = {0}",condition.get("enterpriseId"));
        }
        if(StringUtil.isNotBlank(condition.get("roleTypes"))){
            wrapper.where("role_type in (0,1) ");
        }
        wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
        selectPage(page, wrapper);
        
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public Object selectTree(SysRole roleParam) {
        List<Tree> trees = new ArrayList<Tree>();
        List<SysRole> roles = this.selectAllByRole(roleParam);
        for (SysRole role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    @Override
    public Object selectTree1(SysRole roleParam) {
        List<Tree> trees = new ArrayList<Tree>();
        List<SysRole> roles = this.selectAllByRole(roleParam);
        for (SysRole role : roles) {
            Tree tree = new Tree();
            String name = role.getName();
            if(name.equals("gys") || name.equals("zjf") || name.equals("hxqy") || name.equals("admin")){
                continue;
            } else {
                tree.setId(role.getId());
                tree.setText(role.getName());
                trees.add(tree);
            }
        }
        return trees;
    }

    @Override
    public void updateRoleResource(Long roleId, String resourceIds) {
        // 先删除后添加,有点爆力
        SysRoleResource roleResource = new SysRoleResource();
        roleResource.setRoleId(roleId);
        roleResourceMapper.delete(new EntityWrapper<SysRoleResource>(roleResource));
        
        // 如果资源id为空，判断为清空角色资源
        if (StringUtils.isBlank(resourceIds)) {
            return;
        }
        
        String[] resourceIdArray = resourceIds.split(",");
        for (String resourceId : resourceIdArray) {
            roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Long.parseLong(resourceId));
            roleResourceMapper.insert(roleResource);
        }
    }

    @Override
    public List<Long> selectResourceIdListByRoleId(Long id) {
        return roleMapper.selectResourceIdListByRoleId(id);
    }
    
    @Override
    public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(userId);
        Set<String> urlSet = new HashSet<String>();
        Set<String> roles = new HashSet<String>();
        Set<String> roleTypes = new HashSet<>();
        for (Long roleId : roleIdList) {
            List<Map<String, String>> resourceList = roleMapper.selectResourceListByRoleId(roleId);
            if (resourceList != null && !resourceList.isEmpty()) {
                for (Map<String, String> map : resourceList) {
                    if (map != null && StringUtils.isNotBlank(map.get("url"))) {
                        urlSet.add(map.get("url"));
                    }
                }
            }
            SysRole role = roleMapper.selectById(roleId);
            if (role != null) {
                roles.add(role.getName());
                roleTypes.add(role.getRoleType().toString());
            }
        }
        resourceMap.put("urls", urlSet);
        resourceMap.put("roles", roles);
        resourceMap.put("roleTypes", roleTypes);
        return resourceMap;
    }


    @Override
    public List<SysRole> getRoleTypeId(){
        EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        wrapper.where(" role_type = {0}",1);
        wrapper.orderBy("seq");
        return roleMapper.selectList(wrapper);
    }

}