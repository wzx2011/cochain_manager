package com.manage.cochain.service;


import com.manage.cochain.entity.vo.TaskVO;
import com.manage.cochain.entity.dto.TaskDTO;
import java.util.Map;
/**
 * 任务信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 11:44:45
 **/
public interface ITaskService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return List<TaskVO>
	 **/
	Map<String,Object> getTaskAllList(TaskDTO taskDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 11:44:45
	* @Param TaskDTO
	* @return Map
	**/
	Map<String, Object> saveTask(TaskDTO taskDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 11:44:45
	* @Param TaskDTO
	* @return Map
	**/
	Map<String, Object> updateTask(TaskDTO taskDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 11:44:45
	* @Param TaskDTO
	* @return Map
	**/
	Map<String, Object> deleteTaskById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 11:44:45
	* @Param id
	* @return TaskVO
	**/
	TaskVO getTaskById(Integer id);
}
