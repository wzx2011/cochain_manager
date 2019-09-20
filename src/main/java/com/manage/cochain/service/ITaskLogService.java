package com.manage.cochain.service;


import com.manage.cochain.entity.vo.TaskLogVO;
import com.manage.cochain.entity.dto.TaskLogDTO;
import java.util.Map;
/**
 * 任务日志信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 11:45:23
 **/
public interface ITaskLogService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return List<TaskLogVO>
	 **/
	Map<String,Object> getTaskLogAllList(TaskLogDTO taskLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 11:45:23
	* @Param TaskLogDTO
	* @return Map
	**/
	Map<String, Object> saveTaskLog(TaskLogDTO taskLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 11:45:23
	* @Param TaskLogDTO
	* @return Map
	**/
	Map<String, Object> updateTaskLog(TaskLogDTO taskLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 11:45:23
	* @Param TaskLogDTO
	* @return Map
	**/
	Map<String, Object> deleteTaskLogById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 11:45:23
	* @Param id
	* @return TaskLogVO
	**/
	TaskLogVO getTaskLogById(Integer id);
}
