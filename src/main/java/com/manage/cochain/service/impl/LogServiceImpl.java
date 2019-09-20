package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.LogDTO;
import com.manage.cochain.entity.vo.LogVO;
import com.manage.cochain.mapper.LogMapper;
import com.manage.cochain.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 日志信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 11:44:02
 **/
@Service
public class LogServiceImpl implements ILogService  {

	@Autowired
	private LogMapper logMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return List<LogVO>
	 **/
	@Override
	public Map<String,Object> getLogAllList(LogDTO logDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<LogVO> logList = new ArrayList<>();
		Integer logTotal = logMapper.findLogTotal(logDTO);
		if(logTotal>0){
			logList = logMapper.getLogAllList(logDTO);
		}
		resultMap.put("rows", logList);
		resultMap.put("total", logTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveLog(LogDTO logDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(logMapper.saveLog(logDTO)>0){
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
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateLog(LogDTO logDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(logMapper.updateLog(logDTO)>0){
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
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteLogById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(logMapper.deleteLogById(id)>0){
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
	 * @Date 2019年05月07日 11:44:02
	 * @Param id
	 * @return LogVO
	 **/
	@Override
	public LogVO getLogById(Integer id){
		return logMapper.getLogById(id);
	}
	
}
