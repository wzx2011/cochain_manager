package com.wangzhixuan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.manage.util.StringUtil;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.SysOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.service.IOrganizationService;

/**
 *
 * SysOrganization 表数据服务层接口实现类
 *
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization> implements IOrganizationService {

    @Autowired
    private SysOrganizationMapper organizationMapper;
    
    @Override
    public List<Tree> selectTree(Map<String,Object> map) {
        List<SysOrganization> organizationList = organizationMapper.selectOrganizationList(map);

        List<Tree> trees = new ArrayList<Tree>();
        if (organizationList != null) {
            for (SysOrganization organization : organizationList) {
                if(StringUtil.isBlank(organization.getPid())){
                    // 如果父节点为null 则把企业id作为父节点加入tree
                    // 为了区分企业id和部门id，在保存企业id时加一个‘-’ 负数符号
                    Tree tree = new Tree();
                    tree.setId(-organization.getEnterpriseId().longValue());
                    tree.setText(organization.getEnterpriseName());
                    tree.setIconCls(organization.getIcon());
                    tree.setPid(organization.getPid());
                    trees.add(tree);
                    // 子节点
                    Tree tree2 = new Tree();
                    tree2.setId(organization.getId());
                    tree2.setText(organization.getName());
                    tree2.setIconCls(organization.getIcon());
                    tree2.setPid(-organization.getEnterpriseId().longValue());
                    trees.add(tree2);
                }else{
                    Tree tree = new Tree();
                    tree.setId(organization.getId());
                    tree.setText(organization.getName());
                    tree.setIconCls(organization.getIcon());
                    tree.setPid(organization.getPid());
                    trees.add(tree);
                }

            }
        }
        return trees;
    }

    @Override
    public List<SysOrganization> selectTreeGrid(Map<String,Object> map) {
        EntityWrapper<SysOrganization> wrapper = new EntityWrapper<SysOrganization>();
        if(StringUtil.isNotBlank("map") && StringUtil.isNotBlank(map.get("enterpriseId"))){
            wrapper.where("enterprise_id = {0}",map.get("enterpriseId"));
        }
        wrapper.orderBy("seq");
        return organizationMapper.selectList(wrapper);
    }

    /**
     * @Author zhangfeng
     * @Description //TODO 查询部门管理列表
     * @Date 2019/3/4 15:43
     * @Param [page, params]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public void selectOrganizationPage(PageInfo pageInfo) {
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
        page.setOrderByField(pageInfo.getSort());
        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
        List<Map<String, Object>> list = organizationMapper.selectOrganizationPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }



}