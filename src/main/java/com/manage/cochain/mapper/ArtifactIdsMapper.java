package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.ArtifactIdsVO;
import com.manage.cochain.entity.dto.ArtifactIdsDTO;

/**
 * 商品编码信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 11:40:41
**/
public interface ArtifactIdsMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return List<ArtifactIdsVO>
	 **/
	List<ArtifactIdsVO> getArtifactIdsAllList(ArtifactIdsDTO artifactIdsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return Integer
	 **/
	Integer findArtifactIdsTotal(ArtifactIdsDTO artifactIdsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return int
	 **/
	int saveArtifactIds(ArtifactIdsDTO artifactIdsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return int
	 **/
	int updateArtifactIds(ArtifactIdsDTO artifactIdsDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:40:41
	 * @Param ArtifactIdsDTO
	 * @return int
	 **/
	int deleteArtifactIdsById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:40:41
	 * @Param id
	 * @return ArtifactIdsVO
	 **/
	ArtifactIdsVO getArtifactIdsById(@Param("id")Integer id);

}