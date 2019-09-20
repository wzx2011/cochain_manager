package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.SysUser;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.vo.UserVo;

/**
 *
 * SysUser 表数据库控制层接口
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserVo selectUserVoById(@Param("id") Long id);

    List<Map<String, Object>> selectUserPage(Pagination page,Map<String, Object> params);

    List<UserVo> searchUserByEnterpriseId(Integer enterpriseId);

    /**
     * 根据企业ID获取用户集合
     */
    List<Map<String, Object>> findUserMapByEnterpriseId(String enterpriseId);

    /**
     * 查询待审核用户
     */
    Integer countMainUser();

    /**
     * 根据企业id修改用户有效时间
     * @param paraMap
     * @return
     */
    int updUserDeadlineByEnterpriseId(Map<String, Object> paraMap);

    /**
     * 根据企业id获取对应管理员用户的有效时间
     * @param enterpriseId
     * @return
     */
    String getDeadlineByEnterpriseId(Integer enterpriseId);

    /**
     * 根据企业id获取对应管理员用户的角色名称
     * @param enterpriseId
     * @return
     */
    String getUserRoleTypeName(Integer enterpriseId);
}