package com.wangzhixuan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.manage.util.StringUtil;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.SysOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.service.IOrganizationService;

/**
 * @description：部门管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @GetMapping(value = "/manager")
    public String manager() {
        return "admin/organization/organization";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @PostMapping(value = "/tree")
    @ResponseBody
    public Object tree() {
        Map<String,Object> map = new HashMap<>();
        Set<String> roles = getShiroUser().getRoles();
        Integer enterpriseId = null;
        // 如果没有超级管理员权限
        if (!roles.contains("admin")) {
            enterpriseId = getShiroUser().getEnterpriseId();
        }
        map.put("enterpriseId",enterpriseId);
        return organizationService.selectTree(map);
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid(Integer page, Integer rows, String sort, String order,Integer enterpriseId) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        Set<String> roles = getShiroUser().getRoles();
        // 如果没有超级管理员权限
        if (!roles.contains("admin")) {
            enterpriseId = getShiroUser().getEnterpriseId();
        }

        condition.put("enterpriseId",enterpriseId);
        pageInfo.setCondition(condition);
        organizationService.selectOrganizationPage(pageInfo);
        return pageInfo;
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/organization/organizationAdd";
    }

    /**
     * 添加部门
     *
     * @param organization
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(@Valid SysOrganization organization) {
        organization.setCreateTime(new Date());
        Set<String> roles = getShiroUser().getRoles();
        // 如果没有超级管理员权限
        if (!roles.contains("admin")) {
            Integer enterpriseId = getShiroUser().getEnterpriseId();
            organization.setEnterpriseId(enterpriseId);
        }
        organizationService.insert(organization);
        return renderSuccess("添加成功！");
    }

    /**
     * 编辑部门页
     *
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        SysOrganization organization = organizationService.selectById(id);
        model.addAttribute("organization", organization);
        return "admin/organization/organizationEdit";
    }

    /**
     * 编辑部门
     *
     * @param organization
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(@Valid SysOrganization organization) {
        if(StringUtil.isBlank(organization.getPid())){
            organization.setPid(new Long(0));
        }
        organizationService.updateById(organization);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        organizationService.deleteById(id);
        return renderSuccess("删除成功！");
    }
}