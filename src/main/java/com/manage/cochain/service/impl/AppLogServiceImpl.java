package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.AppLogDTO;
import com.manage.cochain.entity.vo.AppLogVO;
import com.manage.cochain.mapper.AppLogMapper;
import com.manage.cochain.service.IAppLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 应用日志信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 14:20:29
 **/
@Service
public class AppLogServiceImpl implements IAppLogService  {

	@Autowired
	private AppLogMapper appLogMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return List<AppLogVO>
	 **/
	@Override
	public Map<String,Object> getAppLogAllList(AppLogDTO appLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<AppLogVO> appLogList = new ArrayList<>();
		Integer appLogTotal = appLogMapper.findAppLogTotal(appLogDTO);
		if(appLogTotal>0){
			appLogList = appLogMapper.getAppLogAllList(appLogDTO);
		}
		resultMap.put("rows", appLogList);
		resultMap.put("total", appLogTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveAppLog(AppLogDTO appLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(appLogMapper.saveAppLog(appLogDTO)>0){
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
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateAppLog(AppLogDTO appLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(appLogMapper.updateAppLog(appLogDTO)>0){
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
	 * @Date 2019年05月07日 14:20:29
	 * @Param AppLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteAppLogById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(appLogMapper.deleteAppLogById(id)>0){
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
	 * @Date 2019年05月07日 14:20:29
	 * @Param id
	 * @return AppLogVO
	 **/
	@Override
	public AppLogVO getAppLogById(Integer id){
		return appLogMapper.getAppLogById(id);
	}
	
}
