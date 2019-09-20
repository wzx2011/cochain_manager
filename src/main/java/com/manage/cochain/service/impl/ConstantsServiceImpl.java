package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.ConstantsDTO;
import com.manage.cochain.entity.vo.ConstantsVO;
import com.manage.cochain.mapper.ConstantsMapper;
import com.manage.cochain.service.IConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 常量信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 11:42:11
 **/
@Service
public class ConstantsServiceImpl implements IConstantsService  {

	@Autowired
	private ConstantsMapper constantsMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return List<ConstantsVO>
	 **/
	@Override
	public Map<String,Object> getConstantsAllList(ConstantsDTO constantsDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<ConstantsVO> constantsList = new ArrayList<>();
		Integer constantsTotal = constantsMapper.findConstantsTotal(constantsDTO);
		if(constantsTotal>0){
			constantsList = constantsMapper.getConstantsAllList(constantsDTO);
		}
		resultMap.put("rows", constantsList);
		resultMap.put("total", constantsTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveConstants(ConstantsDTO constantsDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(constantsMapper.saveConstants(constantsDTO)>0){
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
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateConstants(ConstantsDTO constantsDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(constantsMapper.updateConstants(constantsDTO)>0){
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
	 * @Date 2019年05月07日 11:42:11
	 * @Param ConstantsDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteConstantsById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(constantsMapper.deleteConstantsById(id)>0){
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
	 * @Date 2019年05月07日 11:42:11
	 * @Param id
	 * @return ConstantsVO
	 **/
	@Override
	public ConstantsVO getConstantsById(Integer id){
		return constantsMapper.getConstantsById(id);
	}
	
}
