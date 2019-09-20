package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.TaskLogDTO;
import com.manage.cochain.entity.vo.TaskLogVO;
import com.manage.cochain.mapper.TaskLogMapper;
import com.manage.cochain.service.ITaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 任务日志信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 11:45:23
 **/
@Service
public class TaskLogServiceImpl implements ITaskLogService  {

	@Autowired
	private TaskLogMapper taskLogMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return List<TaskLogVO>
	 **/
	@Override
	public Map<String,Object> getTaskLogAllList(TaskLogDTO taskLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<TaskLogVO> taskLogList = new ArrayList<>();
		Integer taskLogTotal = taskLogMapper.findTaskLogTotal(taskLogDTO);
		if(taskLogTotal>0){
			taskLogList = taskLogMapper.getTaskLogAllList(taskLogDTO);
		}
		resultMap.put("rows", taskLogList);
		resultMap.put("total", taskLogTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveTaskLog(TaskLogDTO taskLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(taskLogMapper.saveTaskLog(taskLogDTO)>0){
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
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateTaskLog(TaskLogDTO taskLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(taskLogMapper.updateTaskLog(taskLogDTO)>0){
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
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteTaskLogById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(taskLogMapper.deleteTaskLogById(id)>0){
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
	 * @Date 2019年05月07日 11:45:23
	 * @Param id
	 * @return TaskLogVO
	 **/
	@Override
	public TaskLogVO getTaskLogById(Integer id){
		return taskLogMapper.getTaskLogById(id);
	}
	
}
