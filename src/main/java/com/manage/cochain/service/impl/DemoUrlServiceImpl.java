package com.manage.cochain.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.cochain.mapper.DemoUrlMapper;
import com.manage.cochain.entity.vo.DemoUrlVO;
import com.manage.cochain.entity.dto.DemoUrlDTO;
import com.manage.cochain.service.IDemoUrlService;


/**
 * 远程接口上链路径信息 Service 实现层
 * @author wzx
 * @create 2019年05月17日 09:34:37
 **/
@Service
public class DemoUrlServiceImpl implements IDemoUrlService  {

	@Autowired
	private DemoUrlMapper demoUrlMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return List<DemoUrlVO>
	 **/
	@Override
	public Map<String,Object> getDemoUrlAllList(DemoUrlDTO demoUrlDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<DemoUrlVO> demoUrlList = new ArrayList<>();
		Integer demoUrlTotal = demoUrlMapper.findDemoUrlTotal(demoUrlDTO);
		if(demoUrlTotal>0){
			demoUrlList = demoUrlMapper.getDemoUrlAllList(demoUrlDTO);
		}
		resultMap.put("rows", demoUrlList);
		resultMap.put("total", demoUrlTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveDemoUrl(DemoUrlDTO demoUrlDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoUrlMapper.saveDemoUrl(demoUrlDTO)>0){
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
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateDemoUrl(DemoUrlDTO demoUrlDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoUrlMapper.updateDemoUrl(demoUrlDTO)>0){
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
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteDemoUrlById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoUrlMapper.deleteDemoUrlById(id)>0){
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
	 * @Date 2019年05月17日 09:34:37
	 * @Param id
	 * @return DemoUrlVO
	 **/
	@Override
	public DemoUrlVO getDemoUrlById(Integer id){
		return demoUrlMapper.getDemoUrlById(id);
	}

	/**
	 * 获取远程路径集合
	 *
	 * @param demoUrlDTO
	 * @return
	 */
	@Override
	public List<DemoUrlVO> getDemoUrlVOList(DemoUrlDTO demoUrlDTO) {
		return demoUrlMapper.getDemoUrlVOList(demoUrlDTO);
	}
}
