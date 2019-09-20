package com.manage.cochain.service;


import com.manage.cochain.entity.vo.AppVO;
import com.manage.cochain.entity.dto.AppDTO;

import java.util.List;
import java.util.Map;
/**
 * 应用信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 14:19:49
 **/
public interface IAppService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return List<AppVO>
	 **/
	Map<String,Object> getAppAllList(AppDTO appDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 14:19:49
	* @Param AppDTO
	* @return Map
	**/
	Map<String, Object> saveApp(AppDTO appDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 14:19:49
	* @Param AppDTO
	* @return Map
	**/
	Map<String, Object> updateApp(AppDTO appDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 14:19:49
	* @Param AppDTO
	* @return Map
	**/
	Map<String, Object> deleteAppById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 14:19:49
	* @Param id
	* @return AppVO
	**/
	AppVO getAppById(Integer id);

	/**
	 * 获取应用的所用集合信息
	 * @param appDTO
	 * @return
	 */
	List<AppVO> getAppVOList(AppDTO appDTO);
}
