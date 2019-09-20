package com.manage.cochain.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.cochain.mapper.DemoProjectMapper;
import com.manage.cochain.entity.vo.DemoProjectVO;
import com.manage.cochain.entity.dto.DemoProjectDTO;
import com.manage.cochain.service.IDemoProjectService;


/**
 * 远程接口项目信息 Service 实现层
 * @author wzx
 * @create 2019年05月17日 09:33:36
 **/
@Service
public class DemoProjectServiceImpl implements IDemoProjectService  {

	@Autowired
	private DemoProjectMapper demoProjectMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return List<DemoProjectVO>
	 **/
	@Override
	public Map<String,Object> getDemoProjectAllList(DemoProjectDTO demoProjectDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<DemoProjectVO> demoProjectList = new ArrayList<>();
		Integer demoProjectTotal = demoProjectMapper.findDemoProjectTotal(demoProjectDTO);
		if(demoProjectTotal>0){
			demoProjectList = demoProjectMapper.getDemoProjectAllList(demoProjectDTO);
		}
		resultMap.put("rows", demoProjectList);
		resultMap.put("total", demoProjectTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveDemoProject(DemoProjectDTO demoProjectDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoProjectMapper.saveDemoProject(demoProjectDTO)>0){
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
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateDemoProject(DemoProjectDTO demoProjectDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoProjectMapper.updateDemoProject(demoProjectDTO)>0){
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
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteDemoProjectById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoProjectMapper.deleteDemoProjectById(id)>0){
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
	 * @Date 2019年05月17日 09:33:36
	 * @Param id
	 * @return DemoProjectVO
	 **/
	@Override
	public DemoProjectVO getDemoProjectById(Integer id){
		return demoProjectMapper.getDemoProjectById(id);
	}

	/**
	 * 获取project集合
	 *
	 * @param demoProjectDTO
	 * @return
	 */
	@Override
	public List<DemoProjectVO> getDemoProjectVOList(DemoProjectDTO demoProjectDTO) {
		return demoProjectMapper.getDemoProjectVOList(demoProjectDTO);
	}
}
