package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.BasePoolVO;
import com.manage.cochain.entity.dto.BasePoolDTO;

/**
 * 基础数据池 Mappeer 接口层
 * @author wzx
 * @create 2019年08月22日 10:21:07
**/
public interface BasePoolMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return List<BasePoolVO>
	 **/
	List<BasePoolVO> getBasePoolAllList(BasePoolDTO basePoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return Integer
	 **/
	Integer findBasePoolTotal(BasePoolDTO basePoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return int
	 **/
	int saveBasePool(BasePoolDTO basePoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return int
	 **/
	int updateBasePool(BasePoolDTO basePoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年08月22日 10:21:07
	 * @Param BasePoolDTO
	 * @return int
	 **/
	int deleteBasePoolById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年08月22日 10:21:07
	 * @Param id
	 * @return BasePoolVO
	 **/
	BasePoolVO getBasePoolById(@Param("id")Integer id);

}