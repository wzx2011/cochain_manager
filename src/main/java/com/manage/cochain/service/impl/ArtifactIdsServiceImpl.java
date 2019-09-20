package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.ArtifactIdsDTO;
import com.manage.cochain.entity.vo.ArtifactIdsVO;
import com.manage.cochain.mapper.ArtifactIdsMapper;
import com.manage.cochain.service.IArtifactIdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品编码信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 11:40:41
 **/
@Service
public class ArtifactIdsServiceImpl implements IArtifactIdsService  {

	@Autowired
	private ArtifactIdsMapper artifactIdsMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return List<ArtifactIdsVO>
	 **/
	@Override
	public Map<String,Object> getArtifactIdsAllList(ArtifactIdsDTO artifactIdsDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<ArtifactIdsVO> artifactIdsList = new ArrayList<>();
		Integer artifactIdsTotal = artifactIdsMapper.findArtifactIdsTotal(artifactIdsDTO);
		if(artifactIdsTotal>0){
			artifactIdsList = artifactIdsMapper.getArtifactIdsAllList(artifactIdsDTO);
		}
		resultMap.put("rows", artifactIdsList);
		resultMap.put("total", artifactIdsTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveArtifactIds(ArtifactIdsDTO artifactIdsDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(artifactIdsMapper.saveArtifactIds(artifactIdsDTO)>0){
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
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateArtifactIds(ArtifactIdsDTO artifactIdsDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(artifactIdsMapper.updateArtifactIds(artifactIdsDTO)>0){
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
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteArtifactIdsById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(artifactIdsMapper.deleteArtifactIdsById(id)>0){
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
	 * @Date 2019年05月07日 11:40:41
	 * @Param id
	 * @return ArtifactIdsVO
	 **/
	@Override
	public ArtifactIdsVO getArtifactIdsById(Integer id){
		return artifactIdsMapper.getArtifactIdsById(id);
	}
	
}
