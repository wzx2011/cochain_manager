package com.manage.cochain.service;


import com.manage.cochain.entity.vo.AppLogVO;
import com.manage.cochain.entity.dto.AppLogDTO;
import java.util.Map;
/**
 * 应用日志信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 14:20:29
 **/
public interface IAppLogService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return List<AppLogVO>
	 **/
	Map<String,Object> getAppLogAllList(AppLogDTO appLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 14:20:29
	* @Param AppLogDTO
	* @return Map
	**/
	Map<String, Object> saveAppLog(AppLogDTO appLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 14:20:29
	* @Param AppLogDTO
	* @return Map
	**/
	Map<String, Object> updateAppLog(AppLogDTO appLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 14:20:29
	* @Param AppLogDTO
	* @return Map
	**/
	Map<String, Object> deleteAppLogById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 14:20:29
	* @Param id
	* @return AppLogVO
	**/
	AppLogVO getAppLogById(Integer id);
}
