package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.TaskDTO;
import com.manage.cochain.entity.vo.TaskVO;
import com.manage.cochain.mapper.TaskMapper;
import com.manage.cochain.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 任务信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 11:44:45
 **/
@Service
public class TaskServiceImpl implements ITaskService  {

	@Autowired
	private TaskMapper taskMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return List<TaskVO>
	 **/
	@Override
	public Map<String,Object> getTaskAllList(TaskDTO taskDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<TaskVO> taskList = new ArrayList<>();
		Integer taskTotal = taskMapper.findTaskTotal(taskDTO);
		if(taskTotal>0){
			taskList = taskMapper.getTaskAllList(taskDTO);
		}
		resultMap.put("rows", taskList);
		resultMap.put("total", taskTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveTask(TaskDTO taskDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(taskMapper.saveTask(taskDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "添加成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "添加失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateTask(TaskDTO taskDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(taskMapper.updateTask(taskDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteTaskById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(taskMapper.deleteTaskById(id)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:44:45
	 * @Param id
	 * @return TaskVO
	 **/
	@Override
	public TaskVO getTaskById(Integer id){
		return taskMapper.getTaskById(id);
	}
	
}
