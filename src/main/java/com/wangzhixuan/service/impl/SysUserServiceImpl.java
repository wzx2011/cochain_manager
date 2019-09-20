package com.wangzhixuan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manage.util.StringUtil;
import com.manage.util.TimeUtil;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.mapper.SysUserRoleMapper;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.ISysUserService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * SysUser 表数据服务层接口实现类
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysOrganizationMapper organizationMapper;

    
    @Override
    public List<SysUser> selectByLoginName(UserVo userVo) {
        SysUser user = new SysUser();
        user.setLoginName(userVo.getLoginName());
        EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>(user);
        if (null != userVo.getId()) {
            wrapper.where("id != {0}", userVo.getId());
        }
        return this.selectList(wrapper);
    }

    @Override
    public void insertByVo(UserVo userVo) {
        SysUser user = BeanUtils.copy(userVo, SysUser.class);
        user.setCreateTime(new Date());
        user.setDeadline(TimeUtil.strParseDate(userVo.getDeadlineStr()));
        // 获取企业id
        Integer enterpriseId = userVo.getEnterpriseId();
        // 根据企业id获取企业所对应的管理员用户的有效时间
        String deadline = userMapper.getDeadlineByEnterpriseId(enterpriseId);
        if (null != deadline && deadline.length() != 0){
            user.setDeadline(TimeUtil.strParseDate(deadline));
        }
        this.insert(user);

        Long id = user.getId();
        if(StringUtil.isNotBlank(userVo.getRoleIds())){
            String[] roles = userVo.getRoleIds().split(",");
            SysUserRole userRole = new SysUserRole();
            for (String string : roles) {
                userRole.setUserId(id);
                userRole.setRoleId(Long.valueOf(string));
                userRoleMapper.insert(userRole);
            }
        }

    }
    /**
     * @Author zhangfeng
     * @Description //TODO 用户注册 用户类型是管理员，则创建企业id和用户id，状态为未审核
     * @Date 2019/1/10 9:44
     * @Param [userVo]
     * @return void
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(UserVo userVo) {
        SysUser user = BeanUtils.copy(userVo, SysUser.class);
        // 管理员
        if(user.getUserType().equals(0)){
            // 创建企业id
            Map<String,Object> jsonData = new HashMap<>();
            Integer enterpriseId = Integer.parseInt(jsonData.get("id").toString());
            // 创建部门
            SysOrganization organization = new SysOrganization();
            organization.setName("部门");
            organization.setCode("01");
            organization.setSeq(0);
            organization.setEnterpriseId(enterpriseId);
            organizationMapper.insertOrganizationId(organization);
            Integer organizationId = organization.getId().intValue();
            user.setOrganizationId(organizationId);
            user.setEnterpriseId(enterpriseId);
            //状态为 未审核
            user.setStatus(2);
        }
        user.setCreateTime(new Date());
        if(StringUtil.isNotBlank(userVo.getDeadlineStr())){
            user.setDeadline(TimeUtil.strParseDate(userVo.getDeadlineStr()));
        }
        this.insert(user);
        Long userId = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        SysUserRole userRole = new SysUserRole();
        for (String string : roles) {
            userRole.setUserId(userId);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public UserVo selectVoById(Long id) {
        return userMapper.selectUserVoById(id);
    }

    @Override
    public void updateByVo(UserVo userVo) {
        SysUser user = BeanUtils.copy(userVo, SysUser.class);
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        }
        if(StringUtils.isNotBlank(userVo.getDeadlineStr())){
            user.setDeadline(TimeUtil.strParseDate(userVo.getDeadlineStr()));
        }
        this.updateById(user);
        
        Long id = userVo.getId();
        List<SysUserRole> userRoles = userRoleMapper.selectByUserId(id);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (SysUserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }

        String[] roles = userVo.getRoleIds().split(",");
        SysUserRole userRole = new SysUserRole();
        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
        // 获取
        long userId = userVo.getId();
        UserVo userVoAdmin = userMapper.selectUserVoById(userId);
        // 获取管理员企业id
        int enterpriseId = userVoAdmin.getEnterpriseId();
        // 获取有效时间
        String deadlineStr =userVo.getDeadlineStr();
        if (null != deadlineStr && deadlineStr.length() != 0){
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("enterpriseId",enterpriseId);
            paraMap.put("deadline",TimeUtil.strParseDate(deadlineStr));
            // 修改该企业对应用户的有效时间
            userMapper.updUserDeadlineByEnterpriseId(paraMap);
        }
    }

    @Override
    public void updatePwdByUserId(Long userId, String md5Hex) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setPassword(md5Hex);
        this.updateById(user);
    }

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
        page.setOrderByField(pageInfo.getSort());
        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
        List<Map<String, Object>> list = userMapper.selectUserPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void deleteUserById(Long id) {
        this.deleteById(id);
        userRoleMapper.deleteByUserId(id);
    }

    /**
     * 根据企业id修改用户有效时间
     *
     * @param paraMap
     * @return
     */
    @Override
    public int updUserDeadlineByEnterpriseId(Map<String, Object> paraMap) {
        return userMapper.updUserDeadlineByEnterpriseId(paraMap);
    }

    /**
     * 根据企业id获取对应管理员用户的有效时间
     *
     * @param enterpriseId
     * @return
     */
    @Override
    public String getDeadlineByEnterpriseId(Integer enterpriseId) {
        return userMapper.getDeadlineByEnterpriseId(enterpriseId);
    }

    @Override
    public List<UserVo> searchUserByEnterpriseId(Integer enterpriseId){
    	List<UserVo> userVo = userMapper.searchUserByEnterpriseId(enterpriseId);
        return userVo;
    }

    /**
     * 查询待审核用户
     *
     * @return
     */
    @Override
    public Integer countMainUser() {
        return userMapper.countMainUser();
    }

    /**
     * 根据企业id获取对应管理员用户的角色名称
     *
     * @param enterpriseId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getUserRoleTypeName(Integer enterpriseId) {
        return userMapper.getUserRoleTypeName(enterpriseId);
    }
}