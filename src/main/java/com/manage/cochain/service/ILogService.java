package com.manage.cochain.service;


import com.manage.cochain.entity.vo.LogVO;
import com.manage.cochain.entity.dto.LogDTO;
import java.util.Map;
/**
 * 日志信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 11:44:02
 **/
public interface ILogService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return List<LogVO>
	 **/
	Map<String,Object> getLogAllList(LogDTO logDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 11:44:02
	* @Param LogDTO
	* @return Map
	**/
	Map<String, Object> saveLog(LogDTO logDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 11:44:02
	* @Param LogDTO
	* @return Map
	**/
	Map<String, Object> updateLog(LogDTO logDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 11:44:02
	* @Param LogDTO
	* @return Map
	**/
	Map<String, Object> deleteLogById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 11:44:02
	* @Param id
	* @return LogVO
	**/
	LogVO getLogById(Integer id);
}
