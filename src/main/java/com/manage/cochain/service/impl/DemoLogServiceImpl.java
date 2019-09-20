package com.manage.cochain.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.cochain.mapper.DemoLogMapper;
import com.manage.cochain.entity.vo.DemoLogVO;
import com.manage.cochain.entity.dto.DemoLogDTO;
import com.manage.cochain.service.IDemoLogService;


/**
 * 上链日志信息 Service 实现层
 * @author wzx
 * @create 2019年05月22日 17:17:05
 **/
@Service
public class DemoLogServiceImpl implements IDemoLogService  {

	@Autowired
	private DemoLogMapper demoLogMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return List<DemoLogVO>
	 **/
	@Override
	public Map<String,Object> getDemoLogAllList(DemoLogDTO demoLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<DemoLogVO> demoLogList = new ArrayList<>();
		Integer demoLogTotal = demoLogMapper.findDemoLogTotal(demoLogDTO);
		if(demoLogTotal>0){
			demoLogList = demoLogMapper.getDemoLogAllList(demoLogDTO);
		}
		resultMap.put("rows", demoLogList);
		resultMap.put("total", demoLogTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveDemoLog(DemoLogDTO demoLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoLogMapper.saveDemoLog(demoLogDTO)>0){
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
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateDemoLog(DemoLogDTO demoLogDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoLogMapper.updateDemoLog(demoLogDTO)>0){
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
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteDemoLogById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoLogMapper.deleteDemoLogById(id)>0){
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
	 * @Date 2019年05月22日 17:17:05
	 * @Param id
	 * @return DemoLogVO
	 **/
	@Override
	public DemoLogVO getDemoLogById(Integer id){
		return demoLogMapper.getDemoLogById(id);
	}
	
}
