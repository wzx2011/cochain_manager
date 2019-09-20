package com.manage.cochain.service;


import com.manage.cochain.entity.vo.UserApiVO;
import com.manage.cochain.entity.dto.UserApiDTO;
import com.manage.util.ResponseData;

import java.util.List;
import java.util.Map;
/**
 * 用户信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 14:03:15
 **/
public interface IUserApiService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return List<UserApiVO>
	 **/
	Map<String,Object> getUserApiAllList(UserApiDTO userApiDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 14:03:15
	* @Param UserApiDTO
	* @return Map
	**/
	Map<String, Object> saveUserApi(UserApiDTO userApiDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 14:03:15
	* @Param UserApiDTO
	* @return Map
	**/
	Map<String, Object> updateUserApi(UserApiDTO userApiDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 14:03:15
	* @Param UserApiDTO
	* @return Map
	**/
	Map<String, Object> deleteUserApiById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 14:03:15
	* @Param id
	* @return UserApiVO
	**/
	UserApiVO getUserApiById(Integer id);

	/**
	 * 通过接口新增用户
	 * @param appId
	 * @param secretkey
	 * @return
	 */
	ResponseData doAddUserByCallInterface(int appId, String secretkey);

	/**
	 * 获取用户集合信息
	 * @param userApiDTO
	 * @return
	 */
	List<UserApiVO> getUserApiVOList(UserApiDTO userApiDTO);
}
