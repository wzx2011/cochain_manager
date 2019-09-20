package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;

/**
 *
 * SysUser 表数据服务层接口
 *
 */
public interface ISysUserService extends IService<SysUser> {

    List<SysUser> selectByLoginName(UserVo userVo);

    void insertByVo(UserVo userVo);
    /**
     * @Author zhangfeng
     * @Description //TODO 用户注册 用户类型是管理员，则创建企业id和用户id，状态为未审核
     * @Date 2019/1/10 9:44
     * @Param [userVo]
     * @return void
     **/
    void insertUser(UserVo userVo);

    UserVo selectVoById(Long id);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(Long userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Long id);

    List<UserVo> searchUserByEnterpriseId(Integer enterpriseId);

    /**
     * 查询待审核用户
     * @return
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