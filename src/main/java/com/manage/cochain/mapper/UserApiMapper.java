package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.UserApiVO;
import com.manage.cochain.entity.dto.UserApiDTO;

/**
 * 用户信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 14:03:15
**/
public interface UserApiMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return List<UserApiVO>
	 **/
	List<UserApiVO> getUserApiAllList(UserApiDTO userApiDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return Integer
	 **/
	Integer findUserApiTotal(UserApiDTO userApiDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return int
	 **/
	int saveUserApi(UserApiDTO userApiDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return int
	 **/
	int updateUserApi(UserApiDTO userApiDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return int
	 **/
	int deleteUserApiById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 14:03:15
	 * @Param id
	 * @return UserApiVO
	 **/
	UserApiVO getUserApiById(@Param("id")Integer id);

	/**
	 * 获取用户集合信息
	 * @param userApiDTO
	 * @return
	 */
	List<UserApiVO> getUserApiVOList(UserApiDTO userApiDTO);

}