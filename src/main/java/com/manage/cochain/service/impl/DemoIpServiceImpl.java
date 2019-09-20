package com.manage.cochain.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.cochain.mapper.DemoIpMapper;
import com.manage.cochain.entity.vo.DemoIpVO;
import com.manage.cochain.entity.dto.DemoIpDTO;
import com.manage.cochain.service.IDemoIpService;


/**
 * 远程网址端口信息 Service 实现层
 * @author wzx
 * @create 2019年05月17日 09:31:55
 **/
@Service
public class DemoIpServiceImpl implements IDemoIpService  {

	@Autowired
	private DemoIpMapper demoIpMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return List<DemoIpVO>
	 **/
	@Override
	public Map<String,Object> getDemoIpAllList(DemoIpDTO demoIpDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<DemoIpVO> demoIpList = new ArrayList<>();
		Integer demoIpTotal = demoIpMapper.findDemoIpTotal(demoIpDTO);
		if(demoIpTotal>0){
			demoIpList = demoIpMapper.getDemoIpAllList(demoIpDTO);
		}
		resultMap.put("rows", demoIpList);
		resultMap.put("total", demoIpTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveDemoIp(DemoIpDTO demoIpDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoIpMapper.saveDemoIp(demoIpDTO)>0){
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
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateDemoIp(DemoIpDTO demoIpDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoIpMapper.updateDemoIp(demoIpDTO)>0){
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
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteDemoIpById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(demoIpMapper.deleteDemoIpById(id)>0){
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
	 * @Date 2019年05月17日 09:31:55
	 * @Param id
	 * @return DemoIpVO
	 **/
	@Override
	public DemoIpVO getDemoIpById(Integer id){
		return demoIpMapper.getDemoIpById(id);
	}

	/**
	 * 获取ip集合信息
	 *
	 * @param demoIpDTO
	 * @return
	 */
	@Override
	public List<DemoIpVO> getDemoIpVOList(DemoIpDTO demoIpDTO) {
		return demoIpMapper.getDemoIpVOList(demoIpDTO);
	}
}
