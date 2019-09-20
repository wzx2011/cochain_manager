package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.AppDTO;
import com.manage.cochain.entity.vo.AppVO;
import com.manage.cochain.mapper.AppMapper;
import com.manage.cochain.service.IAppService;
import com.manage.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 应用信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 14:19:49
 **/
@Service
public class AppServiceImpl implements IAppService  {

	@Autowired
	private AppMapper appMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return List<AppVO>
	 **/
	@Override
	public Map<String,Object> getAppAllList(AppDTO appDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<AppVO> appList = new ArrayList<>();
		Integer appTotal = appMapper.findAppTotal(appDTO);
		if(appTotal>0){
			appList = appMapper.getAppAllList(appDTO);
		}
		resultMap.put("rows", appList);
		resultMap.put("total", appTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveApp(AppDTO appDTO){
		Map<String,Object> resultMap=new HashMap<>();
		// 逻辑操作
		String name = appDTO.getName();
		String appId = MD5Utils.encrypt(name);
		String appKey = null;
		try {
			appKey = MD5Utils.encryptSHA(appId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		appDTO.setAppid(appId);
		appDTO.setAppkey(appKey);
		if(appMapper.saveApp(appDTO)>0){
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
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateApp(AppDTO appDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(appMapper.updateApp(appDTO)>0){
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
	 * @Date 2019年05月07日 14:19:49
	 * @Param AppDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteAppById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(appMapper.deleteAppById(id)>0){
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
	 * @Date 2019年05月07日 14:19:49
	 * @Param id
	 * @return AppVO
	 **/
	@Override
	public AppVO getAppById(Integer id){
		return appMapper.getAppById(id);
	}

	/**
	 * 获取应用的所用集合信息
	 *
	 * @param appDTO
	 * @return
	 */
	@Override
	public List<AppVO> getAppVOList(AppDTO appDTO) {
		return appMapper.getAppVOList(appDTO);
	}
}
