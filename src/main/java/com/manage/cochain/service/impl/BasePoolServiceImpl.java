package com.manage.cochain.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.cochain.mapper.BasePoolMapper;
import com.manage.cochain.entity.vo.BasePoolVO;
import com.manage.cochain.entity.dto.BasePoolDTO;
import com.manage.cochain.service.IBasePoolService;


/**
 * 基础数据池 Service 实现层
 * @author wzx
 * @create 2019年08月22日 10:21:07
 **/
@Service
public class BasePoolServiceImpl implements IBasePoolService  {

	@Autowired
	private BasePoolMapper basePoolMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return List<BasePoolVO>
	 **/
	@Override
	public Map<String,Object> getBasePoolAllList(BasePoolDTO basePoolDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<BasePoolVO> basePoolList = new ArrayList<>();
		Integer basePoolTotal = basePoolMapper.findBasePoolTotal(basePoolDTO);
		if(basePoolTotal>0){
			basePoolList = basePoolMapper.getBasePoolAllList(basePoolDTO);
		}
		resultMap.put("rows", basePoolList);
		resultMap.put("total", basePoolTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveBasePool(BasePoolDTO basePoolDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(basePoolMapper.saveBasePool(basePoolDTO)>0){
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
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateBasePool(BasePoolDTO basePoolDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(basePoolMapper.updateBasePool(basePoolDTO)>0){
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
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteBasePoolById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(basePoolMapper.deleteBasePoolById(id)>0){
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
	 * @Date 2019年08月22日 10:21:07
	 * @Param id
	 * @return BasePoolVO
	 **/
	@Override
	public BasePoolVO getBasePoolById(Integer id){
		return basePoolMapper.getBasePoolById(id);
	}
	
}
