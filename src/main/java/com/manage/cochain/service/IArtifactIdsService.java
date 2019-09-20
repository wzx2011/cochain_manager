package com.manage.cochain.service;


import com.manage.cochain.entity.vo.ArtifactIdsVO;
import com.manage.cochain.entity.dto.ArtifactIdsDTO;
import java.util.Map;
/**
 * 商品编码信息 Service接口层
 * @author wzx
 * @create 2019年05月07日 11:40:41
 **/
public interface IArtifactIdsService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return List<ArtifactIdsVO>
	 **/
	Map<String,Object> getArtifactIdsAllList(ArtifactIdsDTO artifactIdsDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月07日 11:40:41
	* @Param ArtifactIdsDTO
	* @return Map
	**/
	Map<String, Object> saveArtifactIds(ArtifactIdsDTO artifactIdsDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月07日 11:40:41
	* @Param ArtifactIdsDTO
	* @return Map
	**/
	Map<String, Object> updateArtifactIds(ArtifactIdsDTO artifactIdsDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月07日 11:40:41
	* @Param ArtifactIdsDTO
	* @return Map
	**/
	Map<String, Object> deleteArtifactIdsById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月07日 11:40:41
	* @Param id
	* @return ArtifactIdsVO
	**/
	ArtifactIdsVO getArtifactIdsById(Integer id);
}
